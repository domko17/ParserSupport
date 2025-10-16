package org.plugin.parsersupport

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.vfs.VirtualFile
import org.plugin.parsersupport.service.ParserSupportService

class GrammarFileListener : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.extension.equals("g4", ignoreCase = true)) {

            val parserSupportService = ParserSupportService(file)
            val root = parserSupportService.getAst()
            print("RESULT OF AST::: ")
            parserSupportService.printTree(root)


            println("Grammar file opened: ${file.path}")
        }
    }
}