package com.example.ucp2.repository

import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMk {
    suspend fun insertMk(mataKuliah: MataKuliah)

    fun getAllMk(): Flow<List<MataKuliah>>
    fun getMk(kode: String): Flow<MataKuliah>
    suspend fun deleteMk(mataKuliah: MataKuliah)
    suspend fun updateMk(mataKuliah: MataKuliah)
}