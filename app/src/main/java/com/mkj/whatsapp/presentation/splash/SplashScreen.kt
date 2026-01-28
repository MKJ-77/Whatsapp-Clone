package com.mkj.whatsapp.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.whatsapp.R

@Composable
@Preview(showBackground = true)
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.whatsapp_icon),
            contentDescription = "App Logo",
            tint = Color(0xFF5BCE12),
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                "From",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(Modifier.padding(6.dp)) {
                Icon(
                    painter = painterResource(R.drawable.meta),
                    contentDescription = "meta_Logo",
                    tint = Color(0xFF3D860F),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(Modifier.padding(4.dp))
                Text(
                    "Meta",
                    fontSize = 32.sp,
                    color = Color(0xFF3D860F),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}