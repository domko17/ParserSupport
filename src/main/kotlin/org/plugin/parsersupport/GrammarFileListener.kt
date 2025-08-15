package org.plugin.parsersupport

import com.intellij.notification.NotificationType
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import org.plugin.parsersupport.exception.process
import org.plugin.parsersupport.generate.AntlrGenerator
import java.io.File

class GrammarFileListener : FileDocumentManagerListener {

    override fun beforeAllDocumentsSaving() {
        val fileDocumentManager = FileDocumentManager.getInstance()

        for (document in fileDocumentManager.unsavedDocuments) {
            val grammarFile: VirtualFile = fileDocumentManager.getFile(document) ?: continue

            if (GrammarTypes.ANTLR.typeOfGrammar == grammarFile.extension) {

                // TODO Maybe add notify for successful generated parser after save changes of grammar and parser ready for using.
                ApplicationManager.getApplication().invokeLater {
                    updateParser(grammarFile)
                }

            } else {
                ProjectManager.getInstance().openProjects.forEach { project ->
                    val psiFile = PsiManager.getInstance(project).findFile(grammarFile)
                    if (psiFile != null) {
                        notify("Grammar type exception: No support type (${grammarFile.extension}) of grammar", project, NotificationType.ERROR)
                    }
                }

                process(true, "No support type of grammar")
            }
        }
    }

    private fun updateParser(file: VirtualFile) {
        val antlr4Generator = AntlrGenerator();
        val classLoader = antlr4Generator.generateParser(File(file.path));
        print("CLASS_LOADER: $classLoader")
    }
}