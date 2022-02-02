package com.example.main

import com.example.commands.CommandFactory
import com.example.transactions.TransactionState
import com.example.transactions.TransactionStateImpl

class CommandProcessor(
    private val input: In,
    private val output: Out,
    private val state: State = StateImpl(),
    private val transactionState: TransactionState = TransactionStateImpl(),
    private val commandFactory: CommandFactory = CommandFactory(),
) {

    private val spacesRegex = Regex("\\s+")

    fun start() {
        while (processCmd(readCommand())) {
            /*process until exit cmd*/
        }
    }

    private fun readCommand() = input.readLine()
        ?.split(spacesRegex)
        ?.filter { it.isNotBlank() }
        ?: emptyList()

    private fun processCmd(rawCmd: List<String>): Boolean {
        if (rawCmd.isEmpty()) return true

        if (rawCmd[0].uppercase() == "EXIT") {
            return false
        }

        val command = commandFactory.createCommand(
            cmd = rawCmd[0],
            params = rawCmd.subList(1, rawCmd.size),
            output,
            transactionState
        )

        transactionState.onCmdAdded(command)
        command.apply(state)

        return true
    }
}

