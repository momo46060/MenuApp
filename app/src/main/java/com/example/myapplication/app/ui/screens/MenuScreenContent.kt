package com.example.myapplication.app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.app.ui.views.MenuItem
import com.example.myapplication.data.local.entity.MenuItemEntity

@Composable
fun MenuScreenContent(
    menu: List<MenuItemEntity>,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(menu) { item ->
            MenuItem(item)
        }
    }


}
