package com.onixx.mobileupintern.presentation.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import com.onixx.mobileupintern.R

//@Preview
@Composable
fun ErrorScreen(modifier: Modifier = Modifier, refreshAction : () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = modifier
                .fillMaxHeight(0.25f)
                .fillMaxWidth()
        )

        Box(modifier = modifier.fillMaxSize(0.4f)) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.error_image),
                contentDescription = "",
                modifier = modifier.fillMaxSize()
            )

        }
        Text(
            text = context.getString(R.string.error_text_1),
            textAlign = TextAlign.Center )
        Text(
            text = context.getString(R.string.error_text_2),
            textAlign = TextAlign.Center )

        Button(onClick = refreshAction ) {
            Text(text = context.getString(R.string.error_button_text))
        }

        Spacer(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )

    }

}