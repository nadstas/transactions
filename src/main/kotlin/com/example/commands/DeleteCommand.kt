package com.example.commands

import com.example.main.State
import java.util.*

class DeleteCommand(private val key: String) : MutatorCommand {
    private var oldValue: Optional<String>? = null

    override fun apply(state: State) {
        oldValue = Optional.ofNullable(state[key])
        state.remove(key)
    }

    override fun undo(state: State) {
        oldValue?.ifPresent { state[key] = it }
    }
}