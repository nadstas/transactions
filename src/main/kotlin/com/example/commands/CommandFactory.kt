package com.example.commands

import com.example.main.Out
import com.example.transactions.TransactionState

class CommandFactory {
    fun createCommand(
        cmd: String,
        params: List<String>,
        output: Out,
        transactionState: TransactionState
    ): Command {
        return when (cmd.uppercase()) {
            "SET" -> {
                if (params.size < 2) {
                    SimpleCommands.wrongAgrs(output)
                } else {
                    SetCommand(params[0], params[1])
                }
            }
            "GET" -> {
                if (params.isEmpty()) {
                    SimpleCommands.wrongAgrs(output)
                } else {
                    SimpleCommands.get(params[0], output)
                }
            }
            "DELETE" -> {
                if (params.isEmpty()) {
                    SimpleCommands.wrongAgrs(output)
                } else {
                    DeleteCommand(params[0])
                }
            }
            "COUNT" -> {
                if (params.isEmpty()) {
                    SimpleCommands.wrongAgrs(output)
                } else {
                    SimpleCommands.count(params[0], output)
                }
            }
            "BEGIN" -> TransactionsCommands.begin(transactionState)
            "COMMIT" -> TransactionsCommands.commit(output, transactionState)
            "ROLLBACK" -> TransactionsCommands.rollback(output, transactionState)
            "HELP" -> SimpleCommands.help(output)
            "" -> SimpleCommands.empty
            else -> SimpleCommands.unknown(output)
        }
    }
}
