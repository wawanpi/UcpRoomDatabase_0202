package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun HomeView(
    onDosenButton: () -> Unit,
    onMataKuliahButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEFF4E9), // Hijau muda
                        Color(0xFF95A263) // Hijau utama
                    )
                )
            )
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = " Universitas Muhammadiyah \nYogyakarta",
                color = Color(0xFF932520),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .size(220.dp)
                    .shadow(4.dp, RoundedCornerShape(40.dp), ambientColor = Color(0xFF95A263)) // Shadow hijau
            ) {
                Image(
                    painter = painterResource(id = R.drawable.umy),
                    contentDescription = "Logo UI",
                    modifier = Modifier.fillMaxSize()
                )

            }
        }

        Text(
            text = "Selamat Datang ",
            color = Color(0xFF932520),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        Text(
            text = " Pengelolaa  ",
            color = Color(0xFF932520),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        Text(
            text = " Dosen dan Mata Kuliah  ",
            color = Color(0xFF932520),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Buttons Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GradientButton(
                    text = "Dosen",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF95A263), // Hijau utama
                            Color(0xFFD4E1B0)  // Hijau lebih terang
                        )
                    ),
                    textColor = Color.White,
                    onClick = onDosenButton
                )

                Spacer(modifier = Modifier.height(17.dp))

                GradientButton(
                    text = "Mata Kuliah",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF95A263), // Hijau utama
                            Color.White      // Warna lebih terang
                        )
                    ),
                    textColor = Color(0xFF95A263),
                    onClick = onMataKuliahButton
                )
            }
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    textColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .background(brush = gradient, shape = RoundedCornerShape(16.dp))
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}



// Extensions untuk Klik
fun Modifier.onPointerEnter(onEnter: () -> Unit): Modifier = this.then(
    Modifier.pointerInput(Unit) {
        detectTapGestures(
            onTap = { onEnter() }
        )
    }
)

fun Modifier.onPointerExit(onExit: () -> Unit): Modifier = this.then(
    Modifier.pointerInput(Unit) {
        detectTapGestures(
            onTap = { onExit() }
        )
    }
)
