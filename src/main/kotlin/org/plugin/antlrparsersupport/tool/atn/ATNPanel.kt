package org.plugin.antlrparsersupport.tool.atn

import com.intellij.ui.components.JBPanel
import javax.swing.JLabel
import javax.swing.JPanel

class ATNPanel : JBPanel<ATNPanel>() {
    private val content: JPanel = JPanel()

    init {
        content.add(JLabel("Hello ATNPanel!"))
        add(content)
    }
}