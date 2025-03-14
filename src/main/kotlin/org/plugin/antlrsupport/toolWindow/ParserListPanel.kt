package org.plugin.antlrsupport.toolWindow

import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import org.plugin.antlrsupport.AntlrParserGenerator
import org.plugin.antlrsupport.notify
import java.awt.BorderLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

/**
 * Created by Dominik.
 *
 * Panel for all found ANTLR generated parsers
 */
class ParserListPanel(project: Project) : JBPanel<ParserListPanel>() {

    private val content: JPanel = JPanel()

    init {
//        val grammarList = JBList((0 until grammarTrackerService.listModel.size()).map { grammarTrackerService.listModel.getElementAt(it).name })
//        val grammarList = JBList()
//        val panel = JPanel(BorderLayout())
//        panel.add(JBScrollPane(grammarList), BorderLayout.CENTER)


//        content.add(panel)
        add(content)
    }
}