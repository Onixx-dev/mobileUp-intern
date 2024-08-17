package com.onixx.mobileupintern.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.onixx.mobileupintern.presentation.navigation.SetupNavGraph
import com.onixx.mobileupintern.presentation.theme.MobileUpInternTheme
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val vm  by viewModel<CurrencyViewModel>()

        setContent {
            MobileUpInternTheme {
                navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        SetupNavGraph(
                            navHostController = navController,
                            currencyViewModel = vm
                        )
                    }
                }
            }
        }
        vm.getCoinList()
    }
}
