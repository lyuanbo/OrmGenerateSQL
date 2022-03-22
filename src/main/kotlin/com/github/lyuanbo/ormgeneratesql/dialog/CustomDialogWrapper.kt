package com.github.lyuanbo.ormgeneratesql.dialog

import com.intellij.icons.AllIcons
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.JBColor
import org.apache.commons.lang.StringUtils
import java.awt.Dimension
import java.awt.Point
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.border.Border


class CustomDialogWrapper : DialogWrapper(true) {
    private var inputTextField: JTextField? = null
    private var windowWidth: Int = 800
    private var windowHeight: Int = 750
    private var exitAction = DialogWrapperExitAction("取消", CANCEL_EXIT_CODE)
    private var okAction = CustomOKAction()
    private var that = this

    init {

        // 设置窗口标题
        title = "数据库配置"

        // 设置窗口大小
        super.setSize(windowWidth, windowHeight)
        //  this.window.setBounds(0, 0, windowWidth, windowHeight)
        // 内边距
        // 禁用窗口最大化
        // isResizable = false
        init()

    }

    override fun createContentPaneBorder(): Border {
        return BorderFactory.createMatteBorder(0, 0, 0, 0, JBColor.background())
    }


    /**
     * 创建视图
     * @return
     */
    override fun createCenterPanel(): JComponent {
        // 创建主容器，中间面板
        val rootPanel = JPanel(null)
        // 设置主容器最小大小
        rootPanel.minimumSize = Dimension(windowWidth, windowHeight)





        rootPanel.setBounds(0, 0, windowWidth, windowHeight)
        rootPanel.setSize(windowWidth, windowHeight)
        rootPanel.border = BorderFactory.createMatteBorder(1, 0, 1, 0, JBColor.border())
        // 创建左边表格
        val leftJPanel = JPanel(null)
        val leftPanelWidth = 180 // 左侧面板宽度
        leftJPanel.setSize(leftPanelWidth, windowHeight)
        leftJPanel.border = BorderFactory.createMatteBorder(1, 0, 1, 1, JBColor.border())
        leftJPanel.background = null
        // 工具栏
        val toolJPanel = JPanel(null)
        toolJPanel.setSize(leftPanelWidth, 28)
        toolJPanel.border = BorderFactory.createMatteBorder(1, 0, 1, 1, JBColor.border())
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
        // 添加到左边面板
        leftJPanel.add(toolJPanel)
        // 创建数据库列表
        val dbPanelList = JPanel(null)
        // 向下偏移
        dbPanelList.setLocation(leftPanelWidth, 0)
        dbPanelList.setSize(windowWidth - leftPanelWidth, windowHeight)
        // 设置右边框，边框宽度为1，其它边框为0
        // 十六进制的颜色值，例如：#393939
        dbPanelList.border = BorderFactory.createMatteBorder(1, 0, 1, 0, JBColor.border())

        // 添加到中间容器
        rootPanel.add(leftJPanel)
        rootPanel.add(dbPanelList)
        // 创建右边容器
        val rightJPanel = JPanel(null)
        rightJPanel.setLocation(leftPanelWidth, 0) // 设置位置
        rightJPanel.setSize(windowWidth - leftPanelWidth, windowHeight)
        rightJPanel.border = null
        // 添加到中间容器
        rootPanel.add(rightJPanel)
        rootPanel.addComponentListener(object : java.awt.event.ComponentAdapter() {
            override fun componentResized(e: java.awt.event.ComponentEvent?) {
                println(rootPanel.size)
                rootPanel.setSize(rootPanel.size.width, rootPanel.size.height)
                windowHeight = rootPanel.size.height
                windowWidth = rootPanel.size.width
                leftJPanel.setSize(leftPanelWidth, windowHeight)    // 左侧面板宽度
                dbPanelList.setSize(windowWidth - leftJPanel.width, windowHeight)    // 左侧面板宽度
            }
        })

        return rootPanel
    }

    override fun createSouthPanel(): JComponent {
        val southPanel = (super.createSouthPanel() as JPanel)
        southPanel.border = BorderFactory.createEmptyBorder(8, 12, 8, 12)
        return southPanel
    }

    /**
     * 校验数据
     * @return 通过必须返回null，不通过返回一个 ValidationInfo 信息
     */
    override fun doValidate(): ValidationInfo? {
        val text = inputTextField!!.text
        return if (StringUtils.isNotBlank(text)) {
            null
        } else {
            ValidationInfo("校验不通过")
        }
    }

    /**
     * 覆盖默认的ok/cancel按钮
     * @return
     */
    override fun createActions(): Array<DialogWrapperAction?> {
        // 设置默认的焦点按钮
        okAction.putValue(DEFAULT_ACTION, true)
        return arrayOf(exitAction, okAction)
    }

    /**
     * 自定义 ok Action
     */
    private inner class CustomOKAction : DialogWrapperAction("确定") {
        override fun doAction(e: ActionEvent) {
            // 点击ok的时候进行数据校验
            val validationInfo = doValidate()
            if (validationInfo != null) {
                Messages.showMessageDialog(validationInfo.message, "校验不通过", Messages.getInformationIcon())
            } else {
                close(CANCEL_EXIT_CODE)
            }
        }
    }
}