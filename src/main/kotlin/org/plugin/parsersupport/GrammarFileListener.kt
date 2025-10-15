package org.plugin.parsersupport

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.vfs.VirtualFile

class GrammarFileListener : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.extension.equals("g4", ignoreCase = true)) {
            println("Grammar file opened: ${file.path}")
        }
    }
}