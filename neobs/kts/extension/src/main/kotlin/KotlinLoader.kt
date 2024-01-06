import com.aperlab.neobs.ILoaderPlugin
import com.aperlab.neobs.Runner
import com.aperlab.neobs.kts.definition.neobsScriptDefinition
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class KotlinLoader(val runner: Runner) : ILoaderPlugin {



    override fun LoadFile(file: File) {
        var res = evalFile(file);

        res.reports.forEach {
            if (it.severity > ScriptDiagnostic.Severity.WARNING) {
                println(" : ${it.message}" + if (it.exception == null) "" else ": ${it.exception}")
            }
        }
    }

    override fun CanLoad(f: File?): Boolean {
        return f?.name?.endsWith("neobs.kts") ?: false;
    }


    fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
        //val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<neobsScriptDefinition>()
        return BasicJvmScriptingHost().evalWithTemplate<neobsScriptDefinition>(scriptFile.toScriptSource(), evaluation = {
            constructorArgs(runner)
        })
    }

}