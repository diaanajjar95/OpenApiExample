package com.example.openapiexample.data.source.remote

import com.example.openapiexample.data.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndpoints {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}