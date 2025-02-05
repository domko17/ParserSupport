package org.plugin.antlrsupport

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class AntlrGrammarFileListener(private val project: Project) : FileEditorManagerListener {
    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.extension == "g4") {
            val content = readFileContent(file)
            println("Opened .g4 file: ${file.name}")
            println("Content: $content")

//            // Trigger parser generation in memory
//            val generator = project.service<AntlrParserGenerator>()
//            generator.generateParserInMemory(content, file.nameWithoutExtension)
        }
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        if (file.extension == "g4") {
            println("Closed .g4 file: ${file.name}")
        }
    }

    private fun readFileContent(file: VirtualFile): String {
        return file.inputStream.bufferedReader().use { it.readText() }
    }
}