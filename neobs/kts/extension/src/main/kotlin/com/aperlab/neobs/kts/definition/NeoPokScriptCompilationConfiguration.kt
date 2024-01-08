package com.aperlab.neobs.kts.definition

import kotlinx.coroutines.runBlocking
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.*
import kotlin.script.experimental.dependencies.maven.MavenDependenciesResolver
import kotlin.script.experimental.jvm.JvmDependency
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

object neobsScriptCompilationConfiguration : ScriptCompilationConfiguration(
        {
            // Implicit imports for all scripts of this type
            defaultImports("com.aperlab.neobs.kts.definition.*")
            jvm {
                // Extract the whole classpath from context classloader and use it as dependencies
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
            compilerOptions.append("-Xadd-modules=ALL-MODULE-PATH")
            // Callbacks
            refineConfiguration {
                // Process specified annotations with the provided handler
                //onAnnotations(DependsOn::class, Repository::class, handler = ::configureMavenDepsOnAnnotations)
            }

            ide {
                acceptedLocations(ScriptAcceptedLocation.Everywhere)
            }
        }
)