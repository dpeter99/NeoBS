package com.aperlab.neobs.kts.definition

import com.aperlab.neobs.NeoBS
import com.aperlab.neobs.api.Project
import com.aperlab.neobs.model.Workspace
import java.io.File
import java.nio.file.Path
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "neobs.kts",
    compilationConfiguration = NeoBSScriptCompilationConfiguration::class
)
abstract class NeoBSScriptDefinition(val neoBS: NeoBS, val scriptFile: File) {

    fun workspace(name: String, configure: Workspace.() -> Unit) {
        val w = Workspace(name)
        configure.invoke(w)
        neoBS.workspace = w
    };

    fun file(path: String): File {
        return Path.of(scriptFile.parent, path).toFile();
    }

    inline fun <reified T: IProjectBuilder<out Project<*>>> project(name: String, configure: T.() -> Unit)
    {
        println("[DEBUG] Running project config in file:" + scriptFile.name)
        val proj = constructNewInstance<T>()

        proj.setWorkspace(neoBS.workspace)
        proj.setName(name)

        configure.invoke(proj)

        neoBS.workspace.addProject(proj.build())
    }

    inline fun <reified T: IProjectBuilder<out Project<*>>> project(configure: T.() -> Unit)
    {
        try {
            println("[DEBUG] Running project config in file:" + scriptFile.name)

            val proj = constructNewInstance<T>()

            proj.setWorkspace(neoBS.workspace)

            configure.invoke(proj)

            neoBS.workspace.addProject(proj.build())
            println("[DEBUG] Added project config in file:" + scriptFile.name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inline fun <reified T : IProjectBuilder<out Project<*>>> constructNewInstance() : T
    {
        val kClass = T::class
        val constructor = kClass.constructors.first()

        val proj: T

        println(
            "[DEBUG] Making object: " +
                    "${kClass.qualifiedName} " +
                    "calling constructor: ${constructor.name} " +
                    "with args: ${constructor.parameters.map { "${it.name}:${it.type}" }}"
        )
        try {
            proj = constructor.call(neoBS.workspace, scriptFile.parentFile);
        } catch (e: Exception) {
            throw e;
        }
        println("[DEBUG] Constructed project config in file: ${scriptFile.name}")

        return proj;
    }
}

