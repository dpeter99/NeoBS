import com.aperlab.neobs.NeoKey
import com.aperlab.neobs.NeoBS
import com.aperlab.neobs.plugin.IPlugin
import com.aperlab.neobs.plugin.Plugin

import com.google.auto.service.AutoService


@Plugin(ID = "kts")
@AutoService(IPlugin::class)
class KotlinPlugin : IPlugin {

    companion object {
        val id = "kts"

        fun makeID(path: String) = NeoKey.ofPlugin("$id/$path");
    }

    override fun RegisterExtensions(neoBS: NeoBS) {
        neoBS.loaderRegistry.register(makeID("kts-loader"), com.aperlab.utils.Lazy { KotlinLoader(neoBS) });
    }
}