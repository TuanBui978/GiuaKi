package com.example.buiquangtuan.data.localdatasource.entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buiquangtuan.domain.model.Order


@Entity
data class DataBaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "cream") val cream: Boolean,
    @ColumnInfo(name = "chocolate") val chocolate: Boolean,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo("price") val price: Float,
    @ColumnInfo(name = "status") val status: String
) {
    fun toOrder(): Order {
        return Order(id, cream, chocolate, quantity, price, status);
    }
}
