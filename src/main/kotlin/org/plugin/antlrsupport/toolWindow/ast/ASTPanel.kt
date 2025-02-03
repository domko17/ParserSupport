package org.plugin.antlrsupport.toolWindow.ast

import com.intellij.ui.components.JBPanel
import javax.swing.JLabel
import javax.swing.JPanel

class ASTPanel : JBPanel<ASTPanel>() {
    private val content: JPanel = JPanel()

    init {
        content.add(JLabel("Hello ASTPanel!"))
        add(content)
    }
}