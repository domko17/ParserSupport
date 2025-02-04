package org.plugin.antlrsupport

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages


class GenerateParserAction : AnAction("Generate ANTLR Parser") {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val grammarFile = findGrammarFile(project, "src/main/antlr/MyGrammar.g4")

        if (grammarFile != null) {
            generateParser(grammarFile.path, "${project.basePath}/generated")
            Messages.showInfoMessage("Parser generated successfully!", "ANTLR Plugin")
        } else {
            Messages.showErrorDialog("Grammar file not found.", "Error")
        }
    }
}