package com.mkj.whatsapp.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.whatsapp.R

@Composable
@Preview(showSystemUi = true)
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.whatsapp_sticker2),
            contentDescription = "Whatsapp Sticker",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            tint = Color(0xFF4ABB18),

            )
        Spacer(Modifier.padding(20.dp))
        Text(text = "Welcome to Whatsapp", fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Spacer(Modifier.padding(14.dp))
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = buildAnnotatedString {

                    append("Read our ")

                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.whatsapp.com/legal/privacy-policy",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color(0xFF25D366), fontWeight = FontWeight.Medium
                                )
                            )
                        )
                    ) {
                        append("Privacy Policy")
                    }

                    append(". Tap \"Agree and continue\" to accept the ")

                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.whatsapp.com/legal/terms-of-service",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color(0xFF25D366), fontWeight = FontWeight.Medium
                                )
                            )
                        )
                    ) {
                        append("Terms of Service")
                    }
                },
                style = TextStyle(
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            )

        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A976F),
            ),
        ) {
            Text("Agree and continue", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        }

    }
}