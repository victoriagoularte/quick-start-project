package com.example.recipe.data.datasource

import com.example.recipe.data.model.RecipeResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRemoteDataSource {

    suspend fun getRecipeList() : List<RecipeResponse>
}