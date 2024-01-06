import com.aperlab.neopok.ID
import com.aperlab.neopok.Runner
import com.aperlab.neopok.plugin.IPlugin
import com.aperlab.neopok.plugin.Plugin
import com.aperlab.neopok.util.Lazy
import com.google.auto.service.AutoService


@Plugin(ID = "kts")
@AutoService(IPlugin::class)
class KotlinPlugin : IPlugin {

    companion object {
        val id = "kts"

        fun makeID(path: String) = ID.ofPlugin("$id/$path");
    }

    override fun RegisterExtensions(runner: Runner) {
        runner.loaderRegistry.register(makeID("kts-loader"), Lazy { KotlinLoader(runner) });
    }
}