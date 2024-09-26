package com.example.buiquangtuan.ui.Presentation.Order

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.buiquangtuan.MyApplication
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class OrderViewModel(application: MyApplication): ViewModel() {

    private val localRepository = application.getRepository()

    companion object {
        val Factory :ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                val  application = checkNotNull(extras[APPLICATION_KEY])
                return OrderViewModel(application as MyApplication) as T
            }
        }
    }

    fun onOrderClick(dataBaseEntity: DataBaseEntity) {
        viewModelScope.launch {
            localRepository.insertEntity(dataBaseEntity)
        }
    }

}