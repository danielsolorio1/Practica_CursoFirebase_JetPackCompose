package com.example.cursofirebaselite

import android.app.Application
import android.content.Context

class CursoFireBaseApp : Application() {

    companion object{
         lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}