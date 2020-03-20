package de.johnki.demoAndroidStore.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import de.johnki.demoAndroidStore.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        RestaurantModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: BaseApplication)
}
