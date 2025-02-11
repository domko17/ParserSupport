package org.plugin.antlrsupport

import com.intellij.openapi.vfs.VirtualFile

fun findFileInProject(grammarFile: VirtualFile, lexerName: String): VirtualFile? {
    var currentDir: VirtualFile? = grammarFile.parent

    while (currentDir != null) {
        val lexerFile = currentDir.findChild("$lexerName.g4")
        if (lexerFile != null && lexerFile.exists()) {
            return lexerFile
        }
        currentDir = currentDir.parent
    }

    return null
}