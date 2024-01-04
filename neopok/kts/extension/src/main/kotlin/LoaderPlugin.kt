import com.aperlab.neopok.ILoaderPlugin
import com.aperlab.neopok.Runner
import com.aperlab.neopok.kts.definition.NeoPokScriptDefinition
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

class LoaderPlugin(val runner: Runner) : ILoaderPlugin {



    override fun LoadFile(file: File) {
        var res = evalFile(file);

        res.reports.forEach {
            if (it.severity > ScriptDiagnostic.Severity.WARNING) {
                println(" : ${it.message}" + if (it.exception == null) "" else ": ${it.exception}")
            }
        }
    }


    fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
        //val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<NeoPokScriptDefinition>()
        return BasicJvmScriptingHost().evalWithTemplate<NeoPokScriptDefinition>(scriptFile.toScriptSource(), evaluation = {
            constructorArgs(runner)
        })
    }

}