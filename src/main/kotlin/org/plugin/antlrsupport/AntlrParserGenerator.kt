package org.plugin.antlrsupport

import org.antlr.v4.Tool
import org.antlr.v4.codegen.CodeGenerator
import org.antlr.v4.tool.Grammar
import com.intellij.openapi.components.Service

@Service
class AntlrParserGenerator {
    private val parserCache = mutableMapOf<String, Class<*>>() // In-memory parser cache

    fun generateParserInMemory(grammarContent: String, grammarName: String) {
        try {
            val tool = Tool()
            val grammar = Grammar(grammarContent)
            val codeGenerator = CodeGenerator(tool, grammar, "Java")

//            val parserCode = codeGenerator.genRecognizer()
//            val parserClass = compileAndLoadParser(parserCode)

            // Store in cache
//            parserCache[grammarName] = parserClass
            println("ANTLR Parser Cached for $grammarName")
        } catch (e: Exception) {
            println("ANTLR In-Memory Generation Failed: ${e.message}")
        }
    }

    fun getGeneratedParser(grammarName: String): Class<*>? {
        return parserCache[grammarName]
    }

//    private fun notify(message: String, project: Project, type: NotificationType = NotificationType.INFORMATION) {
//        Notifications.Bus.notify(Notification("ANTLR", "ANTLR Plugin", message, type), project)
//    }
}