package com.fairfaxmedia.newsapiclient.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "asset"
)

data class Asset(
    val acceptComments: Boolean,
    val assetType: String,
    val byLine: String,
    val headline: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val indexHeadline: String,
    val lastModified: Long,
    val legalStatus: String,
    val numberOfComments: Int,
    val onTime: Long,
    val relatedImages: List<RelatedImage>,
    val sponsored: Boolean,
    val tabletHeadline: String,
    val theAbstract: String,
    val timeStamp: Long,
    val url: String
): Serializable