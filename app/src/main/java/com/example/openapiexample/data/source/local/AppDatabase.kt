package com.example.openapiexample.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openapiexample.data.models.PostEntity
import com.example.openapiexample.data.source.local.daos.PostDao

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

}