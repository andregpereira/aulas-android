package com.andre.checkpoint04.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val ARTEFATO_DATABASE_NAME = "artefato_database"

@Database(entities = [ArtefatoInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun artefatoInfoDao(): ArtefatoInfoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    ARTEFATO_DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}