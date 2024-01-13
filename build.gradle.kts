plugins {
    kotlin("jvm") //apply false
}

java {
    withSourcesJar()
}

repositories {
    mavenCentral()
}

subprojects {
    group = "com.aperlab.neobs"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<Jar>{
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

dependencies {
    //implementation(kotlin("script-runtime"))
    implementation(project(":neobs:kts:definition"))
}

val runDir = rootDir.resolve("tests/basic")

/*
kotlin {
    sourceSets.maybeCreate("main").kotlin.apply {
        srcDir(runDir)
        srcDir(rootDir.resolve("include"))
    }
}
*/