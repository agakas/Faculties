package com.example.facultiesapp

import android.app.Application
import android.content.Context
import com.example.facultiesapp.database.DatabaseProvider
import com.example.facultiesapp.network.NetworkProvider

class ApplicationForFaculties: Application() {
    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        //подключение бд в приложение
        DatabaseProvider.init(this)
        //подключение сети в приложение
        NetworkProvider.init(this)
    }
    companion object {
        private lateinit var instance: ApplicationForFaculties
        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}