import com.aperlab.neobs.NeoBS
import com.aperlab.neobs.kts.definition.NeoBSScriptDefinition
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
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
        val neoBS = NeoBS()
        //runner.loader = KotlinLoader(runner);

        val cwd = File("");

        neoBS.openWorkspace(cwd)

        //runner.PrintStructure();
    }
}


fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
    val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<NeoBSScriptDefinition>()
    return BasicJvmScriptingHost().eval(scriptFile.toScriptSource(), compilationConfiguration, null)
}