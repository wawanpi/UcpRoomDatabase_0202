package com.example.ucp2.dependeciesinjection

import com.example.ucp2.repository.RepositoryDsn

interface InterfaceContainerApp {
    val repositoryDsn: RepositoryDsn
}