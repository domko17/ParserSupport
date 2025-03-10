package org.plugin.antlrsupport.toolWindow

import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBPanel
import javax.swing.JPanel
import javax.swing.JButton
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import org.plugin.antlrsupport.AntlrParserGenerator
import org.plugin.antlrsupport.GrammarFileTrackerService
import org.plugin.antlrsupport.notify
import java.awt.BorderLayout
import javax.swing.BoxLayout

class GrammarViewerPanel(project: Project) : JBPanel<GrammarViewerPanel>() {

    private val content: JPanel = JPanel()

    init {
        val grammarTrackerService = project.getService(GrammarFileTrackerService::class.java)
//        val grammarList = JBList((0 until grammarTrackerService.listModel.size()).map { grammarTrackerService.listModel.getElementAt(it).name })
        val grammarList = JBList(grammarTrackerService.listModel)
        val panel = JPanel(BorderLayout())
        panel.add(JBScrollPane(grammarList), BorderLayout.CENTER)

        grammarTrackerService.addUpdateListener {
            grammarList.updateUI()
        }

        val buttonPanel = JPanel()
        val generateButton = JButton("Generate Parser").apply {
            addActionListener {
                val selectedFile = grammarList.selectedValue
                if (selectedFile != null) {
                    val success = AntlrParserGenerator.generateParserForFile(project, selectedFile.path)

                    if (success) {
                        notify("Parser generated successfully!", project)
                    } else {
                        notify("Failed to generate parser.", project, NotificationType.WARNING)
                    }
                } else {
                    notify("No file selected!", project, NotificationType.WARNING)
                }
            }
        }

        buttonPanel.layout = BoxLayout(buttonPanel, BoxLayout.Y_AXIS)
        buttonPanel.add(generateButton)
        panel.add(buttonPanel, BorderLayout.SOUTH)

        content.add(panel)
        add(content)
    }
}