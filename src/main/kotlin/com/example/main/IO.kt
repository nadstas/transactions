package com.example.main

fun interface Out {
    fun print(output: String)
}

fun interface In {
    fun readLine(): String?
}