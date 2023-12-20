package com.example.openapiexample

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

interface AppState {

    val loadingState: SharedFlow<Boolean>

    fun setLoadingState(isLoading: Boolean)

    fun setToastMsg(text: String)

    var activityContext: Activity?
}

class AppStateImpl @Inject constructor(private val application: Context) : AppState {

    private val _loadingState = MutableSharedFlow<Boolean>(replay = 1)

    private var _context: Activity? = null
    override val loadingState: SharedFlow<Boolean>
        get() = _loadingState

    override fun setLoadingState(isLoading: Boolean) {
        _loadingState.tryEmit(isLoading)
    }

    override fun setToastMsg(text: String) {
        text.toast(application)
    }

    override var activityContext: Activity? = _context
}