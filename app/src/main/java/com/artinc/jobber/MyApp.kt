package com.artinc.jobber

import android.app.Application
import com.artinc.jobber.di.dataModule
import com.artinc.jobber.di.domainModule
import com.artinc.jobber.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}