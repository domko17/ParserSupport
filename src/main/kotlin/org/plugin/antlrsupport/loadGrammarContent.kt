package org.plugin.antlrsupport

import com.intellij.openapi.vfs.VirtualFile
import java.nio.charset.StandardCharsets

fun loadGrammarContent(file: VirtualFile): String {
    return try {
        String(file.contentsToByteArray(), StandardCharsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}