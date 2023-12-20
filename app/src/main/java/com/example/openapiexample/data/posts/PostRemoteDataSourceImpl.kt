package com.example.openapiexample.data.posts

import com.example.openapiexample.data.models.Post
import com.example.openapiexample.data.source.remote.ApiEndpoints
import com.example.openapiexample.data.source.remote.DefaultResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRemoteDataSourceImpl @Inject constructor(
    private val apiEndPoints: ApiEndpoints,
) : PostRemoteDataSource {

    override suspend fun getPosts(): DefaultResponse<List<Post>> {
        return try {
            DefaultResponse.create(
                apiEndPoints.getPosts()
            )
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }

}