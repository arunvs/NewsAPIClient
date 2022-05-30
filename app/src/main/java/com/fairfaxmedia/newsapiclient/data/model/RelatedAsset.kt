package com.fairfaxmedia.newsapiclient.data.model

data class RelatedAsset(
    val assetType: String,
    val authors: List<Author>,
    val categories: List<Category>,
    val headline: String,
    val id: Int,
    val lastModified: Long,
    val onTime: Long,
    val sponsored: Boolean,
    val timeStamp: Long,
    val url: String
)