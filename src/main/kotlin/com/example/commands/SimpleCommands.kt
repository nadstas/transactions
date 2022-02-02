package com.example.commands

import com.example.main.Out
import com.example.main.Strings.HELP
import com.example.main.Strings.KEY_NOT_SET
import com.example.main.Strings.UNKNOWN_COMMAND
import com.example.main.Strings.WRONG_ARGS_MESSAGE

object SimpleCommands {

    val empty = Command { }

    fun wrongAgrs(output: Out) =
        Command { output.print(WRONG_ARGS_MESSAGE) }

    fun count(value: String, output: Out) =
        Command { output.print("Keys with given values = ${it.count(value)}") }

    fun get(key: String, output: Out) =
        Command { output.print(it[key] ?: KEY_NOT_SET) }

    fun help(output: Out) =
        Command { output.print(HELP) }

    fun unknown(output: Out) =
        Command { output.print(UNKNOWN_COMMAND) }
}
