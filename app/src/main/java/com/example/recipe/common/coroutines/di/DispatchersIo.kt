package com.example.recipe.common.coroutines.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatchersIo

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatchersMain

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatchersDefault
