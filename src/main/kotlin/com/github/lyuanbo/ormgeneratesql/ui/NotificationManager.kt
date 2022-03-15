package com.github.lyuanbo.ormgeneratesql.ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
class NotificationManager {
    companion object {
        @JvmStatic
        fun createAndShowNotification(project: Project, firstName: String, lastName: String) {
            val notification = NotificationGroup("AndroidVille", NotificationDisplayType.BALLOON, true)
            notification.createNotification(
                "Hola!",
                "Welcome to AndroidVille $firstName $lastName",
                NotificationType.INFORMATION,
                null
            ).notify(project)
        }
    }
}