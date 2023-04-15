package com.tamayo.jettodoapp.core.di

import com.google.firebase.auth.FirebaseAuth
import com.tamayo.jettodoapp.login.data.LoginRepository
import com.tamayo.jettodoapp.login.data.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): LoginRepository = LoginRepositoryImpl(firebaseAuth)
}