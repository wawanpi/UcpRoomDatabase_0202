package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.ContainerApp

class KampusApp : Application() {
    lateinit var containerApp: ContainerApp //Fungsinya untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //Membuat instance dari ContainerApp
    }   //
}