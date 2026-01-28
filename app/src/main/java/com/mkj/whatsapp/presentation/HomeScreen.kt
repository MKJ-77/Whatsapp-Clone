package com.mkj.whatsapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {
    Scaffold(
        topBar = {},
        bottomBar = {},
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ChatList()
        }
    }
}


@Composable
fun ChatList() {
    LazyColumn() {
        items(10) {
            ChatListItem()
        }
    }
}

@Composable
fun ChatListItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(80.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ajay_devgn),
            contentDescription = "DP",
            contentScale = ContentScale.Crop,
            modifier = Modifier
//                .padding(horizontal = 8.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp)
                .weight(2f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(Modifier.padding(5.dp))
            Text(
                text = "Ajay Devgan",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(Modifier.padding(4.dp))
            Text(
                text = "Hello",
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
        Text(
            "10:00 am",
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W400,
            color = Color.Gray,
            modifier = Modifier.padding(top = 15.dp),
        )
    }
}
