package com.example.myapplication.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.app.ui.views.MenuItem
import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.domain.model.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel(),
) {
    val menuState by viewModel.menu.collectAsStateWithLifecycle()
    val filteredMenu by viewModel.filteredMenu.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        FiltersSection(viewModel)
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

@Composable
fun FiltersSection(viewModel: MyViewModel) {
    val showVeganOnly by viewModel.showVeganOnly.collectAsStateWithLifecycle()
    val showHotOnly by viewModel.showHotOnly.collectAsStateWithLifecycle()
    val showAvailableOnly by viewModel.showAvailableOnly.collectAsStateWithLifecycle()
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DropdownMenuFilter(
            label = "Sort",
            options = listOf("Price Asc", "Price Desc", "Rating"),
            selectedOption = sortOrder,
            onOptionSelected = {
                when (it) {
                    "Price Asc" -> viewModel.setSortOrder("Price Asc")
                    "Price Desc" -> viewModel.setSortOrder("Price Desc")
                    "Rating" -> viewModel.setSortOrder("Rating")
                }
            }
        )
        FilterToggle("Vegan", state = showVeganOnly) { viewModel.setShowVeganOnly() }
        FilterToggle("Hot", state = showHotOnly) { viewModel.setShowHotOnly() }
        FilterToggle("Available", state = showAvailableOnly) { viewModel.setShowAvailableOnly() }
    }
}


@Composable
fun DropdownMenuFilter(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text(
            text = "$label: $selectedOption",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE7ECE7))
                .padding(8.dp)
                .clickable { expanded = true }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun FilterToggle(label: String, state: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (state) Color(0xFF4CAF50) else Color(0xFFE7ECE7))
            .clickable { onToggle(!state) }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = if (state) Color.White else Color.Black
        )
    }
}