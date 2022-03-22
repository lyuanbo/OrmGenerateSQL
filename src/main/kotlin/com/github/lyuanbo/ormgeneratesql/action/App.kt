package com.github.lyuanbo.ormgeneratesql.action

import com.github.lyuanbo.ormgeneratesql.dialog.CustomDialogWrapper
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.components.BaseComponent


class App : BaseComponent {
    override fun initComponent() {
        // 获取弹出框的大小
        CustomDialogWrapper().showAndGet()
    }
}