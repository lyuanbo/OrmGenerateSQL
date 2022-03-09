package com.github.lyuanbo.ormgeneratesql.ui

import javax.swing.JFrame

class DbConfigUi(title: String) : JFrame() {
    init {
        // 设置窗口标题
        this.title = title
        // 设置窗口大小
        this.setSize(800, 750)
        // 设置窗口可见
        this.isVisible = true
        // 设置窗口关闭时销毁窗口
        this.defaultCloseOperation = DISPOSE_ON_CLOSE
        // 设置窗口居中
        this.setLocationRelativeTo(null)
    }
}

