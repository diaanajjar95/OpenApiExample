package com.example.openapiexample.data.posts

import com.example.openapiexample.data.mappers.toEntity
import com.example.openapiexample.data.models.PostEntity
import com.example.openapiexample.data.source.remote.DefaultResponse
import com.example.openapiexample.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDataSourceImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostLocalDataSource,
) : PostDataSource {

    override val postsList: Flow<List<PostEntity>>
        get() = localDataSource.postsList

    override suspend fun getPosts() {
        withContext(dispatcher) {
            val result = remoteDataSource.getPosts()
            if (result is DefaultResponse.Success) {
                val posts = result.body.map {
                    it.toEntity()
                }.toMutableList()
                localDataSource.refreshPosts(posts)
            }
        }
    }

}