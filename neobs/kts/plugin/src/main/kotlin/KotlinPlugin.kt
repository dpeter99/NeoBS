import com.aperlab.neobs.NeoKey
import com.aperlab.neobs.Runner
import com.aperlab.neobs.plugin.IPlugin
import com.aperlab.neobs.plugin.Plugin
import com.aperlab.neobs.util.Lazy
import com.google.auto.service.AutoService


@Plugin(ID = "kts")
@AutoService(IPlugin::class)
class KotlinPlugin : IPlugin {

    companion object {
        val id = "kts"

        fun makeID(path: String) = NeoKey.ofPlugin("$id/$path");
    }

    override fun RegisterExtensions(runner: Runner) {
        runner.loaderRegistry.register(makeID("kts-loader"), Lazy { KotlinLoader(runner) });
    }
}