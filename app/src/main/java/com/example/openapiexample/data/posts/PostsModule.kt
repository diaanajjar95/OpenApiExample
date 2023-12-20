package com.example.openapiexample.data.posts


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PostsModule {

    @Binds
    @Singleton
    abstract fun bindPostLocalDataSource(
        postLocalDataSourceImpl: PostLocalDataSourceImpl,
    ): PostLocalDataSource

    @Binds
    @Singleton
    abstract fun bindPostRemoteDataSource(
        postRemoteDataSourceImpl: PostRemoteDataSourceImpl,
    ): PostRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postDataSourceImpl: PostDataSourceImpl,
    ): PostDataSource

}