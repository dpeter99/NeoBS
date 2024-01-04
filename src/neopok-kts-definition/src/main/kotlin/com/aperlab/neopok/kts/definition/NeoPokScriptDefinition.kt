package com.aperlab.neopok.kts.definition

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
        fileExtension = "neopok.kts",
        compilationConfiguration = NeoPokScriptCompilationConfiguration::class
)
abstract class NeoPokScriptDefinition {

    fun Workspace( ) = Unit;

}

