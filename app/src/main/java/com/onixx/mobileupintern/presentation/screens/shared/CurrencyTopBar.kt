package com.onixx.mobileupintern.presentation.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onixx.mobileupintern.R
import com.onixx.mobileupintern.presentation.theme.ChipsDisableColorBackground
import com.onixx.mobileupintern.presentation.theme.ChipsDisableColorText
import com.onixx.mobileupintern.presentation.theme.ChipsEnableColorBackground
import com.onixx.mobileupintern.presentation.theme.ChipsEnableColorText
import com.onixx.mobileupintern.presentation.theme.Typography

//@Preview
@Composable
fun CurrencyTopBar(
    modifier: Modifier = Modifier,
    items: ArrayList<String>,
    onChipClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .shadow(1.dp)
            .fillMaxWidth()
            .height(140.dp)
            .padding(start = 25.dp, top = 25.dp)
    ) {
        val context = LocalContext.current

        Text(text = context.getString(R.string.topbar_text_main),
            style = Typography.titleLarge,
            modifier = modifier
        )

        ChipGroupCompose(
            modifier = modifier.padding(top = 35.dp),
            items = items,
            onChipClick = onChipClick
        )

    }
}

@Composable
fun ChipGroupCompose(
    modifier: Modifier,
    items: ArrayList<String>,
    onChipClick: (String) -> Unit
) {
    var selected by remember { mutableStateOf(items.first()) }

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    onChipClick(selected)
                }
            )
            Spacer(modifier = modifier
                .width(10.dp))
        }
    }

}

@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {

    val isSelected = selected == title

    val background = if (isSelected) ChipsEnableColorBackground else ChipsDisableColorBackground
    val contentColor = if (isSelected) ChipsEnableColorText else ChipsDisableColorText

    Box(
        modifier = Modifier
            .height(40.dp)
            .width(90.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    onSelected(title)
                }
            )
    ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title.uppercase(),
                color = contentColor,
                fontSize = 16.sp)

        }
    }



