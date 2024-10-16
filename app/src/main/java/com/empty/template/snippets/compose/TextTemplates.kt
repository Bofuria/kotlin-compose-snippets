package com.empty.template.snippets.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily

@Composable
fun SimpleText(
    text: String,
    padding: Int = 8,
    size: Int = 16,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.inverseSurface
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = size.sp,
            color = color
        ),
        modifier = Modifier
//            .wrapContentWidth()
            .padding(padding.dp)
    )
}

@Composable
fun HeadlineText(
    text: String,
    padding: Int = 16,
    size: Int = 24,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    color: Color = MaterialTheme.colorScheme.inverseSurface
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = bodyFontFamily,
            fontSize = size.sp,
            color = color
        ),
        modifier = Modifier
            .padding(padding.dp),
    )
}