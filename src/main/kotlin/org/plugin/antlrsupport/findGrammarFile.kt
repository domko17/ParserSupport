package org.plugin.antlrsupport

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VfsUtil

fun findGrammarFile(project: Project, grammarFileName: String): VirtualFile? {
    val baseDir = project.baseDir
    return VfsUtil.findRelativeFile(grammarFileName, baseDir)
}