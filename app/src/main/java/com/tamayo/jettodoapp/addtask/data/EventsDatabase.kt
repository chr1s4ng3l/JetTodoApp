package com.tamayo.jettodoapp.addtask.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class] , version = 1)
abstract class EventsDatabase: RoomDatabase() {
    //Dao

    abstract fun taskDao():TaskDao
}