package com.example.ucp2.repository

import com.example.ucp2.data.dao.MataKuliahDao
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMk (
    private val mataKuliahDao: MataKuliahDao

): RepositoryMk {
    override suspend fun insertMk(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMataKuliah(mataKuliah)
    }

    override fun getAllMk(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMataKuliah()
    }

    override fun getMk(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getMataKuliah(kode)
    }

    override suspend fun deleteMk(matakuliah: MataKuliah) {
        mataKuliahDao.deleteMataKuliah(matakuliah)
    }

    override suspend fun updateMk(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMataKuliah(mataKuliah)
    }
}