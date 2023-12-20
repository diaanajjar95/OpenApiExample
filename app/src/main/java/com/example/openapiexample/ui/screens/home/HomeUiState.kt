package com.example.openapiexample.ui.screens.home

import com.example.openapiexample.data.models.PostEntity

data class HomeUiState(
    val postsList: List<PostEntity> = listOf(),
)