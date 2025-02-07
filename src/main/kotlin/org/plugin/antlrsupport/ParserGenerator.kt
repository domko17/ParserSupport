package org.plugin.antlrsupport

import com.intellij.openapi.vfs.VirtualFile
import org.antlr.v4.Tool
import org.antlr.v4.runtime.*
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.net.URLClassLoader
import javax.tools.ToolProvider

object ParserGenerator {

    private val cache = mutableMapOf<String, Class<out Parser>>()

    fun generateParser(file: VirtualFile): Parser? {
        val grammarContent = String(file.contentsToByteArray(), StandardCharsets.UTF_8)
        val grammarName = file.nameWithoutExtension

        // Define temporary directory for generated files
        val tempDir = Files.createTempDirectory("antlr").toFile()
        val grammarFile = File(tempDir, "$grammarName.g4")
        grammarFile.writeText(grammarContent)

        // Run ANTLR tool to generate lexer/parser Java files
        val antlrTool = Tool(arrayOf("-o", tempDir.absolutePath, grammarFile.absolutePath))
        antlrTool.processGrammarsOnCommandLine()

        // Compile generated Java files
        if (!compileGeneratedFiles(tempDir)) {
            println("Failed to compile ANTLR generated files.")
            return null
        }

        // Load compiled classes dynamically
        val parserClass = loadParserClass(tempDir, grammarName)
        if (parserClass != null) {
            cache[file.name] = parserClass
            return instantiateParser(parserClass, grammarContent)
        }
        return null
    }

    private fun compileGeneratedFiles(tempDir: File): Boolean {
        val javaFiles = tempDir.listFiles { _, name -> name.endsWith(".java") } ?: return false
        val compiler = ToolProvider.getSystemJavaCompiler()
        val result = compiler.run(null, null, null, *javaFiles.map { it.absolutePath }.toTypedArray())
        return result == 0
    }


    private fun loadParserClass(tempDir: File, grammarName: String): Class<out Parser>? {
        return try {
            val classLoader = URLClassLoader(arrayOf(tempDir.toURI().toURL()), this::class.java.classLoader)
            val parserClassName = "${grammarName}Parser"
            @Suppress("UNCHECKED_CAST")
            classLoader.loadClass(parserClassName) as Class<out Parser>
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun instantiateParser(parserClass: Class<out Parser>, content: String): Parser? {
        return try {
            val lexerClass = Class.forName(parserClass.name.replace("Parser", "Lexer"))
            val inputStream = CharStreams.fromString(content)
            val lexer = lexerClass.getConstructor(CharStream::class.java).newInstance(inputStream) as Lexer
            val tokens = CommonTokenStream(lexer)
            parserClass.getConstructor(TokenStream::class.java).newInstance(tokens) as Parser
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}