pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.22"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "neobs"

include(
        "neobs:api",
        "neobs:host",
        "neobs:extensions:npm",
        "neobs:kts:definition",
        "neobs:kts:plugin"
)
include("neobs:hcl-lang")
findProject(":neobs:hcl-lang")?.name = "hcl-lang"
include("neobs:serialization")
findProject(":neobs:serialization")?.name = "serialization"
include("neobs:utils")
findProject(":neobs:utils")?.name = "utils"
