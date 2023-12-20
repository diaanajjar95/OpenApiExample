package com.example.openapiexample.ui.screens.home

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapiexample.AppState
import com.example.openapiexample.R
import com.example.openapiexample.data.posts.PostDataSource
import com.example.openapiexample.domain.IsNetworkAvailable
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.share.Sharer
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val isNetworkAvailable: IsNetworkAvailable,
    private val postsRepository: PostDataSource,
    appState: AppState,
) : AppState by appState, ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            setLoadingState(true)
            try {
                if (isNetworkAvailable()) {
                    postsRepository.getPosts()
                } else {
                    setToastMsg(application.getString(R.string.offline_mode_message))
                }

                val postsList = postsRepository.postsList.first()
                _uiState.update {
                    it.copy(
                        postsList = postsList,
                    )
                }
                setLoadingState(false)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        postsList = mutableListOf()
                    )
                }
            }
        }
    }

    fun facebookLinkShare(url: String) {
        val shareContent =
            ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).build()

        val cb = CallbackManager.Factory.create()

        activityContext?.let {
            val shareDialog = ShareDialog(it)
            shareDialog.registerCallback(cb, object : FacebookCallback<Sharer.Result> {
                override fun onCancel() {
                    Log.d(TAG, "registerCallback onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "registerCallback onError : $error")
                }

                override fun onSuccess(result: Sharer.Result) {
                    Log.d(TAG, "registerCallback onSuccess")
                }
            })
            shareDialog.show(shareContent, ShareDialog.Mode.AUTOMATIC)
        }
    }

}