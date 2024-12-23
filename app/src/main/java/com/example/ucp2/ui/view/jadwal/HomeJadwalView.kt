package com.example.ucp2.ui.view.jadwal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.jadwal.HomeJadwalViewModel

@Composable
fun HomeJadwalView(
    viewModel: HomeJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddJadwal: () -> Unit,
    onKembali: () -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        HeaderSection()
        BodySection(
            viewModel = viewModel,
            onAddJadwal = onAddJadwal,
            onKembali = onKembali,
            onDetailClick = onDetailClick
        )
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF0E91F3))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Text(
                        text = "Welcome, ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Klinik Sehatmu",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier.padding(end = 15.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun BodySection(
    viewModel: HomeJadwalViewModel,
    onAddJadwal: () -> Unit,
    onKembali: () -> Unit,
    onDetailClick: (String) -> Unit = {}
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = "Chat Icon",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Chat Dokter di Klinik Sehatmu",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Layanan telemedisin yang siap siaga untuk bantu kamu hidup lebih sehat",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Cari dokter",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onAddJadwal,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0D61C4),
                        contentColor = Color.White
                    )
                ) {
                    Text("Tambah Jadwal")
                }
                Button(
                    onClick = onKembali,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0D61C4),
                        contentColor = Color.White
                    )
                ) {
                    Text("Kembali")
                }
            }
        }
    }

    if (homeUiState.listJadwal.isEmpty()) {
        EmptyState()
    } else {
        ListJadwal(
            listJadwal = homeUiState.listJadwal,
            onClick = onDetailClick
        )
    }
}

@Composable
fun ListJadwal(
    listJadwal: List<Jadwal>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = listJadwal,
            itemContent = { jdwl ->
                CardJadwal(
                    jadwal = jdwl,
                    onClick = { onClick(jdwl.id.toString()) }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardJadwal(
    jadwal: Jadwal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Dokter Icon",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Column {
                        Text(
                            text = jadwal.namaPasien,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = jadwal.status,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Row {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Tanggal Konsultasi",
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 6.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = jadwal.tanggalKonsultasi,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Call,
                                contentDescription = "No Hp",
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 6.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = jadwal.noHp,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tidak ada data jadwal.",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
