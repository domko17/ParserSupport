package org.plugin.parsersupport

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiDocumentManager
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

fun project(document: Document): Project {
    // Find the project which owns this document
    return ProjectManager.getInstance().openProjects
        .firstOrNull { project ->
            PsiDocumentManager.getInstance(project).getPsiFile(document) != null
        } ?: throw IllegalStateException("Project is unknown or not available")
}