package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch

class DosenViewModel (private val repositoryDsn: RepositoryDsn) : ViewModel(){

    var uiState by mutableStateOf(DsnUiState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }

    //Validasi input nama pengguna
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = DsnFormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "Nidn tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "jenisKelamin tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //menyimpan data ke repository
    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if(validateFields()) {
            viewModelScope.launch {
                try{
                    repositoryDsn.insertDsn(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        dosenEvent = DosenEvent(), //reset input form
                        isEntryValid = DsnFormErrorState() // reset error state
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

data class DsnUiState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: DsnFormErrorState = DsnFormErrorState(),
    val snackBarMessage: String? = null,
)

data class DsnFormErrorState(
    val nidn: String? =null,
    val nama: String? =null,
    val jenisKelamin: String? =null,
){
    fun isValid(): Boolean {
        return nidn == null && nama == null && jenisKelamin == null
    }
}


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