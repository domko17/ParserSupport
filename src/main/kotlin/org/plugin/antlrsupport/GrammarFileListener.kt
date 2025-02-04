package org.plugin.antlrsupport

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.project.Project
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

class GrammarFileListener : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (file.extension == "g4") {
            val project = source.project
            generateANTLRParser(project, file)
        }
    }

    private fun generateANTLRParser(project: Project, file: VirtualFile) {
        val grammarFilePath = file.path
        val outputDir = "${project.basePath}/src/main/gen"
        val antlrJarPath = "${project.basePath}/lib/antlr-4.9.3-complete.jar"  // Ensure ANTLR JAR is present

        try {
            val command = "java -jar $antlrJarPath -Dlanguage=Kotlin -o $outputDir $grammarFilePath"
            val process = Runtime.getRuntime().exec(command)

            process.waitFor()

            if (process.exitValue() == 0) {
                notify("ANTLR Parser Generated Successfully!", project)
            } else {
                notify("ANTLR Parser Generation Failed!", project, NotificationType.ERROR)
            }
        } catch (e: Exception) {
            notify("Error Running ANTLR: ${e.message}", project, NotificationType.ERROR)
        }
    }

    private fun notify(message: String, project: Project, type: NotificationType = NotificationType.INFORMATION) {
        Notifications.Bus.notify(Notification("ANTLR", "ANTLR Plugin", message, type), project)
    }

}