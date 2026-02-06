package com.mkj.whatsapp.presentation.home_screen

import TopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.ChatDataModel
import com.mkj.whatsapp.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = ::TopAppBar,
        bottomBar = ::BottomNavigation,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}, modifier = Modifier.padding(10.dp), containerColor = Color(0xFF69C950)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_chat_icon),
                    contentDescription = "Message_Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(30.dp)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            HorizontalDivider()
            ChatList()
        }
    }
}


@Composable
fun ChatList() {
    val chatList = listOf<ChatDataModel>(
        ChatDataModel(R.drawable.ajay_devgn, "Ajay Devgan", "HI", "9:00"),
        ChatDataModel(
            R.drawable.carryminati,
            "Carry Minati",
            "HI This is Carry Minati, How are you doing. Mr Keshav ",
            "9:00"
        ),
        ChatDataModel(R.drawable.akshay_kumar, "Aditaya Krishna Sharma", "HI", "9:00"),
        ChatDataModel(R.drawable.bhuvan_bam, "BB", "HI", "9:00"),
        ChatDataModel(R.drawable.boy, "Arnav", "HI", "9:00"),
        ChatDataModel(R.drawable.boy1, "Yavar", "HI", "9:00"),
        ChatDataModel(R.drawable.boy3, "Rupesh", "HI", "9:00"),
        ChatDataModel(R.drawable.rashmika, "Rashmika Mandhana", "HI", "9:00"),
        ChatDataModel(R.drawable.salman_khan, "Salman Khan", "HI", "9:00"),
        ChatDataModel(R.drawable.sharukh_khan, "Shahrukh Khan", "HI", "9:00"),
        ChatDataModel(R.drawable.sharadha_kapoor, "Shradhha Kapoor", "HI", "9:00"),
        ChatDataModel(R.drawable.mrbeast, "Mr Beast", "HI", "9:00"),
        ChatDataModel(R.drawable.hrithik_roshan, "Hrithik Roshan", "HI", "9:00"),
        ChatDataModel(R.drawable.tripti_dimri, "Tripti Dimri", "HI", "9:00"),
    )
    LazyColumn {
        items(chatList) {
            ChatListItem(chatData = it)
        }
    }
}

@Composable
fun ChatListItem(chatData: ChatDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(72.dp)
    ) {
        Image(
            painter = painterResource(chatData.image),
            contentDescription = "DP",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(Modifier.padding(5.dp))
            Text(
                text = chatData.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(Modifier.padding(4.dp))
            Text(
                text = chatData.message,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                chatData.time,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W400,
                color = Color.Gray,
            )
        }
    }
}
