package com.example.myapplication.di

import com.example.myapplication.domain.repository.AppointmentRepository
import com.example.myapplication.domain.repository.AppointmentRepositoryImpl
import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        database: FirebaseFirestore,
        auth: FirebaseAuth,
        gson: Gson
    ): AuthRepository {
        return AuthRepositoryImpl(auth, database, gson)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(
        database: FirebaseFirestore,
    ): AppointmentRepository {
        return AppointmentRepositoryImpl(database)
    }
}