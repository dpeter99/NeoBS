package com.aperlab.neopok.kts.definition

import com.aperlab.neopok.Runner
import com.aperlab.neopok.model.Project
import com.aperlab.neopok.model.Workspace
import java.util.Optional
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
        fileExtension = "neopok.kts",
        compilationConfiguration = NeoPokScriptCompilationConfiguration::class
)
abstract class NeoPokScriptDefinition(val runner: Runner) {

    fun Workspace(name: String, configure: Workspace.()->Unit ) {
        val w = Workspace(name);
        configure.invoke(w);
        runner.SetWorkspace(w);
    };

    fun Project(name: String, configure: Project.()->Unit ) {
        val p = Project(name);
        configure.invoke(p);
        runner.GetWorkspace().AddProject(p);
    };

}

