package org.plugin.antlrsupport

import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import org.jetbrains.annotations.NotNull

class GrammarFileListener(private val project: Project) : BulkFileListener {
    override fun after(@NotNull events: List<VFileEvent>) {
        for (event in events) {
            val file: VirtualFile = event.file ?: continue
            if (file.extension == "g4") {

                println("ASKASKKASK>>  $file")

//                val document: Document? = FileDocumentManager.getInstance().getDocument(file)
//                val grammarContent = document?.text ?: continue



                // Generate and cache parser
//                val generator = project.service<AntlrParserGenerator>()
//                generator.generateParserInMemory(grammarContent, file.nameWithoutExtension)
            }
        }
    }
}