package com.mkj.whatsapp.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mkj.whatsapp.R

@Composable
fun BottomNavigation(
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    BottomAppBar(
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavItem(
                modifier = Modifier.weight(1f),
                image = R.drawable.chat_icon,
                text = "Chats",
                selected = currentRoute == Screen.Chats.route
            ) {
                navController.navigate(Screen.Chats.route) {
                    launchSingleTop = true
                }
            }

            BottomNavItem(
                modifier = Modifier.weight(1f),
                image = R.drawable.update_icon,
                text = "Updates",
                selected = currentRoute == Screen.Updates.route
            ) {
                navController.navigate(Screen.Updates.route) {
                    launchSingleTop = true
                }
            }

            BottomNavItem(
                modifier = Modifier.weight(1f),
                image = R.drawable.communities_icon,
                text = "Communities",
                selected = currentRoute == Screen.Communities.route
            ) {
                navController.navigate(Screen.Communities.route) {
                    launchSingleTop = true
                }
            }

            BottomNavItem(
                modifier = Modifier.weight(1f),
                image = R.drawable.telephone,
                text = "Calls",
                selected = currentRoute == Screen.Calls.route
            ) {
                navController.navigate(Screen.Calls.route) {
                    launchSingleTop = true
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    image: Int,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val activeColor = Color(0xFF075E54)
    val inactiveColor = Color.Gray

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(image),
            contentDescription = text,
            tint = if (selected) activeColor else inactiveColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (selected) activeColor else inactiveColor
        )
    }
}
