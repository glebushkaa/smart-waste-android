package ua.glebm.smartwaste.core.android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import ua.glebm.smartwaste.log.error
import ua.glebm.smartwaste.log.tag

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

abstract class BaseViewModel : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        error(this@BaseViewModel.tag(), throwable) {
            "ViewModel CoroutineExceptionHandler got $throwable in $coroutineContext"
        }
    }
    private val job = SupervisorJob()
    private val context = Dispatchers.Main.immediate + exceptionHandler + job

    val viewModelScope = CoroutineScope(context)

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
