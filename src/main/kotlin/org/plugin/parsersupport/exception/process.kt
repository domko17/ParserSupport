package org.plugin.parsersupport.exception

fun process(isException: Boolean,message: String) {
    if (isException) {
        throw GrammarTypeException("GrammarException: $message")

    }
}