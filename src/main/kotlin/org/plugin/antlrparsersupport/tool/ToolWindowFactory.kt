package org.plugin.antlrparsersupport.tool

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.JBSplitter
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.content.ContentFactory
import org.plugin.antlrparsersupport.tool.tree.ParseTreePanel
import org.plugin.antlrparsersupport.tool.atn.ATNPanel

class ToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val tablePanel = JBTabbedPane()
        val splitter = JBSplitter(false, 0.4f) // 40% / 60% split

        tablePanel.addTab("Parse Tree", ParseTreePanel())
        tablePanel.addTab("Augmented Transition Networks", ATNPanel())
        splitter.firstComponent = InputComponentPanel(project)
        splitter.secondComponent = tablePanel

        val combinedContent = contentFactory.createContent(splitter, "", false)
        toolWindow.contentManager.addContent(combinedContent)
    }
}