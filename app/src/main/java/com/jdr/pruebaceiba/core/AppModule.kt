package com.jdr.pruebaceiba.core

import android.content.Context
import androidx.room.Room
import com.jdr.pruebaceiba.data.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit) : ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context)=
        Room.databaseBuilder(
            app,
            Database::class.java,
            "PRUEBA_CEIBA_DATABASE"
        ).build()


    @Singleton
    @Provides
    fun provideUserDAO(db: Database) = db.userDao()

}