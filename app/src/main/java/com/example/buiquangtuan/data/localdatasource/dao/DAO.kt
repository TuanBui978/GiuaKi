package com.example.buiquangtuan.data.localdatasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity

@Dao
interface DAO {
    @Query("SELECT * FROM DataBaseEntity")
    suspend fun getAllEntity(): List<DataBaseEntity>
    @Insert
    suspend fun insertOrder(dataBaseEntity: DataBaseEntity)
    @Delete
    suspend fun deleteOrder(dataBaseEntity: DataBaseEntity)

    @Query("UPDATE DataBaseEntity SET status=:status WHERE id = :id")
    suspend fun update(status: String?, id: Int)
    @Query("SELECT * FROM DataBaseEntity WHERE price LIKE '%' || :price || '%'")
    suspend fun getByPrice(price: String): List<DataBaseEntity>
}