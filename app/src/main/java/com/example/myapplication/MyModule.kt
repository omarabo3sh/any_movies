package com.example.myapplication

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.MyDao
import com.example.myapplication.data.local.MyDataBase
import com.example.myapplication.data.nework.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MyModule {

    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient {   // create an instance of OkHttpClient
        return OkHttpClient.Builder().addInterceptor { chain -> // add an interceptor to the OkHttpClient
            val original = chain.request() // get the original request
            val requestWithHeader = original.newBuilder() // create a new request
                .header(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZDQwY2ZlYzgyYjg3N2UxNGIwY2M1OWVmZjE3YzlmOCIsIm5iZiI6MTczMjI0NTQ5Mi4yMzIsInN1YiI6IjY3M2ZmN2Y0OGI0ZTRjMmVmNmY3N2JmYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-mha3xT45Md2H7_nRpm96A2c4KLrJM648Yl2HCd-UvI"
                )
                .build() // add the authorization header
            chain.proceed(requestWithHeader) // proceed with the request
        }.build() // build the OkHttpClient instance

    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit { // create a retrofit instance
        return Retrofit.Builder() // start building the retrofit instance
            .client(okHttpClient) // set the OkHttpClient instance
            .baseUrl("https://api.themoviedb.org/3/") // base URL of the API
            .addConverterFactory(GsonConverterFactory.create()) // add the Gson converter factory
            .build() // build the retrofit instance
    }

    @Singleton
    @Provides
    fun getApiService(retrofit: Retrofit): ApiService { // create an instance of the ApiService
        return retrofit.create(ApiService::class.java) // create the ApiService from the retrofit instance
    }

    @Singleton
    @Provides
    fun getMyDatabase(@ApplicationContext context: Context): MyDataBase { // create an instance of the database
        return Room.databaseBuilder(context, MyDataBase::class.java, "contacts") // name of the database
            .allowMainThreadQueries() // allow main thread queries
            .fallbackToDestructiveMigration(false) // allow destructive migration
            .build() // build the database
    }

    @Singleton
    @Provides
    fun getDao(myDataBase: MyDataBase): MyDao { // create an instance of the DAO
        return myDataBase.getDao() // get the DAO from the database
    }

}