package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.components.JBPanel
import javax.swing.JPanel
import com.intellij.ui.components.JBList
import org.plugin.antlrsupport.findAllGrammarFiles
import javax.swing.JScrollPane
import javax.swing.DefaultListModel

class GrammarViewerPanel(project: Project) : JBPanel<GrammarViewerPanel>() {

    private val content: JPanel = JPanel()
    private val listModel = DefaultListModel<String>()
    private val grammarList = JBList(listModel)

    init {
        val leftPanel = JScrollPane(grammarList)

        updateGrammarList(findAllGrammarFiles(project))
        content.add(leftPanel)
        add(content)
    }

    private fun updateGrammarList(files: List<VirtualFile>) {
        listModel.clear()
        files.forEach { file -> listModel.addElement(file.name) }
    }
}