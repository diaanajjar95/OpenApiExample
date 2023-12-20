package com.example.openapiexample.ui.screens.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapiexample.R
import com.example.openapiexample.data.posts.PostDataSource
import com.example.openapiexample.domain.IsNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val isNetworkAvailable: IsNetworkAvailable,
    private val postsRepository: PostDataSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                if (isNetworkAvailable()) {
                    postsRepository.getPosts()
                } else {
                    _uiState.update {
                        it.copy(
                            message = application.getString(R.string.offline_mode_message)
                        )
                    }
                }

                val postsList = postsRepository.postsList.first()
                _uiState.update {
                    it.copy(
                        postsList = postsList,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        postsList = mutableListOf()
                    )
                }
            }
        }
    }

}