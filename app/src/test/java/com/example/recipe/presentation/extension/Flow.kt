package com.example.recipe.presentation.extension

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope

fun <State> TestScope.testWith(
    state: StateFlow<State>,
    dispatcher: CoroutineDispatcher,
    block: () -> Unit,
): ArrayList<State> {
    val stateResult = arrayListOf<State>()
    val jobState = launch(dispatcher) { state.toList(destination = stateResult) }

    block.invoke()

    jobState.cancel()
    return stateResult
}

fun <State, Event> TestScope.testWith(
    state: StateFlow<State>? = null,
    event: SharedFlow<Event>? = null,
    dispatcher: CoroutineDispatcher,
    block: () -> Unit,
): Pair<ArrayList<State>, ArrayList<Event>> {
    var jobState: Job? = null
    var jobEvent: Job? = null
    val stateResult = arrayListOf<State>()
    val eventResult = arrayListOf<Event>()

    state?.let {
        jobState = launch(dispatcher) { it.toList(destination = stateResult) }
    }

    event?.let {
        jobEvent = launch(dispatcher) { it.toList(destination = eventResult) }
    }

    // When
    block.invoke()

    // Then
    jobState?.cancel()
    jobEvent?.cancel()
    return Pair(stateResult, eventResult)
}

fun <State> ArrayList<State>.inOrder(vararg expected: State) {
    assertEquals(expected.toList(), this)
}
