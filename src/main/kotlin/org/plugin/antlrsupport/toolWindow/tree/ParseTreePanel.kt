package org.plugin.antlrsupport.toolWindow.tree

import com.intellij.ui.components.JBPanel
import javax.swing.JLabel
import javax.swing.JPanel

class ParseTreePanel : JBPanel<ParseTreePanel>() {
    private val content: JPanel = JPanel()

    init {
        content.add(JLabel("Hello parse tree Panel!"))
        add(content)
    }
}