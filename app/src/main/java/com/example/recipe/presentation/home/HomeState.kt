package com.example.recipe.presentation.home

import com.example.recipe.domain.model.Recipe

data class HomeState(
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList(),
    val textFilter: String = "",
) {
    fun showLoading() = this.copy(isLoading = true, recipeList = emptyList())
    fun hideLoading() = this.copy(isLoading = false)
    fun updateRecipeList(list: List<Recipe>) = this.copy(isLoading = false, recipeList = list)
    fun updateFilter(text: String) = this.copy(textFilter = text)
}
