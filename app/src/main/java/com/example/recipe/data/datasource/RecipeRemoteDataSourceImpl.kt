package com.example.recipe.data.datasource

import com.example.recipe.data.model.RecipeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor() : RecipeRemoteDataSource {

    override fun getRecipeList(): Flow<List<RecipeResponse>> = flow {
        // response should be the result from injected service
        val response = listOf(
            RecipeResponse("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            RecipeResponse("Brigadeiro", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            RecipeResponse("Estrogonofe", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            RecipeResponse("Lasanha", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            RecipeResponse("Escondidinho", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
            RecipeResponse("Bacalhau", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
        )
        emit(response)
    }
}