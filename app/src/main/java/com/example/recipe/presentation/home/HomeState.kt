package com.example.recipe.presentation.home

import com.example.recipe.domain.model.Recipe

data class HomeState(
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList(),
) {
    fun showLoading() = this.copy(isLoading = true, recipeList = emptyList())
    fun updateRecipeList(list: List<Recipe>) = this.copy(isLoading = false, recipeList = list)
}
