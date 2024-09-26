package com.example.buiquangtuan.data.localdatasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buiquangtuan.data.localdatasource.dao.DAO
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity

@Database(entities = [DataBaseEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun DAO() : DAO
    companion object {
        private var database: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
            }
            return database!!
        }
    }

}