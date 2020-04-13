package com.puzzlebench.clean_marvel_kotlin.presentation.base

import android.app.Application
import io.realm.Realm

class BaseRealm() : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}