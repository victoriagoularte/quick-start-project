package com.example.recipe.domain.repository

import com.example.recipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipeList(): List<Recipe>
}