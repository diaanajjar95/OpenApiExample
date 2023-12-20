package com.example.openapiexample.ui.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openapiexample.data.models.PostEntity

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: PostEntity,
    onItemClick: (postId: Int) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onItemClick.invoke(post.id.toInt())
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(size = 15.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .background(color = Color.White),
        ) {
            Box(
                modifier = Modifier
                    .size(103.dp)
                    .clip(RoundedCornerShape(10.dp)),
            ) {
                SplashAnimatedLogo()
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = post.title ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    modifier = Modifier,
                    text = post.body ?: "",
                    fontSize = 12.sp,
                    maxLines = 3,
                    color = Color.Gray
                )
            }
        }

    }
}


@Preview
@Composable
fun PostListItemPreview() {
    val post = PostEntity(
        id = 0L,
        title = "Diaa Post title",
        body = "Diaa post body ",
        userId = 10,
        postId = 11
    )
    PostListItem(post = post) {

    }
}