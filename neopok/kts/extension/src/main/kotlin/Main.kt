import com.aperlab.neopok.Runner
import com.aperlab.neopok.kts.definition.NeoPokScriptDefinition
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

fun main(vararg args: String) {
    //TODO: This needs to be inverted and started from the host
    // For the that we will need to find the loader from the class path
    // No idea how to do that XD

    if (args.size != 1) {
        println("usage: <app> <script file>")
    } else {
        val scriptFile = File(args[0])
        println("Executing script $scriptFile")

        val runner = Runner()
        runner.loader = LoaderPlugin(runner);
        runner.OpenWorkspace(scriptFile)

        runner.PrintStructure();
    }
}


fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
    val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<NeoPokScriptDefinition>()
    return BasicJvmScriptingHost().eval(scriptFile.toScriptSource(), compilationConfiguration, null)
}