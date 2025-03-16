package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import org.plugin.antlrsupport.GrammarFileTrackerService
import java.awt.BorderLayout
import java.io.File
import javax.swing.*


/**
 * Created by Dominik.
 *
 * Panel for picking ANTLR generated parser
 */
class ParserListPanel(project: Project) : JBPanel<ParserListPanel>() {

    private val content: JPanel = JPanel()

    init {
        val grammarTrackerService = project.getService(GrammarFileTrackerService::class.java)
        val grammarList = JBList((0 until grammarTrackerService.listModel.size()).map { grammarTrackerService.listModel.getElementAt(it).name })

        val panel = JPanel(BorderLayout())
        // updated grammar list after created or removed grammar file
        grammarTrackerService.addUpdateListener {
            grammarList.updateUI()
        }

        val filePathField = JTextField(20)
        val browseButton = JButton()

        filePathField.isEditable = false;

        browseButton.add(JLabel("<html><center>Browse<br>parser</center></html>", JLabel.CENTER))
        browseButton.addActionListener {
            val fileChooser = JFileChooser()
            val returnValue = fileChooser.showOpenDialog(panel)
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                val selectedFile: File = fileChooser.selectedFile
                filePathField.text = selectedFile.getAbsolutePath()
            }
        }

        panel.add(JBScrollPane(grammarList), BorderLayout.WEST)
        panel.add(filePathField, BorderLayout.CENTER)
        panel.add(browseButton, BorderLayout.EAST)
        content.add(panel)
        add(content)
    }
}