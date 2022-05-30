package com.fairfaxmedia.newsapiclient.data.model

data class RelatedImage(
    val assetType: String,
    val description: String,
    val height: Int,
    val id: Int,
    val lastModified: Long,
    val photographer: String,
    val sponsored: Boolean,
    val timeStamp: Long,
    val type: String,
    val url: String,
    val width: Int
)