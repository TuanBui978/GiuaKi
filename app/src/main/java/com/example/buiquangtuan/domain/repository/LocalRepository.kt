package com.example.buiquangtuan.domain.repository

import android.content.Context
import com.example.buiquangtuan.data.localdatasource.database.MyDatabase
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity
import com.example.buiquangtuan.domain.model.Order

class LocalRepository(private val context: Context) {
    suspend fun getAllEntity(): List<Order> {
        return MyDatabase.getInstance(context).DAO().getAllEntity().map { it.toOrder() }
    }

    suspend fun insertEntity(dataBaseEntity: DataBaseEntity) {
        MyDatabase.getInstance(context).DAO().insertOrder(dataBaseEntity)
    }

}