package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.MataKuliah



data class MkFormErrorState(
    val kode: String? =null,
    val nama: String? =null,
    val sks: String? =null,
    val semester: String? =null,
    val jenis: String? =null,
    val dosenPengampu: String?=null,
){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenPengampu == null
    }
}

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)

//Menyimpan input form kedalam entity
fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)