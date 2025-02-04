package org.plugin.antlrsupport.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFileVisitor
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import javax.swing.JPanel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.DefaultListModel

class GrammarLoader(project : Project) {

    private val panel = JPanel(BorderLayout())
    private val fileListModel = DefaultListModel<String>()
    private val fileList = JBList(fileListModel)
    private val textArea = JBTextArea()

    init {
        val scrollPane = JBScrollPane(textArea)
        val grammarFiles = findAllG4Files(project)
        for (file in grammarFiles) {
            fileListModel.addElement(file.path)
        }

        fileList.addListSelectionListener { event ->
            if (!event.valueIsAdjusting) {
                val selectedFile = grammarFiles[fileList.selectedIndex]
                textArea.text = selectedFile.contentsToByteArray().toString(Charsets.UTF_8)
            }
        }

        val listScrollPane = JBScrollPane(fileList)
        listScrollPane.preferredSize = Dimension(250, 0)

        panel.add(listScrollPane, BorderLayout.WEST)
        panel.add(scrollPane, BorderLayout.CENTER)
    }

    fun getContent() = panel

    private fun findAllG4Files(project: Project): List<VirtualFile> {
        val projectBase = project.baseDir ?: return emptyList()
        val files = mutableListOf<VirtualFile>()

        VfsUtil.visitChildrenRecursively(projectBase, object : VirtualFileVisitor<Boolean>() {
            override fun visitFile(file: VirtualFile): Boolean {
                if (file.extension == "g4") {
                    files.add(file)
                }
                return true
            }
        })

        return files
    }
}