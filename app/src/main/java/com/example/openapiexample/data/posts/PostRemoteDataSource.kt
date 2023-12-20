package com.example.openapiexample.data.posts

import com.example.openapiexample.data.models.Post
import com.example.openapiexample.data.source.remote.DefaultResponse

interface PostRemoteDataSource {

    suspend fun getPosts(): DefaultResponse<List<Post>>

}