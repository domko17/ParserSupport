package org.plugin.parsersupport

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiManager


class ParserUtil(val project: Project) {

    fun execute(className: String, pathFile: String): Process {
        val pb = ProcessBuilder("java", "-cp", pathFile, className)
        return pb.start()
    }

    fun findClassName(filePath: String) {
        val virtualFile = LocalFileSystem.getInstance().findFileByPath(filePath)
        val psiFile = PsiManager.getInstance(project).findFile(virtualFile!!)

        if (psiFile is PsiJavaFile) {
            val javaFile = psiFile
            for (psiClass in javaFile.classes) {
                val qualifiedName = psiClass.getQualifiedName() // com.example.MyClass
                println("Found class: " + qualifiedName)
            }
        }
    }
}