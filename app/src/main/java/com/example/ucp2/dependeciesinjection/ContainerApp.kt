package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.data.database.KampusDatabase
import com.example.ucp2.repository.LocalRepositoryDsn
import com.example.ucp2.repository.LocalRepositoryMk
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMk

interface InterfaceContainerApp {
    val repositoryDsn: RepositoryDsn
    val repositoryMk: RepositoryMk
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepositoryDsn(KampusDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(KampusDatabase.getDatabase(context).mataKuliahDao())
    }
}