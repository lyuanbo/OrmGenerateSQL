package com.github.lyuanbo.ormgeneratesql.services

import com.intellij.openapi.project.Project
import com.github.lyuanbo.ormgeneratesql.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
