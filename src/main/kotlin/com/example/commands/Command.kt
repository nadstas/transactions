package com.example.commands

import com.example.main.State

fun interface Command {
    fun apply(state: State)
}

interface MutatorCommand : Command {
    fun undo(state: State)
}
