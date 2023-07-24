package com.example.recipe.domain.repository

import com.example.recipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipeList(): Flow<List<Recipe>>
}
