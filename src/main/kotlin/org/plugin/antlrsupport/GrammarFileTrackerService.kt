package org.plugin.antlrsupport

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.*
import java.util.concurrent.ConcurrentHashMap
import javax.swing.DefaultListModel

@Service(Service.Level.PROJECT)
class GrammarFileTrackerService(private val project: Project) : VirtualFileListener {

    private val grammarFiles: MutableSet<VirtualFile> = ConcurrentHashMap.newKeySet()
    private val listeners = mutableListOf<() -> Unit>()

    val listModel = DefaultListModel<String>()

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
                    listModel.addElement(file.name)
                }
                true
            }
        }
    }

    override fun fileCreated(event: VirtualFileEvent) {
        if (isValidGrammarFile(event.file)) {
            grammarFiles.add(event.file)
            listModel.addElement(event.file.name)
            notify("New grammar file (.g4) detected.", project)
        }
    }

    override fun fileDeleted(event: VirtualFileEvent) {
        if (isValidGrammarFile(event.file)) {
            grammarFiles.remove(event.file)
            listModel.removeElement(event.file.name)
            notify("Deleted grammar file (.g4) detected.", project)
        }
    }

    fun addUpdateListener(listener: () -> Unit) {
        listeners.add(listener)
    }

    fun getGrammarFiles(): List<VirtualFile> = grammarFiles.toList()
}