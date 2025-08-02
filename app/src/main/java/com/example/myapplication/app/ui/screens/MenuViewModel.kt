package com.example.myapplication.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.data.repository.SettingsRepositoryImpl
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.repository.SettingsRepository
import com.example.myapplication.domain.usecase.AddItemToFavorite
import com.example.myapplication.domain.usecase.GetMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
   private val getMenu: GetMenuUseCase,
   private val addItemToFavoriteUseCase: AddItemToFavorite,
    private val settingsRepository: SettingsRepositoryImpl

) : ViewModel() {
    private val _menu = MutableStateFlow<Resource<List<MenuItemEntity>>>(Resource.Idle)
    val menu: StateFlow<Resource<List<MenuItemEntity>>> = _menu

    private val _sortOrder = MutableStateFlow("Price Asc")
     val sortOrder = _sortOrder
    private val _showVeganOnly = MutableStateFlow(false)
     val showVeganOnly = _showVeganOnly
    private val _showHotOnly = MutableStateFlow(false)
     val showHotOnly = _showHotOnly
    private val _showAvailableOnly = MutableStateFlow(false)
     val showAvailableOnly = _showAvailableOnly
    val isDarkMode: StateFlow<Boolean> = settingsRepository.isDarkModeFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val filteredMenu: StateFlow<List<MenuItemEntity>> = combine(
        _menu,
        _sortOrder,
        _showVeganOnly,
        _showHotOnly,
        _showAvailableOnly
    ) { menuState, sort, vegan, hot, available ->

        val originalList = (menuState as? Resource.Success)?.data ?: emptyList()

        var filtered = originalList

        if (vegan) filtered = filtered.filter { it.vegan }
        if (hot) filtered = filtered.filter { it.hot }
        if (available) filtered = filtered.filter { it.available }

        filtered = when (sort) {
            "" -> filtered.sortedBy { it.price }
            "Price Desc" -> filtered.sortedByDescending { it.price }
            "Rating" -> filtered.sortedByDescending { it.rating }
            else -> filtered
        }
        filtered
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            getMenu()
                .flowOn(Dispatchers.IO)
                .collect {
                    _menu.value = it
                }
        }

    }
    fun setSortOrder(order: String) { _sortOrder.value = order }
    fun setShowVeganOnly() { _showVeganOnly.value = !_showVeganOnly.value }
    fun setShowHotOnly() { _showHotOnly.value = ! _showHotOnly.value }
    fun setShowAvailableOnly() { _showAvailableOnly.value = !_showAvailableOnly.value }
     fun addItemToFavorite(itemId:String) {
         viewModelScope.launch(Dispatchers.IO) {
             addItemToFavoriteUseCase(itemId)
         }
    }
    fun toggleDarkMode() {
        viewModelScope.launch {
            settingsRepository.setDarkMode(!(isDarkMode.value))
        }
    }

    fun setDarkMode(value: Boolean) {
        viewModelScope.launch { settingsRepository.setDarkMode(value) }
    }

}