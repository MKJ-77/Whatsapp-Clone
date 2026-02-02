package com.mkj.whatsapp.model

data class CommunityGroupModel(
    val name: String,
    val image: Int,
    val isAnnouncement: Boolean = false
)