package org.plugin.antlrparsersupport.tool

import com.intellij.ui.components.JBPanel
import javax.swing.*
import java.awt.*


/**
 * Created by Dominik.
 *
 */
class InputComponentPanel() : JBPanel<InputComponentPanel>() {

    init {
        add(createBasicCodeInput())
    }

    fun createBasicCodeInput(): JPanel {
        val textArea = JTextArea(20, 60)
        textArea.lineWrap = false
        textArea.font = Font("Monospaced", Font.PLAIN, 14)

        val scrollPane = JScrollPane(textArea)
        val panel = JPanel(BorderLayout())
        panel.add(scrollPane, BorderLayout.CENTER)

        return panel
    }
}