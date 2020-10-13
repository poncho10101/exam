package com.alfonsosotelo.examtestingapp.di

import android.app.Application
import android.content.Context
import com.alfonsosotelo.examtestingapp.repository.remote.utils.NetworkConstants
import com.alfonsosotelo.examtestingapp.repository.remote.utils.Webservice
import com.alfonsosotelo.examtestingapp.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideWebservice(): Webservice {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.SERVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
            .create(Webservice::class.java)
    }

}