package com.example.recipe.presentation.home

import com.example.recipe.domain.GetRecipesUseCase
import com.example.recipe.domain.model.Recipe
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeViewModelTest {

    private val useCase: GetRecipesUseCase = mockk()
    private val dispatcher = UnconfinedTestDispatcher()
    private val viewModel =
        HomeViewModel(useCase = useCase, dispatcher = dispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `getRecipeList should set correct states when useCase returns success`() =
        runTest {
            // Given
            val result = listOf(
                Recipe("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
                Recipe("Brigadeiro", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            )
            val initialState = HomeState()
            val successState = initialState.updateRecipeList(result)

            val testResults = arrayListOf<HomeState>()
            val job = launch(dispatcher) { viewModel.uiState.toList(destination = testResults) }

            every { useCase() } returns flowOf(result)

            // When
            viewModel.getRecipeList()

            // Then
            assertEquals(listOf(initialState, successState), testResults)
            job.cancel()
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}