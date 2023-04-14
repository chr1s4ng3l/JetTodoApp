package com.tamayo.jettodoapp.addtask.data.di

import android.content.Context
import androidx.room.Room
import com.tamayo.jettodoapp.addtask.data.EventsDatabase
import com.tamayo.jettodoapp.addtask.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesTaskDao(eventsDatabase: EventsDatabase): TaskDao{
        return eventsDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): EventsDatabase {
        return Room.databaseBuilder(appContext, EventsDatabase::class.java, "EventsDatabase")
            .build()
    }
}