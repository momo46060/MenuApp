package com.example.myapplication.app.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.AppDatabase
import com.example.myapplication.data.local.MenuDao
import com.example.myapplication.data.remote.services.MenuService
import com.example.myapplication.data.repository.MenuRepositoryImpl
import com.example.myapplication.domain.repository.MenuRepository
import com.example.myapplication.domain.usecase.GetMenuUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.jsonbin.io/v3/b/6882874df7e7a370d1ed6cc1/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "X-Master-Key",
                    "\$2a\$10\$gKGk.WhhPDqDbokEJkqkUucnE66bIZSSTGM31bo6SUkYjYbRD2Hf6"
                )
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuService(retrofit: Retrofit): MenuService {
        return retrofit.create(MenuService::class.java)
    }

    @Provides
    @Singleton
    fun bindMenuRepository(menuRepositoryImpl: MenuRepositoryImpl): MenuRepository {
        return menuRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGetMenuUseCase(menuRepository: MenuRepository): GetMenuUseCase {
        return GetMenuUseCase(menuRepository)
    }


    @Provides @Singleton fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "menu_db").build()
    @Provides fun provideMenuDao(db: AppDatabase): MenuDao = db.menuDao()

}