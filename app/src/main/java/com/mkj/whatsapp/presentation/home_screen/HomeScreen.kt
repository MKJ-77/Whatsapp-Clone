package com.mkj.whatsapp.presentation.home_screen

import WhatsAppTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.ChatDataModel
import com.mkj.whatsapp.presentation.navigation.BottomNavigation
import com.mkj.whatsapp.presentation.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val chatList = remember {
        listOf(
            ChatDataModel(R.drawable.ajay_devgn, "Ajay Devgan", "HI", "9:00"),
            ChatDataModel(
                R.drawable.carryminati,
                "Carry Minati",
                "HI This is Carry Minati, How are you doing. Mr Keshav",
                "9:00"
            ),
            ChatDataModel(R.drawable.akshay_kumar, "Aditya Krishna Sharma", "HI", "9:00"),
            ChatDataModel(R.drawable.bhuvan_bam, "BB", "HI", "9:00"),
            ChatDataModel(R.drawable.boy, "Arnav", "HI", "9:00"),
            ChatDataModel(R.drawable.boy1, "Yavar", "HI", "9:00"),
            ChatDataModel(R.drawable.boy3, "Rupesh", "HI", "9:00"),
            ChatDataModel(R.drawable.rashmika, "Rashmika Mandhana", "HI", "9:00"),
            ChatDataModel(R.drawable.salman_khan, "Salman Khan", "HI", "9:00"),
            ChatDataModel(R.drawable.sharukh_khan, "Shahrukh Khan", "HI", "9:00"),
            ChatDataModel(R.drawable.sharadha_kapoor, "Shraddha Kapoor", "HI", "9:00"),
            ChatDataModel(R.drawable.mrbeast, "Mr Beast", "HI", "9:00"),
            ChatDataModel(R.drawable.hrithik_roshan, "Hrithik Roshan", "HI", "9:00"),
            ChatDataModel(R.drawable.tripti_dimri, "Tripti Dimri", "HI", "9:00"),
        )
    }

    Scaffold(
        topBar = { WhatsAppTopAppBar() },
        bottomBar = { BottomNavigation(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* New chat */ },
                containerColor = Color(0xFF69C950)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_chat_icon),
                    contentDescription = "New Chat",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
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
            item { HorizontalDivider() }

            items(chatList) { chat ->
                ChatListItem(chat) {
                    navController.navigate(
                        Routes.ChatDetail.createRoute(chat.name)
                    )
                }
            }
        }
    }
}
@Composable
fun ChatListItem(
    chatData: ChatDataModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .heightIn(min=72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(chatData.image),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = chatData.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chatData.message,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = chatData.time,
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
