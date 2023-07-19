package com.example.recipe.domain

import com.example.recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {

    suspend operator fun invoke() = repository.getRecipeList()
}