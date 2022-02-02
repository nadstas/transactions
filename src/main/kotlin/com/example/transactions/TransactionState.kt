package com.example.transactions

import com.example.commands.Command
import com.example.commands.MutatorCommand
import com.example.main.State
import java.util.*

interface TransactionState {
    val isEmpty: Boolean

    fun begin()
    fun commit()
    fun rollback(state: State)

    fun onCmdAdded(cmd: Command)
}

class TransactionStateImpl : TransactionState {
    private val stack = Stack<MutableList<MutatorCommand>>()

    override val isEmpty: Boolean get() = stack.isEmpty()

    override fun onCmdAdded(cmd: Command) {
        if (stack.isNotEmpty() && cmd is MutatorCommand) {
            stack.peek()?.add(cmd)
        }
    }

    override fun begin() {
        stack.push(mutableListOf())
    }

    override fun commit() {
        stack.pop()
    }

    override fun rollback(state: State) {
        stack.pop().let { commands ->
            commands.forEach { it.undo(state) }
        }
    }
}