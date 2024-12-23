package com.example.ucp2.ui.costumwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun CustomTopAppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
    modifier: Modifier = Modifier
) {
    // Tampilan atas dengan warna latar belakang #87CEFA (Light Sky Blue)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp) // Atur tinggi app bar
            .background(Color(0xFF00BFFF)) // Gunakan warna Light Sky Blue
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Jika showBackButton true, tampilkan tombol kembali
        if (showBackButton) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color.Black // Menyesuaikan warna ikon dengan latar belakang biru muda
                )
            }
        }

        // Teks judul dengan gaya modern
        Text(
            text = judul,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black, // Menyesuaikan warna teks dengan latar belakang biru muda
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
