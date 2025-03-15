package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import org.plugin.antlrsupport.GrammarFileTrackerService
import java.awt.BorderLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.io.File
import javax.swing.*


/**
 * Created by Dominik.
 *
 * Panel for all found ANTLR generated parsers
 */
class ParserListPanel(project: Project) : JBPanel<ParserListPanel>() {

    private val content: JPanel = JPanel()

    init {
        val grammarTrackerService = project.getService(GrammarFileTrackerService::class.java)
        grammarTrackerService.listModel
        val grammarList = JBList((0 until grammarTrackerService.listModel.size()).map { grammarTrackerService.listModel.getElementAt(it).name })

        val panel = JPanel(BorderLayout())
        // updated grammar list after created or removed grammar file
        grammarTrackerService.addUpdateListener {
            grammarList.updateUI()
        }

        val filePathField = JTextField(20)
        filePathField.isEditable = false;
        val browseButton = JButton("Browse")
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