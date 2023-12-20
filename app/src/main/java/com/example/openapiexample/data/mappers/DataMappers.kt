package com.example.openapiexample.data.mappers

import com.example.openapiexample.data.models.Post
import com.example.openapiexample.data.models.PostEntity

fun Post.toEntity(): PostEntity {
    return PostEntity(
        title = title,
        body = body,
        userId = userId,
        postId = id
    )
}