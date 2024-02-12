import com.aperlab.neobs.loader.FileLoadingException
import com.aperlab.neobs.loader.ILoaderPlugin
import com.aperlab.neobs.NeoBS
import com.aperlab.neobs.kts.definition.NeoBSScriptDefinition
import java.io.File
import java.lang.reflect.InvocationTargetException
import kotlin.io.path.Path
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class KotlinLoader(val neoBS: NeoBS) : ILoaderPlugin {



    override fun LoadFile(file: File) {

        val res: ResultWithDiagnostics<EvaluationResult>
        try {
            res = evalFile(file);
        } catch (e: InvocationTargetException) {
            throw e.targetException
        }

        res.reports.forEach {
            if (it.severity > ScriptDiagnostic.Severity.WARNING) {
                val filePath = Path(it.sourcePath ?: "").fileName.toString();
                val fileLink = "(${filePath}:${it.location?.start?.line ?: '?'})"
                println("$fileLink ${it.message}" + if (it.exception == null) "" else ": ${it.exception} ${it.location}")
            }
        }

        if(res.reports.any { it.severity > ScriptDiagnostic.Severity.WARNING }){
            throw FileLoadingException("Errors while running kts script")
        }
    }

    override fun CanLoad(f: File?): Boolean {
        return f?.name?.endsWith("neobs.kts") ?: false;
    }


    private fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
        return BasicJvmScriptingHost().evalWithTemplate<NeoBSScriptDefinition>(
                scriptFile.toScriptSource(),
                compilation = {
                    jvm {
                        dependenciesFromCurrentContext(wholeClasspath = true)
                    }
                    compilerOptions.append("-Xadd-modules=ALL-MODULE-PATH")
                },
                evaluation = {
                    constructorArgs(neoBS, scriptFile)
                    jvm{

                    }
                })
    }

}