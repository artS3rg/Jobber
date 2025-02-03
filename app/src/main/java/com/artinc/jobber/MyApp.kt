package com.artinc.jobber

import android.app.Application
import com.artinc.data.dataModule
import com.artinc.domain.domainModule
import com.artinc.presentation.presentationModule
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