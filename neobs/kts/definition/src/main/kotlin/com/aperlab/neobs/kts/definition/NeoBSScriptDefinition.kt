package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.Runner
import com.aperlab.neobs.model.Project as ProjectModel
import com.aperlab.neobs.model.Workspace
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "neobs.kts",
    compilationConfiguration = NeoBSScriptCompilationConfiguration::class
)
abstract class NeoBSScriptDefinition(val runner: Runner) {

    fun Workspace(name: String, configure: Workspace.() -> Unit) {
        val w = Workspace(name)
        configure.invoke(w)
        runner.SetWorkspace(w)
    };

    fun Project(name: String, configure: ProjectModel.() -> Unit) {
        val workspace = runner.GetWorkspace()
        val p = ProjectModel(workspace, name)
        configure.invoke(p)
        workspace.AddProject(p)
    };

}

