package org.plugin.antlrsupport

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope

class FindANTLRParsersAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        findGeneratedParsers(project)
    }

    private fun findGeneratedParsers(project: Project) {
        val fileTypeManager = FileTypeManager.getInstance()
        val javaFileType = fileTypeManager.getFileTypeByExtension("java")

        val files = mutableListOf<VirtualFile>()

        FileTypeIndex.processFiles(javaFileType, { file ->
            if (file.name.endsWith("Parser.java") || file.name.endsWith("Lexer.java")) {
                files.add(file)
            }
            true
        }, GlobalSearchScope.projectScope(project))

        // Print the results
        if (files.isNotEmpty()) {
            println("Project: ${project.name}")
            files.forEach { println("  - ${it.path}") }
            println("----------------------")
        } else {
            println("No generated ANTLR parsers found in project: ${project.name}")
        }
    }

}