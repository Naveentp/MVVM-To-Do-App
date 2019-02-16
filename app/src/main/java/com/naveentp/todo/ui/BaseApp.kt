package com.naveentp.todo.ui

import android.app.Application
import com.naveentp.todo.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

/**
 * @author Naveen T P
 * @since 03/02/19
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initialising Koin
        startKoin {
            androidContext(this@BaseApp)
            modules(applicationModule)
            androidFileProperties()
        }
    }
}