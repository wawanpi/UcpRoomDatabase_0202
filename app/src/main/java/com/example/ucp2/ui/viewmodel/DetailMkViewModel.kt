package com.example.ucp2.ui.viewmodel

import com.example.ucp2.data.entity.MataKuliah

fun MataKuliah.toDetailUiEvent(): MataKuliahEvent{
    return MataKuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}
