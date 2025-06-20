package org.plugin.parsersupport.tool

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiManager
import com.intellij.ui.components.JBPanel
import com.intellij.util.concurrency.AppExecutorUtil
import org.plugin.parsersupport.ParserUtil
import javax.swing.*
import java.awt.*
import java.io.File
import java.net.URLClassLoader
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
        val targetDir = VfsUtil.findFileByIoFile(java.io.File(basePath), true)

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


        // TODO
        //  1. generate parser after changes in grammar
        //  2. save parser .java file in samewhere of plugin
        //  3. to create instance from class file
        //  4. to obtain sentence of code lang
        //  5. processing to AST & ATN

        // button for devel testing -> load parser at the moment if is ready in a project
        val parseButton = JButton("Testing devel")

        parseButton.addActionListener {

            ReadAction.nonBlocking {
                val virtualFile = LocalFileSystem.getInstance().findFileByPath("/Users/dominikhorvath/Desktop/evolveum/prism/infra/axiom/target/generated-sources/antlr4/com/evolveum/axiom/lang/antlr/query/AxiomQueryParser.java")
                val psiFile = PsiManager.getInstance(project).findFile(virtualFile!!)
                val psiClass = (psiFile as? PsiJavaFile)?.classes?.firstOrNull()


                val qualifiedName = psiClass?.qualifiedName

                if (qualifiedName != null) {
//                    val instance = clazz.getDeclaredConstructor().newInstance()
//                    print("KASKKAK $instance")
                }

//                val parserUtil = ParserUtil(project)
//                val process = parserUtil.execute(psiClass?.qualifiedName!!, virtualFile.path)
//                val exitCode = process.waitFor()
//                println("Process exited with code $exitCode")

            }.finishOnUiThread(ModalityState.defaultModalityState()) { qualifiedName ->
                if (qualifiedName != null) {
                    Messages.showInfoMessage("Qualified class: $qualifiedName", "Info")
                } else {
                    Messages.showErrorDialog("Couldn't find class", "Error")
                }
            }.submit(AppExecutorUtil.getAppExecutorService())


//            print("Stating")
//            var util = ParserUtil(project);
//            util.findClassName(
//                "/Users/dominikhorvath/Desktop/evolveum/prism/infra/axiom/target/generated-sources/antlr4/com/evolveum/axiom/lang/antlr/query/AxiomQueryParser.java"
//            )
//            print("End")
        }

        topPanel.add(parseButton)

        // end code for devel testing


        return topPanel
    }

    fun createCodeArea(): JComponent {
        val textArea = JTextArea(20, 60)
        textArea.lineWrap = false
        textArea.font = Font("JetBrains Mono", Font.PLAIN, 14)

        return JScrollPane(textArea)
    }
}