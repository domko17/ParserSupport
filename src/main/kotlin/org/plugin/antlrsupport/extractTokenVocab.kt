package org.plugin.antlrsupport

fun extractTokenVocab(grammarContent: String): String? {
    val regex = """tokenVocab\s*=\s*([a-zA-Z_][a-zA-Z0-9_]*)""".toRegex()
    val match = regex.find(grammarContent)
    return match?.groupValues?.get(1) // Extracts "MyLexer"
}