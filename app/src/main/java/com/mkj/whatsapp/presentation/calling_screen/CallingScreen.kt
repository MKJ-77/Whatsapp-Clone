package com.mkj.whatsapp.presentation.calling_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.CallItem
import com.mkj.whatsapp.model.CallType
import com.mkj.whatsapp.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CallingScreen() {

    val calls = listOf(
        CallItem("Ajay Devgn", R.drawable.ajay_devgn, "Today, 10:30 AM", CallType.INCOMING),
        CallItem("Carry Minati", R.drawable.carryminati, "Yesterday, 9:12 PM", CallType.MISSED),
        CallItem("Bhuvan Bam", R.drawable.bhuvan_bam, "Yesterday, 8:45 PM", CallType.OUTGOING),
        CallItem("Rashmika", R.drawable.rashmika, "Yesterday, 6:20 PM", CallType.INCOMING)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Calls",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = Color(0xFF075E54)
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "More",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        bottomBar = ::BottomNavigation,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* start new call */ },
                containerColor = Color(0xFF075E54)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_call),
                    contentDescription = "New call",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            item {
                CreateCallLinkItem()
            }

            item {
                SectionTitle("Recent")
            }

            items(calls) { call ->
                CallRow(call)
            }
        }
    }
}

@Composable
fun CreateCallLinkItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* create call link */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF25D366)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_share_24),
                contentDescription = "Create call link",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "Create call link",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Share a link for your WhatsApp call",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun CallRow(call: CallItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* open call details */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(call.image),
            contentDescription = call.name,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = call.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(call.type.icon),
                    contentDescription = null,
                    tint = call.type.color,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = call.time,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Icon(
            painter = painterResource(R.drawable.telephone),
            contentDescription = "Call",
            tint = Color(0xFF075E54),
            modifier = Modifier.size(20.dp)
        )
    }
}



