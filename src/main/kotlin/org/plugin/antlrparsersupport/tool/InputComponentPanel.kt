package org.plugin.antlrparsersupport.tool

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextField
import javax.swing.*
import java.awt.*
import javax.swing.JComponent


/**
 * Created by Dominik.
 *
 */
class InputComponentPanel(val project: Project) : JBPanel<InputComponentPanel>() {

    init {
        val content = JPanel(BorderLayout())

        content.add(createInputParserTopBar(), BorderLayout.NORTH)
        content.add(createCodeArea(), BorderLayout.WEST)

        add(content)
    }

    fun createInputParserTopBar(): JComponent {
        val topPanel = JPanel(BorderLayout())

        val fileChooserField = TextFieldWithBrowseButton()
        fileChooserField.preferredSize = Dimension(200, 28)
        fileChooserField.toolTipText = "Select Parser"

        val descriptor: FileChooserDescriptor = FileChooserDescriptor(true, false, false, false, false, false)
        descriptor.title = "Select Parser"
        descriptor.description = "Only files inside project can be selected"
        val basePath = project.basePath ?: ""
        val targetDir = VfsUtil.findFileByIoFile(java.io.File(basePath, "target"), true)

        fileChooserField.addBrowseFolderListener(
            "Choose File",
            "Select a file from the project directory",
            project,
            descriptor
        )

        if (targetDir != null) {
            fileChooserField.text = targetDir.path
        }

        val label = JLabel("Selected:")
        topPanel.add(fileChooserField, BorderLayout.WEST)
        topPanel.add(label)

        return topPanel
    }

    fun createCodeArea(): JComponent {
        val textArea = JTextArea(20, 60)
        textArea.lineWrap = false
        textArea.font = Font("JetBrains Mono", Font.PLAIN, 14)

        return JScrollPane(textArea)
    }
}