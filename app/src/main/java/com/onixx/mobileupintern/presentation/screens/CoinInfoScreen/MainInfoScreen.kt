package com.onixx.mobileupintern.presentation.screens.CoinInfoScreen

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.onixx.mobileupintern.domain.model.CoinInfo
import com.onixx.mobileupintern.presentation.screens.CoinsListScreen.ROW_HEIGHT_DP
import com.onixx.mobileupintern.presentation.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun MainInfoScreen(
    modifier: Modifier = Modifier,
    coinInfo: CoinInfo
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
    )
    {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            GlideImage(
                modifier = modifier.width(160.dp),
                imageModel = { coinInfo.image!!.large },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
        }


        Text(text = "Описание",
            modifier = modifier.padding(top = 15.dp),
            style = Typography.titleLarge
        )

        Text(
            text = HtmlCompat.fromHtml(
                coinInfo.description!!.en!!,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            ).toString(),
            style = Typography.bodyLarge
        )

        Text(text = "Категории",
            modifier = modifier.padding(top = 15.dp),
            style = Typography.titleLarge)

        Text(
            text = coinInfo.categories.joinToString(separator = ", "),
            style = Typography.bodyLarge
        )

    }
}