package com.example.buiquangtuan.ui.Presentation.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.buiquangtuan.MyApplication
import com.example.buiquangtuan.domain.model.Order
import com.example.buiquangtuan.ui.Presentation.Order.OrderViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeViewModel(val application: MyApplication): ViewModel() {

    val localRepository = application.getRepository()

    var list = MutableLiveData<List<Order>>()

    init {
        getAllOrder()
    }

    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                val  application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return HomeViewModel(application as MyApplication) as T
            }
        }
    }

    fun getAllOrder() {
        viewModelScope.launch {
            val result = localRepository.getAllEntity()
            list.postValue(result)
        }

    }

}