package com.example.openapiexample.data.posts

import com.example.openapiexample.data.models.PostEntity
import com.example.openapiexample.data.source.local.daos.PostDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLocalDataSourceImpl @Inject constructor(
    private val postDao: PostDao,
) : PostLocalDataSource {

    private val _postsList: Flow<List<PostEntity>> = postDao.getAllPosts()

    override val postsList: Flow<List<PostEntity>>
        get() = _postsList

    override suspend fun refreshPosts(postsList: MutableList<PostEntity>) {
        postDao.insertAllPosts(*postsList.toTypedArray())
    }

}