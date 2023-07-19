package com.example.recipe.presentation.home

import com.example.recipe.common.test.MainDispatcherRule
import com.example.recipe.domain.GetRecipesUseCase
import com.example.recipe.domain.model.Recipe
import com.example.recipe.presentation.extension.inOrder
import com.example.recipe.presentation.extension.testWith
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    private val useCase: GetRecipesUseCase = mockk()
    private val viewModel = HomeViewModel(useCase = useCase, dispatcher = rule.dispatcher)

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

            coEvery { useCase() } returns result

            // When
            val testResults = testWith(viewModel.uiState, rule.dispatcher) {
                viewModel.getRecipeList()
            }

            // Then
            testResults.inOrder(initialState, successState)
        }
}