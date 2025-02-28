package org.plugin.antlrsupport

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project

/**
 * TODO
 */
fun notify(message: String, project: Project, type: NotificationType = NotificationType.INFORMATION) {
    Notifications.Bus.notify(Notification("ANTLR", "ANTLR Plugin", message, type), project)
}