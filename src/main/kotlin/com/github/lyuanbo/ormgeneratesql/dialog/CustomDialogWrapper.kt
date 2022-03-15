package com.github.lyuanbo.ormgeneratesql.dialog

import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.JBColor
import org.apache.commons.lang.StringUtils
import java.awt.event.ActionEvent
import javax.swing.*


/**
 * @author wengyongcheng
 * @since 2020/3/2 10:36 下午
 */
open class CustomDialogWrapper(project: Project?) : DialogWrapper(true) {
    private var inputTextField: JTextField? = null
    private var okAction: CustomOKAction? = null
    private var exitAction: DialogWrapperExitAction? = null

    init {
        init()
        // 设置窗口标题
        title = "数据库配置"
        // 设置窗口大小
        super.setSize(preferredSize.width, preferredSize.height)
        // 内边距
println(project)
        // 禁用窗口最大化
        // isResizable = false
    }

    /**
     * 创建视图
     * @return
     */
    override fun createCenterPanel(): JComponent? {
        val buttonHeight = 50 // 底部按钮容器高度
        val leftPanelWidth = 180 // 左侧面板宽度
        val leftPanelHeight = preferredSize.height - buttonHeight - 26 // 左侧面板高度, 减去26是因为标题栏高度

        val rootPanel = JPanel(null)
        rootPanel.setBounds(0, 0, preferredSize.width, preferredSize.height)
        rootPanel.setSize(preferredSize.width, preferredSize.height)
        rootPanel.border = javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, JBColor.border())
        // 创建左边表格
        val leftJPanel = JPanel(null)
        leftJPanel.setSize(leftPanelWidth, leftPanelHeight)

        // 工具栏
        val toolJPanel = JPanel(null)
        toolJPanel.setSize(leftPanelWidth, 28)
        toolJPanel.border = javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, JBColor.border())
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
        rightJPanel.setSize(preferredSize.width - leftPanelWidth, leftPanelHeight)
        rightJPanel.border = javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, JBColor.border())
        // 添加到中间容器
        rootPanel.add(rightJPanel)


        return rootPanel
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
        exitAction = DialogWrapperExitAction("取消", CANCEL_EXIT_CODE)
        okAction = CustomOKAction()
        // 设置默认的焦点按钮
        okAction!!.putValue(DEFAULT_ACTION, true)
        return arrayOf(exitAction!!, okAction!!)
    }

    /**
     * 自定义 ok Action
     */
    protected inner class CustomOKAction() : DialogWrapperAction("确定") {
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