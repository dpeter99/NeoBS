package com.aperlab.neobs.kts.definition

import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvm.jvmTarget


object NeoBSScriptCompilationConfiguration : ScriptCompilationConfiguration(
        {

            // Implicit imports for all scripts of this type
            defaultImports(
                    "com.aperlab.neobs.kts.definition.*",
                    "com.aperlab.neobs.model.*",
                    "com.aperlab.neobs.extensions.npm.project.*")
            jvm {
                // Extract the whole classpath from context classloader and use it as dependencies
                dependenciesFromCurrentContext(wholeClasspath = true, unpackJarCollections = true)
                jvmTarget.put("17")
            }
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