package com.example.session2.Models

data class Response <T>(
    var message:String,
    var data: T
    )