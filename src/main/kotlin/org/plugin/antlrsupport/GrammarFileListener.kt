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
        }
    }

    override fun after(events: MutableList<out VFileEvent>) {
        for (event in events) {
            val file = event.file ?: continue
            if (file.extension == "g4") {
                val parser = ParserGenerator.generateParser(file)
            }
        }
    }
}