package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBPanel
import javax.swing.JPanel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import org.plugin.antlrsupport.GrammarFileTrackerService
import java.awt.BorderLayout

class GrammarViewerPanel(project: Project) : JBPanel<GrammarViewerPanel>() {

    private val content: JPanel = JPanel()

    init {
        val grammarTrackerService = project.getService(GrammarFileTrackerService::class.java)

        val grammarList = JBList(grammarTrackerService.listModel)
        val panel = JPanel(BorderLayout())
        panel.add(JBScrollPane(grammarList), BorderLayout.CENTER)

        grammarTrackerService.addUpdateListener {
            grammarList.updateUI()
        }

        content.add(panel)
        add(content)
    }
}