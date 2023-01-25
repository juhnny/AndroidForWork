package com.example.tp01driver.common

object Constants {
    private val IS_DEV = true

    val REAL_DOMAIN = "api.pildservice.com"

    val TEST_DOMAIN = "devapi.pildservice.com:8081"

    val httpProtocol = if(IS_DEV) "http://" else "https://"

    val domainName = if(IS_DEV) TEST_DOMAIN else REAL_DOMAIN

    val baseUrl = httpProtocol + domainName
}