package de.johnki.demoAndroidStore.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import de.johnki.demoAndroidStore.BaseApplication
import de.johnki.demoAndroidStore.MainActivity
import de.johnki.demoAndroidStore.repository.db.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApplicationModule.Binding::class])
class ApplicationModule(private val baseApplication: BaseApplication) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-service-trial-day.herokuapp.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideDataBase() =
        Room.databaseBuilder(
            baseApplication,
            AppDatabase::class.java, "app-db"
        ).build()


    @Module
    abstract class Binding {
        @ContributesAndroidInjector
        abstract fun contributeMainActivity(): MainActivity
    }
}
