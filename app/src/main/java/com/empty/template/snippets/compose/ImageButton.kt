package com.empty.template.snippets.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.empty.template.R

@Composable
fun ButtonImage(
    onClick: () -> Unit
) {
    Column(
        Modifier
            .padding(20.dp)
            .width(200.dp)
            .height(60.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClick()
                    },
                painter = painterResource(id = R.drawable.button),
                contentDescription = "Button",
            )
            Text(
                text = "Show/Hide text",
                color = Color.White
            )
        }
    }
}