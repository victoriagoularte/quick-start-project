package com.example.recipe.data.datasource

import com.example.recipe.data.model.RecipeResponse
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor() : RecipeRemoteDataSource {

    override suspend fun getRecipeList(): List<RecipeResponse> {
        // response should be the result from injected service
        return listOf(
            RecipeResponse(
                "Fricasse",
                "https://receitas123.com/wp-content/uploads/2023/03/fricasse-de-frango.png"
            ),
            RecipeResponse(
                "Brigadeiro",
                "https://cdn.panelinha.com.br/receita/958014000000-Brigadeiro.jpg"
            ),
            RecipeResponse(
                "Estrogonofe",
                "https://ciadereceitas.com.br/wp-content/uploads/2022/09/estrogonofe-de-frango.png"
            ),
            RecipeResponse(
                "Lasanha",
                "https://www.receiteria.com.br/wp-content/uploads/lasanha-de-abobrinha-com-carne-moida-013-730x449.png"
            ),
            RecipeResponse(
                "Escondidinho",
                "https://img.cybercook.com.br/receitas/768/escondidinho-de-carne-moida-4-840x480.jpeg?q=75"
            ),
            RecipeResponse(
                "Bacalhau",
                "https://foodandroad.com/wp-content/uploads/2021/04/bacalhau-bras-1.jpg"
            ),
        )
    }
}