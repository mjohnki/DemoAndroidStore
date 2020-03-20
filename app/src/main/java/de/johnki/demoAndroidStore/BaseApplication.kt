package de.johnki.demoAndroidStore

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import de.johnki.demoAndroidStore.di.ApplicationModule
import de.johnki.demoAndroidStore.di.DaggerApplicationComponent
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this)).build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> =
        androidInjector

}
