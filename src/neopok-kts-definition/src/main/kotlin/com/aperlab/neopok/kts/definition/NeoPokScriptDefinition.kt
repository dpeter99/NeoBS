package com.aperlab.neopok.kts.definition

import com.aperlab.neopok.model.Workspace
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
        fileExtension = "neopok.kts",
        compilationConfiguration = NeoPokScriptCompilationConfiguration::class
)
abstract class NeoPokScriptDefinition {

    fun Workspace(configure: Workspace.()->Unit ) = Unit;

}

