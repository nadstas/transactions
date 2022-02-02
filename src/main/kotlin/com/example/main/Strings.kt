package com.example.main

object Strings {

    const val HELP = """
        SET <key> <value> // store the value for key
        GET <key>         // return the current value for key
        DELETE <key>      // remove the entry for key
        COUNT <value>     // return the number of keys that have the given value
        BEGIN             // start a new transaction
        COMMIT            // complete the current transaction
        ROLLBACK          // revert to state prior to BEGIN call
        EXIT              // exit program
        HELP              // print this instruction
    """

    const val KEY_NOT_SET = "key not set"
    const val WRONG_ARGS_MESSAGE = "Wrong arguments. Use HELP to print instructions"
    const val NO_TRANSACTION = "No transaction"
    const val UNKNOWN_COMMAND = "Unknown command"
}
