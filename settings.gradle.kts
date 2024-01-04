import org.gradle.kotlin.dsl.support.listFilesOrdered

pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.22"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "NeoPok"

include("src:neopok-host")
findProject(":src:neopok-host")?.name = "neopok-host"

include("src:neopok-kts-definition")
findProject(":src:neopok-kts-definition")?.name = "neopok-kts-definition"

include("src:neopok-kts-host")
findProject(":src:neopok-kts-host")?.name = "neopok-kts-host"

//include("tests:basic")