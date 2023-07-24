package com.example.recipe.di

import com.example.recipe.data.datasource.RecipeRemoteDataSource
import com.example.recipe.data.datasource.RecipeRemoteDataSourceImpl
import com.example.recipe.data.repository.RecipeRepositoryImpl
import com.example.recipe.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl,
    ): RecipeRepository

    @Binds
    abstract fun bindRecipeDataSource(
        recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl,
    ): RecipeRemoteDataSource
}
