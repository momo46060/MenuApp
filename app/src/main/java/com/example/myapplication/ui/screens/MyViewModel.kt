package com.example.myapplication.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    fun fetchUsers(){
        viewModelScope.launch {
            try {
                val users= RetrofitClient.instance.getMenu()
                Log.d("TAG", "fetchUsers: $users")
            }catch (e: Exception){
                e.printStackTrace()
                println(e.message)
                Log.d("TAG", "fetchUsers: ${e.message}")


            }
        }
    }
}