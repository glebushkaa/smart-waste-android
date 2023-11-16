package ua.glebm.smartwaste.ui.screen.bucket

import android.net.Uri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.core.common.capitalize
import ua.glebm.smartwaste.domain.usecase.bucket.AddBucketItemUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.DecreaseBucketItemCountUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.GetAllBucketItemsUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.GetBucketFlowUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.IncreaseBucketItemCountUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.ScanItemUseCase
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@HiltViewModel
class BucketViewModel @Inject constructor(
    private val getBucketFlowUseCase: GetBucketFlowUseCase,
    private val increaseBucketItemCountUseCase: IncreaseBucketItemCountUseCase,
    private val decreaseBucketItemCountUseCase: DecreaseBucketItemCountUseCase,
    private val addBucketItemUseCase: AddBucketItemUseCase,
    private val scanItemUseCase: ScanItemUseCase,
    private val getAllBucketItemsUseCase: GetAllBucketItemsUseCase,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = BucketState(),
        reduceState = ::handle,
    )
    val sideEffect = Channel<BucketSideEffect>()

    init {
        getAllBucketItems()
        collectBucketItems()
    }

    private fun getAllBucketItems() = viewModelScope.launch(Dispatchers.IO) {
        val items = getAllBucketItemsUseCase().getOrNull() ?: return@launch
        val event = BucketEvent.UpdateAllBucketItems(items)
        state.handleEvent(event)
    }

    private fun collectBucketItems() = viewModelScope.launch(Dispatchers.IO) {
        getBucketFlowUseCase().getOrNull()?.collectLatest {
            val event = BucketEvent.UpdateBucketItems(it)
            state.handleEvent(event)
        }
    }

    private fun increaseCountClicked(name: String) = viewModelScope.launch(Dispatchers.IO) {
        val params = IncreaseBucketItemCountUseCase.Params(name)
        increaseBucketItemCountUseCase(params)
    }

    private fun decreaseCountClicked(name: String) = viewModelScope.launch(Dispatchers.IO) {
        val params = DecreaseBucketItemCountUseCase.Params(name)
        decreaseBucketItemCountUseCase(params)
    }

    private fun tryAddBucketItem() = viewModelScope.launch(Dispatchers.IO) {
        val bucketItem = state.value.bottomSheetBucketItem ?: return@launch
        state.value.items.firstOrNull { bucketItem.name == it.name }?.let {
            val effect =
                BucketSideEffect.ShowToast("${bucketItem.name.capitalize()} already exists")
            sideEffect.send(effect)
            return@launch
        }
        val count = state.value.newBucketItemCount
        val params = AddBucketItemUseCase.Params(bucketItem.copy(count = count))
        addBucketItemUseCase(params)
        val event = BucketEvent.ClearSelectedBucketItem
        state.handleEvent(event)
    }

    private fun scanImage(uri: Uri) = viewModelScope.launch(Dispatchers.IO) {
        val showLoaderEvent = BucketEvent.ShowLoader
        state.handleEvent(showLoaderEvent)
        val params = ScanItemUseCase.Params(uri)
        val item = scanItemUseCase(params).getOrNull()
        item ?: run {
            val effect = BucketSideEffect.ShowToast("Item was not recognized")
            val hideLoader = BucketEvent.HideLoader
            state.handleEvent(hideLoader)
            sideEffect.send(effect)
            return@launch
        }
        val event = BucketEvent.SendBucketItem(item)
        state.handleEvent(event)
    }

    private fun handle(currentState: BucketState, event: BucketEvent): BucketState {
        when (event) {
            is BucketEvent.UpdateBucketItems -> {
                return currentState.copy(items = event.items)
            }

            is BucketEvent.IncreaseCountClicked -> {
                increaseCountClicked(event.name)
            }

            is BucketEvent.DecreaseCountClicked -> {
                decreaseCountClicked(event.name)
            }

            BucketEvent.HideAddItemSheet -> {
                return currentState.copy(addItemBottomSheetShown = false)
            }

            BucketEvent.ShowAddItemSheet -> {
                return currentState.copy(addItemBottomSheetShown = true)
            }

            is BucketEvent.ScanImageUri -> {
                val uri = event.imageUri ?: return currentState
                scanImage(uri)
            }

            is BucketEvent.AddBucketItem -> tryAddBucketItem()
            BucketEvent.DecreaseNewBucketItemCount -> {
                val count = (currentState.newBucketItemCount - 1).coerceAtLeast(0)
                return currentState.copy(newBucketItemCount = count)
            }

            BucketEvent.IncreaseNewBucketItemCount -> {
                val count = (currentState.newBucketItemCount + 1).coerceAtMost(10)
                return currentState.copy(newBucketItemCount = count)
            }

            is BucketEvent.SendBucketItem -> {
                return currentState.copy(
                    bottomSheetBucketItem = event.bucketItem,
                    loaderVisible = false,
                )
            }

            is BucketEvent.UpdateAllBucketItems -> {
                return currentState.copy(
                    allAvailableItems = event.items,
                    bottomSheetBucketItem = event.items.firstOrNull(),
                )
            }

            BucketEvent.ClearSelectedBucketItem -> {
                return currentState.copy(
                    bottomSheetBucketItem = state.value.allAvailableItems.firstOrNull(),
                    newBucketItemCount = 0,
                )
            }

            BucketEvent.HideLoader -> {
                return currentState.copy(loaderVisible = false)
            }

            BucketEvent.ShowLoader -> {
                return currentState.copy(loaderVisible = true)
            }
        }
        return currentState
    }
}
