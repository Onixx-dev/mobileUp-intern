package com.onixx.mobileupintern.presentation.screens.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.Locale


/**
 * TopBar для экрана информации о конкретной валюте
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navHostController: NavHostController) {

    TopAppBar(
        modifier = modifier.shadow(2.dp),
        title = { Text(text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }) },
        navigationIcon = {
            if (navHostController.previousBackStackEntry != null) {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

            } else {
                null
            }
        }
    )
}