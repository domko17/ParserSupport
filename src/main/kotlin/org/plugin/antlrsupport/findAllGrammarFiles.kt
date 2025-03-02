package org.plugin.antlrsupport

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.util.indexing.FileBasedIndex

fun findAllGrammarFiles(project: Project): List<VirtualFile> {
    val grammarFiles = mutableListOf<VirtualFile>()

    ApplicationManager.getApplication().runReadAction {
        FileBasedIndex.getInstance().iterateIndexableFiles({ file ->
            if (file.extension == "g4" && ProjectFileIndex.getInstance(project).isInSourceContent(file)) {
                grammarFiles.add(file) // Add grammar file
            }
            true // Continue iteration
        }, project, null)
    }

    return grammarFiles
}