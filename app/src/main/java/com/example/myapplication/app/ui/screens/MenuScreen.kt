package com.example.myapplication.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.app.ui.views.FiltersSection
import com.example.myapplication.domain.model.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val menuState by viewModel.menu.collectAsStateWithLifecycle()
    val filteredMenu by viewModel.filteredMenu.collectAsStateWithLifecycle()
    val showVeganOnly by viewModel.showVeganOnly.collectAsStateWithLifecycle()
    val showHotOnly by viewModel.showHotOnly.collectAsStateWithLifecycle()
    val showAvailableOnly by viewModel.showAvailableOnly.collectAsStateWithLifecycle()
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        FiltersSection(
            showVeganOnly = showVeganOnly,
            showHotOnly = showHotOnly,
            showAvailableOnly = showAvailableOnly,
            sortOrder = sortOrder,
            setSortOrder = { viewModel.setSortOrder(it) },
            setShowVeganOnly = { viewModel.setShowVeganOnly() },
            setShowHotOnly = { viewModel.setShowHotOnly() },
            setShowAvailableOnly = { viewModel.setShowAvailableOnly() }
        )
        Spacer(modifier = Modifier.height(2.dp))

        when (menuState) {
            is Resource.Loading -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(5.dp))
                    Text("Loading...", modifier = Modifier.padding(16.dp))
                }
            }
            is Resource.Success -> {
                MenuScreenContent(
                    menu = filteredMenu
                )
            }
            is Resource.Error -> {
                Text("Error: ${(menuState as Resource.Error).message}")
            }
            else -> {}
        }
    }
}




