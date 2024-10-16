package com.empty.template.snippets.compose

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.empty.template.R

Box(
    modifier = Modifier
    .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.backg),
            contentDescription = "bg"
        )
    }