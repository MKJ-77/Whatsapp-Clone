package com.mkj.whatsapp.presentation.community_screen


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
import androidx.navigation.NavHostController
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.CommunityModel
import com.mkj.whatsapp.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavHostController) {

    val communities = listOf(
        CommunityModel("Android Devs", R.drawable.img, "3 groups · Today"),
        CommunityModel("College Notices", R.drawable.neat_roots, "5 groups · Yesterday"),
        CommunityModel("Family", R.drawable.meta, "2 groups · Today")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Communities",
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
        bottomBar = { BottomNavigation(navController) },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            item {
                NewCommunityCard()
            }

            items(communities) { community ->
                CommunityItem(community)
            }
        }
    }
}

@Composable
fun NewCommunityCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* create community */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFECECEC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_groups_24),
                contentDescription = "New Community",
                tint = Color(0xFF075E54),
                modifier = Modifier.size(28.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "New community",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Create a community to stay connected",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CommunityItem(community: CommunityModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* open community */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(community.image),
            contentDescription = community.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = community.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = community.subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
