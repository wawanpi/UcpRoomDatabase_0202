package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.costumwidget.CustomTopAppBar
import com.example.ucp2.ui.viewmodel.HomeDsnViewModel
import com.example.ucp2.ui.viewmodel.HomeUiState
import com.example.ucp2.ui.viewmodel.PenyediaMkViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeDsnView(
    viewModel : HomeDsnViewModel = viewModel(factory = PenyediaMkViewModel.Factory),
    onAddDsn: () -> Unit = { },
    onBack: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier,
){
    Scaffold (
        topBar = {
            CustomTopAppBar (
                judul = "Daftar Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDsn,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,  // Set background color for Scaffold
    ) { innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDsnView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)  // Set background for body
        )
    }
}

@Composable
fun BodyHomeDsnView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface) // Background color for content
    ) {
        when {
            homeUiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            homeUiState.isError -> {
                LaunchedEffect(homeUiState.errorMessage) {
                    homeUiState.errorMessage?.let { message ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
            homeUiState.listDsn.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Tidak ada data Dosen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            else -> {
                ListDosen(
                    listDsn = homeUiState.listDsn,
                    onClick = {
                        onClick(it)
                    },
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ListDosen(
    listDsn: List<Dosen>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            items = listDsn,
            itemContent = { dsn ->
                CardDsn(
                    dsn = dsn,
                    onClick = {
                        onClick(dsn.nidn)
                    },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        )
    }
}

@Composable
fun CardDsn(
    dsn: Dosen,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(Color(0xFF00BFFF))    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = dsn.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = dsn.nidn,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = dsn.jenisKelamin,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
