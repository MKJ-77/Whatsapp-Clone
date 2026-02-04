package com.mkj.whatsapp.presentation.calling_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavHostController
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.CallItem
import com.mkj.whatsapp.model.CallType
import com.mkj.whatsapp.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallingScreen(navController: NavHostController) {

    val favorites = listOf(
        CallItem("MrBeast", R.drawable.mrbeast, "", CallType.OUTGOING),
        CallItem("Bhuvan Bam", R.drawable.bhuvan_bam, "", CallType.OUTGOING),
        CallItem("Tripti Dimri", R.drawable.tripti_dimri, "", CallType.OUTGOING),
        CallItem("Akshay Kumar", R.drawable.akshay_kumar, "", CallType.OUTGOING)
    )

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
                onClick = {},
                containerColor = Color(0xFF075E54)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_call),
                    contentDescription = "New Call",
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
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {

            item { FavoritesSection(favorites) }

            item { CreateCallLinkItem() }

            item { SectionTitle("Recent") }

            items(calls) { call ->
                CallRow(call)
            }
        }
    }
}

/* -------------------- FAVORITES -------------------- */

@Composable
fun FavoritesSection(favorites: List<CallItem>) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Favorites",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favorites) { fav ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(fav.image),
                        contentDescription = fav.name,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = fav.name,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

/* -------------------- CREATE CALL LINK -------------------- */

@Composable
fun CreateCallLinkItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
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

/* -------------------- CALL ROW -------------------- */

@Composable
fun CallRow(call: CallItem) {

    val timeColor =
        if (call.type == CallType.MISSED) Color.Red else Color.Gray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
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
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
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
                    color = timeColor
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

/* -------------------- PREVIEW -------------------- */

@Preview(showBackground = true)
@Composable
fun CallingScreenPreview() {
    CallingScreen(navController)
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
