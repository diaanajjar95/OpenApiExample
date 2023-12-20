package com.example.openapiexample.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkMoshi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseMoshi
