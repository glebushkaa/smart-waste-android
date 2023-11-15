package ua.glebm.smartwaste.ui.screen.bucket

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.model.BucketItem
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@HiltViewModel
class BucketViewModel @Inject constructor() : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = BucketState(),
        reduceState = ::handle,
    )

    init {
        getBucketItems()
    }

    private fun getBucketItems() = viewModelScope.launch(Dispatchers.IO) {
        delay(1000)
        val list = mutableListOf<BucketItem>()
        repeat(20) { count ->
            list.add(
                BucketItem(
                    id = count.toLong(),
                    name = "Item $count",
                    count = count.coerceAtMost(10),
                    limit = 10,
                ),
            )
        }
        val event = BucketEvent.UpdateBucketItems(list)
        state.handleEvent(event)
    }

    private fun handle(currentState: BucketState, event: BucketEvent): BucketState {
        return when (event) {
            is BucketEvent.UpdateBucketItems -> {
                currentState.copy(items = event.items)
            }

            is BucketEvent.IncreaseCountClicked -> {
                currentState.copy(
                    items = currentState.items.map { item ->
                        if (item.id == event.id) {
                            item.copy(count = item.count + 1)
                        } else {
                            item
                        }
                    },
                )
            }

            is BucketEvent.DecreaseCountClicked -> {
                currentState.copy(
                    items = currentState.items.map { item ->
                        if (item.id == event.id) {
                            item.copy(count = item.count - 1)
                        } else {
                            item
                        }
                    },
                )
            }
        }
    }
}
