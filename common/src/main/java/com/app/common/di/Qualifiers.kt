package com.app.common.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DiscoverAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DetailsAPI
