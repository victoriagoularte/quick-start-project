package com.example.recipe.data.repository

import com.example.recipe.data.datasource.RecipeRemoteDataSource
import com.example.recipe.data.model.RecipeResponse
import com.example.recipe.domain.model.Recipe
import com.example.recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl
    @Inject
    constructor(private val dataSource: RecipeRemoteDataSource) : RecipeRepository {

        override fun getRecipeList(): Flow<List<Recipe>> {
            return dataSource.getRecipeList().map(::responseAsDomain)
        }
    }

fun responseAsDomain(response: List<RecipeResponse>): List<Recipe> {
    return response.map { Recipe(it.name, it.image) }
}
