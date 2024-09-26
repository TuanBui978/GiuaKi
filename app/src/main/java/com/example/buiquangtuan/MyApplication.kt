package com.example.buiquangtuan

import android.app.Application
import android.content.Context
import com.example.buiquangtuan.domain.repository.LocalRepository

class MyApplication: Application(){

    var localRepository: LocalRepository? = null

    fun getRepository(): LocalRepository {
        if (localRepository == null) {
            localRepository = LocalRepository(applicationContext)
        }
        return localRepository!!
    }



}