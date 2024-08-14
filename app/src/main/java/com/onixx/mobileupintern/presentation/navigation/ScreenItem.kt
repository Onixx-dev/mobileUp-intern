package com.onixx.mobileupintern.presentation.navigation

sealed class ScreenItem(val route: String) {

    data object ListPage: ScreenItem("list-page")
    data object InfoPage: ScreenItem("info-page")

}