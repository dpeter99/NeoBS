
plugins {
    java
    id("java")
    application
    distribution
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("com.aperlab.neobs.host.Entry")
}

tasks.run.configure {
    workingDir = rootProject.file("tests/basic")
    args("vis")
}

dependencies {
    implementation(project(":neobs:api"))

    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")

    implementation("hu.webarticum:tree-printer:3.2.0")

    runtimeOnly(project(":neobs:kts:plugin"))
    runtimeOnly(project(":neobs:extensions:npm"))
}