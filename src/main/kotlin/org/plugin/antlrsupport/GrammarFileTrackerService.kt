package org.plugin.antlrsupport

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.*
import java.util.concurrent.ConcurrentHashMap

@Service(Service.Level.PROJECT)
class GrammarFileTrackerService(private val project: Project) : VirtualFileListener {

    private val grammarFiles: MutableSet<VirtualFile> = ConcurrentHashMap.newKeySet()

    init {
        VirtualFileManager.getInstance().addVirtualFileListener(this)
        scanForGrammarFiles()
    }

    private fun isValidGrammarFile(file: VirtualFile): Boolean {
        return file.extension == "g4" && !file.path.contains("/target/")
    }

    private fun scanForGrammarFiles() {
        project.baseDir?.let { baseDir ->
            VfsUtilCore.iterateChildrenRecursively(baseDir, null) { file ->
                if (isValidGrammarFile(file)) {
                    grammarFiles.add(file)
                }
                true
            }
        }
    }

    override fun fileCreated(event: VirtualFileEvent) {
        if (isValidGrammarFile(event.file)) {
            grammarFiles.add(event.file)
            println("New grammar file detected: ${event.file.path}")
        }
    }

    override fun fileDeleted(event: VirtualFileEvent) {
        if (isValidGrammarFile(event.file)) {
            grammarFiles.remove(event.file)
            println("Grammar file removed: ${event.file.path}")
        }
    }

    fun getGrammarFiles(): List<VirtualFile> = grammarFiles.toList()
}