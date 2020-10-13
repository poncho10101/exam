package com.alfonsosotelo.examtestingapp

import com.alfonsosotelo.examtestingapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApp: DaggerApplication() {
    private var androidInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector

}