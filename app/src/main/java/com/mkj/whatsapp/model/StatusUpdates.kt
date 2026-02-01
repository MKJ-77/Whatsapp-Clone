package com.mkj.whatsapp.model

data class StatusUpdate(
    val name: String,
    val image: Int,
    val time: String,
    val statusCount: Int,
    val isViewed: Boolean
)
