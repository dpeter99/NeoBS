plugins {
    java
    application
}

application {
    mainClass.set("com.aperlab.neobs.host.Main")
}

tasks.run.configure {
    workingDir = rootProject.file("tests/basic")
}

dependencies {
    implementation(project(":neobs:api"))

    runtimeOnly(project(":neobs:kts:extension"))
}