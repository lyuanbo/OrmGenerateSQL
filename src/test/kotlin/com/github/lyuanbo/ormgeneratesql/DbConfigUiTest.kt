package com.github.lyuanbo.ormgeneratesql

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class DbConfigUiTest : BasePlatformTestCase() {
    fun testUI() {
        PanelExample()
    }
}