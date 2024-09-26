package com.example.buiquangtuan.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity

data class Order(
    val id: Int? = null,
    val cream: Boolean,
    val chocolate: Boolean,
    val quantity: Int,
    val price: Float,
    val status: String
) {
    fun toDataBaseEntity():DataBaseEntity {
        return DataBaseEntity(id, cream, chocolate, quantity, price, status)
    }
}