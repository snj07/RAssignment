package com.snj07.rassignment.utils.global

object Constants {
    const val PLAY_SERVICES_CHECK = 5000

    // API Related Constants
    val APPLICATION_JSON = "application/json"
    val HEADER_CONTENT_TYPE = "Content-Type"
    var BASE_URL = "https://my-json-server.typicode.com"
    const val DATA_URL = "iranjith4/ad-assignment/db"

    // Error Related Constants
    val NO_INTERNET_ERROR = 0
    val IO_ERROR = 1
    val SOCKET_TIMEOUT_ERROR = 2
    val OTHER_ERROR = 3

    // Sync Job Service Related Constant
//    val PLAY_SERVICES_CHECK = 5000
    val SYNC_TAG = "sync-job-service"
    val SERVICE_WINDOW_START = 86400
    val SERVICE_WINDOW_END = 90000
}