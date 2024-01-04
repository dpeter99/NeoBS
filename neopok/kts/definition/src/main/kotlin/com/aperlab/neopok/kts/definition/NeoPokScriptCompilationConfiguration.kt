package com.aperlab.neopok.kts.definition

import kotlinx.coroutines.runBlocking
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.*
import kotlin.script.experimental.dependencies.maven.MavenDependenciesResolver
import kotlin.script.experimental.jvm.JvmDependency
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

object NeoPokScriptCompilationConfiguration : ScriptCompilationConfiguration(
        {
            // Implicit imports for all scripts of this type
            defaultImports(DependsOn::class, Repository::class)
            jvm {
                // Extract the whole classpath from context classloader and use it as dependencies
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
            // Callbacks
            refineConfiguration {
                // Process specified annotations with the provided handler
                onAnnotations(DependsOn::class, Repository::class, handler = ::configureMavenDepsOnAnnotations)
            }

            ide {
                acceptedLocations(ScriptAcceptedLocation.Everywhere)
            }
        }
)

private val resolver = CompoundDependenciesResolver(FileSystemDependenciesResolver(), MavenDependenciesResolver())

// The handler that is called during script compilation in order to reconfigure compilation on the fly
fun configureMavenDepsOnAnnotations(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> {
    val annotations = context.collectedData?.get(ScriptCollectedData.collectedAnnotations)?.takeIf { it.isNotEmpty() }
            ?: return context.compilationConfiguration.asSuccess() // If no action is performed, the original configuration should be returned
    return runBlocking {
        // resolving maven artifacts using annotation arguments
        resolver.resolveFromScriptSourceAnnotations(annotations)
    }.onSuccess {
        context.compilationConfiguration.with {
            // updating the original configurations with the newly resolved artifacts as compilation dependencies
            dependencies.append(JvmDependency(it))
        }.asSuccess()
    }
}