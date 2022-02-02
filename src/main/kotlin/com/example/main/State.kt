package com.example.main

interface State {

    /**
     * Store the value for key
     */
    operator fun get(key: String): String?

    /**
     * Return the current value for key
     */
    operator fun set(key: String, value: String)

    /**
     * Remove the entry for key
     */
    fun remove(key: String)

    /**
     * @return return the number of keys that have the given value
     */
    fun count(value: String): Int
}

class StateImpl : State {
    private val map = mutableMapOf<String, String>()

    override fun get(key: String) = map[key]

    override fun set(key: String, value: String) {
        map[key] = value
    }

    override fun remove(key: String) {
        map.remove(key)
    }

    override fun count(value: String): Int {
        return map.values.count { it == value }
    }
}
