import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply false
}

subprojects{

    group = "com.aperlab.neopok"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    tasks {

        // Preserve parameter names in the bytecode
        withType<JavaCompile>().configureEach {
            options.compilerArgs.add("-parameters")
        }

        withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-java-parameters")
            }
        }
    }
}
