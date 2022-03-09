package com.github.lyuanbo.ormgeneratesql.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.awt.EventQueue
import com.github.lyuanbo.ormgeneratesql.ui.DbConfigUi


// 数据库配置
class DbConfigAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        EventQueue.invokeLater {
            DbConfigUi("数据库配置").isVisible=true
        }
    }

}