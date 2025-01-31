package org.plugin.antlrsupport

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MyToolWindowUI {

    val content: JPanel = JPanel()
    val ICON_PATH: String

    init {
        ICON_PATH = "/icons/antlrSupportIcon.svg";
        content.add(JLabel("Hello from My Tool Window!"))
        val button = JButton("Click me")
        button.addActionListener {
            println("Button clicked!")
        }
        content.add(button)
    }

}