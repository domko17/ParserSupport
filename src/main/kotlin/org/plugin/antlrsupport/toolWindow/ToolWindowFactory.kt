package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import org.plugin.antlrsupport.toolWindow.ast.ASTPanel
import org.plugin.antlrsupport.toolWindow.atn.ATNPanel

class ToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()

//        val mainToolWindow = contentFactory.createContent(GrammarLoader(project).getContent(), "ANTLR Grammar", false)
//        toolWindow.contentManager.addContent(mainToolWindow)

        val parseTreeWindow = contentFactory.createContent(ASTPanel(), "Parse Tree", false)
        toolWindow.contentManager.addContent(parseTreeWindow)

        val atnWindow = contentFactory.createContent(ATNPanel(), "Augmented Transition Networks", false)
        toolWindow.contentManager.addContent(atnWindow)
    }
}