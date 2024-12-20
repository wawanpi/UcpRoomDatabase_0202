package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.Dosen

//data class variabel yang menyimpan data input form
data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)

//Menyimpan input form kedalam entity
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin,

)