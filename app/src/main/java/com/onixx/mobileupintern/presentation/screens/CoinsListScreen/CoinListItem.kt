package com.onixx.mobileupintern.presentation.screens.CoinsListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.presentation.theme.TextColorH1
import com.onixx.mobileupintern.presentation.theme.TextColorH2
import com.onixx.mobileupintern.presentation.theme.TextColorNegative
import com.onixx.mobileupintern.presentation.theme.TextColorPositive
import com.onixx.mobileupintern.presentation.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


/**
 * константа высоты элемента списка
 */
val ROW_HEIGHT_DP = 60.dp


/**
 * верстка элемента списка. В Row изображение, и две колонки по 2 элемента с текстом
 * @param coin - модель данных
 * @param onItemClick - лямбда на клин по данному элементу списка.
 * Вызывает переход в окно информации, передает в него id, связывающий эту модель и модель расширенных данных в API
 * @see CurrencyViewModel.getCoinInfo
 */
@Composable
fun CoinListItem(modifier: Modifier = Modifier,
                 coin: Coin,
                 onItemClick: (String) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(ROW_HEIGHT_DP)
            .clickable {
                if (coin.id != null)
                    onItemClick(coin.id!!)
            }
    ) {

        GlideImage(
            modifier = modifier.size(ROW_HEIGHT_DP),
            imageModel = { coin.image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )

        Spacer(
            modifier = modifier
                .fillMaxHeight()
                .width(15.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight()
        ) {
            Text(
                text = coin.name.toString(),
                color = TextColorH1,
                style = Typography.bodyLarge,
                modifier = modifier.fillMaxHeight(0.5f)
            )
            Text(
                text = coin.symbol.toString(),
                color = TextColorH2,
                style = Typography.bodyMedium,
                modifier = modifier.fillMaxHeight()
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = AbsoluteAlignment.Right
        ) {
            Text(
                text = coin.currentPriceStr,
                color = TextColorH1,
                style = Typography.bodyLarge,
                modifier = modifier.fillMaxHeight(0.5f)
            )
            if (coin.priceChangePercentage24h!! < 0)
                Text(
                    text = coin.percentChangesStr,
                    color = TextColorNegative,
                    style = Typography.bodyMedium,
                    modifier = modifier.fillMaxHeight()
                )
            else
                Text(
                    text = coin.percentChangesStr,
                    color = TextColorPositive,
                    style = Typography.bodyMedium,
                    modifier = modifier.fillMaxHeight()
                )
        }

    }

}
