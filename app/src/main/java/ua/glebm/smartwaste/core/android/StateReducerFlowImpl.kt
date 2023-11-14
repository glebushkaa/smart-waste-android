package ua.glebm.smartwaste.core.android

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

class StateReducerFlowImpl<State, Action> @Inject constructor(
    initialState: State,
    reduceState: suspend (State, Action) -> State,
    scope: CoroutineScope,
) : StateReducerFlow<State, Action> {

    private val events = Channel<Action>()

    private val stateFlow = events
        .receiveAsFlow()
        .runningFold(initialState, reduceState)
        .stateIn(scope, SharingStarted.Eagerly, initialState)

    override val replayCache get() = stateFlow.replayCache

    override val value get() = stateFlow.value

    override suspend fun collect(collector: FlowCollector<State>): Nothing {
        stateFlow.collect(collector)
    }

    override fun handleEvent(action: Action) {
        events.trySend(action)
    }
}

fun <State, Event> BaseViewModel.stateReducerFlow(
    initialState: State,
    reduceState: suspend (State, Event) -> State = { it, _ -> it },
): StateReducerFlow<State, Event> {
    return StateReducerFlowImpl(initialState, reduceState, viewModelScope)
}
