package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.ContainerApp

class KampusApp : Application() {
    lateinit var containerApp: ContainerApp // fungsinya untuk menyimpan dependensi

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // Menginisialisasi ContainerApp dengan context aplikasi
    }
}