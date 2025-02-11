package org.plugin.antlrsupport

import org.antlr.v4.Tool
import org.antlr.v4.tool.ANTLRMessage
import org.antlr.v4.tool.ANTLRToolListener

class CustomANTLRToolListener : ANTLRToolListener {
    val errors = mutableListOf<String>()

    override fun info(msg: String) {
        println("[INFO] $msg")
    }

    override fun error(msg: ANTLRMessage?) {
        errors.add("[ERROR] $msg")
    }

    override fun warning(msg: ANTLRMessage?) {
        println("[WARNING] $msg")
    }
}