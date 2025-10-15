package org.plugin.parsersupport.tool.tree

import com.intellij.ui.components.JBPanel
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class ParseTreePanel() : JBPanel<ParseTreePanel>() {

    private var content: JPanel = JPanel()

    init {
        content.add(JLabel("Hello parse tree Panel!"))
        content.add(getTreeComponent())
        add(content)
    }

    private fun getTreeComponent(): JComponent {

//        print("RESULT OF AST::: ${parserSupportService.getAst()}")

        val component = JPanel()
        return component
    }
}