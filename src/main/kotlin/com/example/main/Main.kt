package com.example.main

fun main() {
    println(Strings.HELP)

    val input = In {
        print(" > ")
        readLine()
    }

    CommandProcessor(input, ::println)
        .start()
}
