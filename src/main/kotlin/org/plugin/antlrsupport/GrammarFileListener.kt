package org.plugin.antlrsupport

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent

class GrammarFileListener : FileEditorManagerListener, BulkFileListener  {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.extension == "g4") {
            val parser = ParserGenerator.generateParser(file)
            if (parser != null) {
                println("Generated and cached parser for ${file.name}")
            } else {
                println("Failed to generate parser for ${file.name}")
            }
        }
    }

    override fun after(events: MutableList<out VFileEvent>) {
        for (event in events) {
            val file = event.file ?: continue
            if (file.extension == "g4") {
                println("Grammar file modified/saved: ${file.name}")
                val content = loadGrammarContent(file)
                println("Grammar file ${file.name} saved. Content:\n$content")
            }
        }
    }
}