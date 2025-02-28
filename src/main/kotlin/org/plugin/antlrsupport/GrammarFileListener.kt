package org.plugin.antlrsupport

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileListener
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.vfs.VirtualFileEvent

@Service
class GrammarFileListener(private val project: Project) : VirtualFileListener {

    init {
        VirtualFileManager.getInstance().addVirtualFileListener(this)
    }

    override fun fileCreated(event: VirtualFileEvent) {
        if (event.file.extension == "g4") {
            println("New ANTLR grammar file detected: ${event.file.path}")
        }
    }

    override fun fileDeleted(event: VirtualFileEvent) {
        if (event.file.extension == "g4") {
            println("ANTLR grammar file deleted: ${event.file.path}")
        }
    }

    override fun contentsChanged(event: VirtualFileEvent) {
        if (event.file.extension == "g4") {
            println("ANTLR grammar file modified: ${event.file.path}")
        }
    }

    fun findAllGrammarFiles(): List<VirtualFile> {
        return project.baseDir?.let { baseDir ->
            baseDir.children.filter { it.extension == "g4" }
        } ?: emptyList()
    }
}