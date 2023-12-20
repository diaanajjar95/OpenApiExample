package com.example.openapiexample.data.posts

import com.example.openapiexample.data.models.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostDataSource {

    val postsList: Flow<List<PostEntity>>

    suspend fun getPosts()

}