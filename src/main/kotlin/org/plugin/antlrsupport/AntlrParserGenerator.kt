package org.plugin.antlrsupport

import com.intellij.notification.NotificationType
import org.antlr.v4.Tool
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

object AntlrParserGenerator {

    private val generatedParsers = mutableSetOf<String>()

    fun generateParserForFile(project: Project, grammarFileName: String): Boolean {
        val grammarFile = findGrammarFile(project, grammarFileName) ?: return false

        if (generatedParsers.contains(grammarFile.path)) {
            println("Parser already generated for: ${grammarFile.name}")
            return true
        }

        val outputDir = File(project.basePath, ".antlr_cache")
        if (!outputDir.exists()) outputDir.mkdirs()

        try {
            val tool = Tool(
                arrayOf(
                    "-Dlanguage=Java",
                    "-o", outputDir.absolutePath,
                    grammarFile.path
                )
            )
            tool.processGrammarsOnCommandLine()

            generatedParsers.add(grammarFile.path)
            println("Parser generated at: ${outputDir.absolutePath}")
            return true
        } catch (e: Exception) {
            notify(e.localizedMessage, project, NotificationType.ERROR)
            return false
        }
    }

    private fun findGrammarFile(project: Project, fileName: String): VirtualFile? {
        return LocalFileSystem.getInstance().findFileByPath("${project.basePath}/$fileName")
    }
}