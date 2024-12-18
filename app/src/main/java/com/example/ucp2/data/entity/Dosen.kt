package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosen")
data class Dosen (
    @PrimaryKey
    val nidn: String,
    val nama: String,
    val jenisKelamin: String,
)