package com.mkj.whatsapp.model

import androidx.compose.ui.graphics.Color
import com.mkj.whatsapp.R

data class CallItem(
    val name: String,
    val image: Int,
    val time: String,
    val type: CallType
)

enum class CallType(val icon: Int, val color: Color) {
    INCOMING(R.drawable.communities_icon, Color(0xFF25D366)),
    OUTGOING(R.drawable.communities_icon, Color(0xFF25D366)),
    MISSED(R.drawable.baseline_call_missed_24, Color.Red)
}
