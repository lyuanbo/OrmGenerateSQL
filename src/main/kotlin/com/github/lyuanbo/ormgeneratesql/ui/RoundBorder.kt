package com.github.lyuanbo.ormgeneratesql.ui

import java.awt.*
import javax.swing.border.Border


/**
 * Swing
 * 设置圆角边框（可以自定义边框的颜色）
 * 可以为button，文本框等人以组件添加边框
 * 使用方法：
 * JButton close = new JButton(" 关 闭 ");
 * close.setOpaque(false);// 设置原来按钮背景透明
 * close.setBorder(new RoundBorder());黑色的圆角边框
 * close.setBorder(new RoundBorder(Color.RED)); 红色的圆角边框
 *
 * @author Monsoons
 */
class RoundBorder(
    private var color: Color,
    private var background: Color?,
    private var arcWidth: Int,
    private var arcHeight: Int
) : Border {
    override fun getBorderInsets(c: Component): Insets {
        return Insets(0, 0, 0, 0)
    }

    override fun isBorderOpaque(): Boolean {
        return false
    }

    override fun paintBorder(c: Component, g: Graphics, x: Int, y: Int, width: Int, height: Int) {
        val g2 = g.create() as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = color // 画线
        g2.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight)
        g2.color = background // 画背景
        g2.fillRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight)
        g2.dispose()
    }
}
