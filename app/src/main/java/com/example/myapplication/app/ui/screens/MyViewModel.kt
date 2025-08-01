package com.example.myapplication.app.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.usecase.GetMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor (
    val getMenuFromApi: GetMenuUseCase
): ViewModel() {
    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected
    val menuState = MutableStateFlow<Resource<List<MenuItemEntity>>>(Resource.Idle)


    fun fetchUsers(){
        viewModelScope.launch {
            try {
                getMenuFromApi().collect {
                    Log.d("TAG", "fetchUsers: $it")

                }

            }catch (e: Exception){
                e.printStackTrace()
                println(e.message)
                Log.d("TAG", "fetchUsers: ${e.message}")
            }
        }
    }
}