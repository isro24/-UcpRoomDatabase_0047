package com.example.ucp2.ui.view.jadwal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.jadwal.DetailJadwalViewModel
import com.example.ucp2.ui.viewmodel.jadwal.DetailUiState
import com.example.ucp2.ui.viewmodel.jadwal.toJadwalEntity
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailJadwalView(
    modifier: Modifier = Modifier,
    viewModel: DetailJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
){
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    DisposableEffect(Unit) {
        onDispose {
            systemUiController.setStatusBarColor(Color.Transparent)
        }
    }
    Scaffold (
        topBar = {
            TopAppBar (
                judul = "Detail Jadwal",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(viewModel.detailUiState.value.detailUiEvent.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Jadwal"
                )
            }
        }
    ){  innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailJadwal(
            modifier = Modifier
                .padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteJdwl()
                onDeleteClick()
            }
        )
    }
}
@Composable
fun BodyDetailJadwal(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(26.dp)

            ) {
                ItemDetailJadwal(
                    jadwal = detailUiState.detailUiEvent.toJadwalEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { deleteConfirmationRequired = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0D61C4),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }

        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailJadwal(
    modifier: Modifier = Modifier,
    jadwal: Jadwal
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer

        )
    ){
        Column (
            modifier = Modifier.padding(20.dp)
        ){
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJadwal(judul = "Nama Dokter", isinya = jadwal.namaDokter, icon = Icons.Default.Person)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJadwal(judul = "NamaPasien", isinya = jadwal.namaPasien, icon = Icons.Default.Person)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJadwal(judul = "NoHp", isinya = jadwal.noHp, icon = Icons.Default.Call)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJadwal(judul = "TanggalKonsultasi", isinya = jadwal.tanggalKonsultasi, icon = Icons.Default.DateRange)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailJadwal(judul = "Status", isinya = jadwal.status, icon = Icons.Default.Info)
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }
}

@Composable
fun ComponentDetailJadwal(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "$judul:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(
                text = isinya,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {  },
        title = { Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data?")},
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = "Yes")
            }
        }
    )
}