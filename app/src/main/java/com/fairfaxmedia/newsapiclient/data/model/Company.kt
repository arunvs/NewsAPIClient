package com.fairfaxmedia.newsapiclient.data.model

data class Company(
    val abbreviatedName: String,
    val companyCode: String,
    val companyName: String,
    val companyNumber: String,
    val exchange: String,
    val id: Int
)