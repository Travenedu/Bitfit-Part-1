package com.example.bitfitpart1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HealthMetricEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun HealthMetricDao(): HealthMetricDao

    companion object {
        private const val DATABASE_NAME = "health_metrics"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}