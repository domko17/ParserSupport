package org.plugin.parsersupport

import com.intellij.notification.NotificationType
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import org.plugin.parsersupport.exception.process


class GrammarFileListener : FileDocumentManagerListener {

    override fun beforeAllDocumentsSaving() {
        val fileDocumentManager = FileDocumentManager.getInstance()

        for (document in fileDocumentManager.unsavedDocuments) {
            val file: VirtualFile = fileDocumentManager.getFile(document) ?: continue

            if (GrammarTypes.ANTLR.typeOfGrammar == file.extension) {

                // TODO Maybe add notify for successful generated parser after save changes of grammar and parser ready for using.
                ApplicationManager.getApplication().invokeLater {
                    runCustomAction(file)
                }

            } else {
                ProjectManager.getInstance().openProjects.forEach { project ->
                    val psiFile = PsiManager.getInstance(project).findFile(file)
                    if (psiFile != null) {
                        notify("Grammar type exception: No support type (${file.extension}) of grammar", project, NotificationType.ERROR)
                    }
                }

                process(true, "No support type of grammar")
            }
        }
    }

    private fun runCustomAction(file: VirtualFile) {

    }
}