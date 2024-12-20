package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMk
import kotlinx.coroutines.launch

class MataKuliahViewModel (private val repositoryMk: RepositoryMk) : ViewModel(){

    var uiState by mutableStateOf(MkUiState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(mataKuliahEvent: MataKuliahEvent){
        uiState = uiState.copy(
            mataKuliahEvent = MataKuliahEvent(),
        )
    }

    //Validasi input nama pengguna
    private fun validateFields(): Boolean {
        val event = uiState.mataKuliahEvent
        val errorState = MkFormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "kelas tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen pengampu tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.mataKuliahEvent
        if(validateFields()) {
            viewModelScope.launch {
                try{
                    repositoryMk.insertMk(currentEvent.toMataKuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        mataKuliahEvent = MataKuliahEvent(), //reset input form
                        isEntryValid = MkFormErrorState() // reset error state
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal disimpamn"
                    )
                }
            }
        }else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak Valid, Periksa kembali data Anda."
            )
        }
    }

    //reset pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MkUiState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: MkFormErrorState = MkFormErrorState(),
    val snackBarMessage: String? = null,
)

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