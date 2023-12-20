package com.example.openapiexample.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.openapiexample.ui.screens.component.PostListItem

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    val viewModel = hiltViewModel<HomeViewModel>()

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.size(4.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .navigationBarsPadding(), content = {
                items(uiState.postsList) {
                    PostListItem(post = it, onItemClick = { postId ->
                        Toast.makeText(context, "postId : $postId", Toast.LENGTH_SHORT).show()
                    }, onShareClick = { post ->
                        viewModel.facebookLinkShare(url = "https://www.linkedin.com/in/dia-a-najjar-b13b0816b/")
                    })
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}