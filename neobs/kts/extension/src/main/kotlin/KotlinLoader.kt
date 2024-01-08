import com.aperlab.neobs.FileLoadingException
import com.aperlab.neobs.ILoaderPlugin
import com.aperlab.neobs.Runner
import com.aperlab.neobs.kts.definition.NeoBSScriptDefinition
import java.io.File
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

class KotlinLoader(val runner: Runner) : ILoaderPlugin {



    override fun LoadFile(file: File) {
        var res = evalFile(file);

        res.reports.forEach {
            if (it.severity > ScriptDiagnostic.Severity.WARNING) {
                println(" : ${it.message}" + if (it.exception == null) "" else ": ${it.exception} ${it.location} ${it.sourcePath}")
            }
        }

        if(res.reports.any { it.severity > ScriptDiagnostic.Severity.WARNING }){
            throw FileLoadingException("Errors while running kts script")
        }
    }

    override fun CanLoad(f: File?): Boolean {
        return f?.name?.endsWith("neobs.kts") ?: false;
    }


    fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
        return BasicJvmScriptingHost().evalWithTemplate<NeoBSScriptDefinition>(
                scriptFile.toScriptSource(),
                compilation = {
                    jvm {
                        dependenciesFromCurrentContext(wholeClasspath = true)
                    }
                    compilerOptions.append("-Xadd-modules=ALL-MODULE-PATH")
                },
                evaluation = {
                    constructorArgs(runner)
                })
    }

}