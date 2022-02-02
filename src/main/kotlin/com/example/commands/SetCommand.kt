package com.example.commands

import com.example.main.State
import java.util.*

class SetCommand(private val key: String, private val value: String) : MutatorCommand {
    private var oldValue: Optional<String>? = null

    override fun apply(state: State) {
        oldValue = Optional.ofNullable(state[key])
        state[key] = value
    }

    override fun undo(state: State) {
        oldValue?.ifPresentOrElse(
            { state[key] = it },
            { state.remove(key) },
        )
    }
}
