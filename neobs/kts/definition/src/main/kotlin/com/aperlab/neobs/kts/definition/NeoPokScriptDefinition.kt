package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.Runner
import com.aperlab.neobs.model.Project
import com.aperlab.neobs.model.Workspace
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "neobs.kts",
    compilationConfiguration = neobsScriptCompilationConfiguration::class
)
abstract class neobsScriptDefinition(val runner: Runner) {

    fun Workspace(name: String, configure: Workspace.() -> Unit) {
        val w = Workspace(name)
        configure.invoke(w)
        runner.SetWorkspace(w)
    };

    fun Project(name: String, configure: Project.() -> Unit) {
        val p = Project(name)
        configure.invoke(p)
        runner.GetWorkspace().AddProject(p)
    };

}

