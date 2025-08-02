package com.example.myapplication.app.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.app.ui.views.MenuItem
import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.domain.model.Resource

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
