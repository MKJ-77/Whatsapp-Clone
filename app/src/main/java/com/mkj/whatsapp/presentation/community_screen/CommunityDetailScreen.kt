package com.mkj.whatsapp.presentation.community_screen

import androidx.compose.foundation.Image
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
import com.mkj.whatsapp.model.CommunityGroupModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun CommunityDetailScreen() {

    val groups = listOf(
        CommunityGroupModel("Announcements", R.drawable.outline_account_circle_24, isAnnouncement = true),
        CommunityGroupModel("Android Discussions", R.drawable.img),
        CommunityGroupModel("Resources", R.drawable.baseline_invert_colors_24),
        CommunityGroupModel("Events", R.drawable.baseline_invert_colors_24)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Android Devs",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = Color(0xFF075E54)
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            item {
                CommunityHeader()
            }

            item {
                SectionTitle("Groups")
            }

            items(groups) { group ->
                CommunityGroupItem(group)
            }
        }
    }
}

@Composable
fun CommunityHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.communities_icon),
            contentDescription = "Community Icon",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Android Devs",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Community for Android developers",
            fontSize = 14.sp,
            color = Color.Gray
        )
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
fun CommunityGroupItem(group: CommunityGroupModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* open group chat */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(group.image),
            contentDescription = group.name,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = group.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            if (group.isAnnouncement) {
                Text(
                    text = "Only admins can send messages",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


