package com.github.lyuanbo.ormgeneratesql.ui

import com.intellij.icons.AllIcons
import com.intellij.ui.JBColor
import java.awt.*
import javax.swing.JButton

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants


class DbConfigUIV2(title: String) {
    init {
        val window = JFrame(title)  // 创建顶级容器
        val windowWidth = 800 // 设置窗口宽度
        val windowHeight = 750 // 设置窗口高度
        val buttonHeight = 50 // 底部按钮容器高度
        val leftPanelWidth = 180 // 左侧面板宽度
        val leftPanelHeight = windowHeight - buttonHeight - 26 // 左侧面板高度, 减去26是因为标题栏高度

        val rootPanel = JPanel(null)
        rootPanel.setSize(windowWidth, windowHeight)

        // 创建左边表格
        val leftJPanel = JPanel(null)
        leftJPanel.setSize(leftPanelWidth, leftPanelHeight)

        // 工具栏
        val toolJPanel = JPanel(null)
        toolJPanel.setSize(leftPanelWidth, 28)
        toolJPanel.border = javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, JBColor.border())
        // + button
        val addButton = JButton(AllIcons.General.Add)
        // 不绘制边框
        addButton.border = null
        // 不绘制背景
        addButton.isContentAreaFilled = false
        // 设置按钮大小
        addButton.setBounds(4, 0, 26, 26)
        // 设置边框为0
        addButton.border = null
        toolJPanel.add(addButton)

        // - button
        val minusButton = JButton(AllIcons.General.Remove)
        minusButton.setBounds(addButton.width + 6, 0, 26, 26)
        // 设置边框为0
        minusButton.border = null
        // 不绘制背景
        minusButton.isContentAreaFilled = false
        toolJPanel.add(minusButton)
        // 创建数据库列表
        val dbPanelList = JPanel(null)
        // 向下偏移
        dbPanelList.setLocation(0, toolJPanel.height)
        dbPanelList.setSize(leftPanelWidth, leftPanelHeight - toolJPanel.height)
        // 设置右边框，边框宽度为1，其它边框为0
        // 十六进制的颜色值，例如：#393939
        dbPanelList.border = javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, JBColor.border())
        // 添加到左边面板
        leftJPanel.add(toolJPanel)
        leftJPanel.add(dbPanelList)
        leftJPanel.background = null
        // 添加到中间容器
        rootPanel.add(leftJPanel)

        // 创建右边容器
        val rightJPanel = JPanel(null)
        rightJPanel.setLocation(leftPanelWidth, 0) // 设置位置
        rightJPanel.setSize(windowWidth - leftPanelWidth, leftPanelHeight)
        rightJPanel.border = javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, JBColor.border())
        // 添加到中间容器
        rootPanel.add(rightJPanel)

        // 创建按钮容器
        val buttonJPanel = JPanel(null)
        // 设置偏移量
        buttonJPanel.setLocation(0, leftPanelHeight)
        buttonJPanel.setSize(windowWidth, buttonHeight)
        buttonJPanel.border = javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, JBColor.border())  // 设置边框
        rootPanel.add(buttonJPanel)

        // 取消按钮
        val cancelButton = JButton("取消")
        cancelButton.setSize(72, 24)
        cancelButton.setLocation(windowWidth - 156 - 28, 10)
        cancelButton.border = RoundBorder(Color(Integer.decode("#C4C4C4")), null, 10, 10)


        val confirmJPanel = JPanel(null)
        confirmJPanel.setSize(72, 24)
        confirmJPanel.setLocation(windowWidth - 72 - 28, 10) // 相对它父级容器的偏移量

        confirmJPanel.border = RoundBorder(Color(Integer.decode("#567B99")), Color(Integer.decode("#3E628D")), 10, 10)
        val confirmButton = JButton("确定")
        confirmButton.setSize(72, 24)
        confirmButton.isBorderPainted = false
        confirmButton.background = null
        confirmButton.isOpaque = true
        confirmButton.foreground = Color.WHITE
        // 设置字体
        //  confirmButton.font=Font()

        confirmJPanel.add(confirmButton)
        // 清除内边距
        confirmButton.margin = Insets(0, 0, 0, 0)
        // confirmButton.border = RoundBorder(Color.black, 10, 10)
        confirmButton.isContentAreaFilled = false
        buttonJPanel.add(cancelButton)
        buttonJPanel.add(confirmJPanel)
        rootPanel.add(buttonJPanel)

        // 添加到顶级容器
        window.contentPane = rootPanel

        // 设置窗口标题
        window.title = title
        // 设置窗口大小
        window.setSize(windowWidth, windowHeight)
        // 设置窗口最大大小
      //  window.maximumSize = window.size
        // 设置窗口可见
        window.isVisible = true
        // 禁用窗口最大化
        window.isResizable = false
        // 设置窗口关闭时销毁窗口
        window.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        // 设置窗口居中
        window.setLocationRelativeTo(null)
    }
}


