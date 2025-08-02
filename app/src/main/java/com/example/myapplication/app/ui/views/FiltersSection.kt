package com.example.myapplication.app.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FiltersSection(
    showVeganOnly: Boolean,
    showHotOnly: Boolean,
    showAvailableOnly: Boolean,
    sortOrder: String,
    setSortOrder:(String)->Unit,
    setShowVeganOnly:()->Unit,
    setShowHotOnly:()->Unit,
    setShowAvailableOnly:()->Unit,
    ) {
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
            onOptionSelected = {setSortOrder(it) }
        )
        FilterToggle("Vegan", state = showVeganOnly) { setShowVeganOnly() }
        FilterToggle("Hot", state = showHotOnly) { setShowHotOnly() }
        FilterToggle("Available", state = showAvailableOnly) {setShowAvailableOnly() }
    }
}

