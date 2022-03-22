package com.github.lyuanbo.ormgeneratesql.action

import com.github.lyuanbo.ormgeneratesql.dialog.CustomDialogWrapper
import com.github.lyuanbo.ormgeneratesql.ui.DbConfigUIV2
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

// 继承AnAction来实现菜单项动作
class DBConfigAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // 获取弹出框的大小
        CustomDialogWrapper().showAndGet()
    }
}