package com.example.commands

import com.example.main.Out
import com.example.main.Strings
import com.example.transactions.TransactionState

object TransactionsCommands {

    fun begin(transactions: TransactionState): Command =
        Command { transactions.begin() }

    fun commit(output: Out, transactions: TransactionState): Command =
        Command {
            if (transactions.isEmpty) {
                output.print(Strings.NO_TRANSACTION)
            } else {
                transactions.commit()
            }
        }

    fun rollback(output: Out, transactions: TransactionState) =
        Command {
            if (transactions.isEmpty) {
                output.print(Strings.NO_TRANSACTION)
            } else {
                transactions.rollback(it)
            }
        }
}
