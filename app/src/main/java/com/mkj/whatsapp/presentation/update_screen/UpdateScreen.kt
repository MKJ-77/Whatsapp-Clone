package com.mkj.whatsapp.presentation.update_screen

import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkj.whatsapp.R
import com.mkj.whatsapp.model.ChannelModel
import com.mkj.whatsapp.model.StatusUpdate
import com.mkj.whatsapp.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun UpdateScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val channels = listOf(
        ChannelModel("WhatsApp", R.drawable.whatsapp_icon),
        ChannelModel("Meta", R.drawable.meta),
        ChannelModel("Tech News", R.drawable.img),
        ChannelModel("NeetRoots", R.drawable.neat_roots)
    )
    val recentUpdates = listOf(
        StatusUpdate("Ajay", R.drawable.ajay_devgn, "10 min ago", 3, false),
        StatusUpdate("Carry", R.drawable.carryminati, "20 min ago", 2, false)
    )

    val viewedUpdates = listOf(
        StatusUpdate("Bhuvan", R.drawable.bhuvan_bam, "Yesterday", 1, true),
        StatusUpdate("Rashmika", R.drawable.rashmika, "Yesterday", 4, true)
    )



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Updates",
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
                            painter = painterResource(R.drawable.camera),
                            contentDescription = "Camera",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {
                        expanded = !expanded
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "More",
                            modifier = Modifier.size(24.dp)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Status privacy") },
                                onClick = { /* navigate */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Create channel") },
                                onClick = { /* navigate */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = { /* navigate */ }
                            )
                        }

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
                    painter = painterResource(R.drawable.baseline_photo_camera_24),
                    contentDescription = "Add status",
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
                Text(
                    text = "Status",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 8.dp)
                )
            }

            item {
                MyStatusRow() // extract your "My status" row into a composable
            }

            item {
                RecentUpdatesSection(recentUpdates)
            }

            item {
                ViewedUpdatesSection(viewedUpdates)
            }

            item {
                ChannelsSection(channels)
            }

        }
    }
}

@Composable
private fun MyStatusRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            Image(
                painter = painterResource(R.drawable.man),
                contentDescription = "My status",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )

            // Add badge
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = Color(0xFF25D366),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = "My status",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Tap to add status update",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ChannelsSection(channels: List<ChannelModel>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Channels",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Explore",
                fontSize = 14.sp,
                color = Color(0xFF25D366)
            )
        }

        // Subtitle
        Text(
            text = "Stay updated on topics you care about",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Horizontal list
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(channels) { channel ->
                ChannelItem(channel)
            }
        }
    }
}

@Composable
fun ChannelItem(channel: ChannelModel) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(channel.image),
            contentDescription = channel.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = channel.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Follow",
            fontSize = 13.sp,
            color = Color(0xFF075E54),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun RecentUpdatesSection(updates: List<StatusUpdate>) {
    Column {
        Text(
            text = "Recent updates",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        updates.forEach { update ->
            StatusItem(update)
        }
    }
}

@Composable
fun ViewedUpdatesSection(updates: List<StatusUpdate>) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Viewed updates",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        updates.forEach { update ->
            StatusItem(update)
        }
    }
}

@Composable
fun StatusItem(
    update: StatusUpdate,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SegmentedStatusRing(
            image = update.image,
            segments = update.statusCount,
            viewed = update.isViewed
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = update.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = update.time,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SegmentedStatusRing(
    image: Int,
    segments: Int,
    viewed: Boolean
) {
    val ringColor = if (viewed) Color.LightGray else Color(0xFF25D366)
    if (segments <= 0) return
    Box(contentAlignment = Alignment.Center) {

        Canvas(modifier = Modifier.size(58.dp)) {
            val sweep = 360f / segments
            val gap = 6f

            repeat(segments) { index ->
                drawArc(
                    color = ringColor,
                    startAngle = (index * sweep) + gap,
                    sweepAngle = sweep - gap,
                    useCenter = false,
                    style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }

        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun StatusViewerScreen(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

