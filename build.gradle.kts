import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") //apply false
}

java{
    withSourcesJar()
}

subprojects{

    group = "com.aperlab.neobs"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    /*
    tasks {
        withType<JavaCompile> {
            targetCompatibility = "1.8"
            sourceCompatibility = "1.8"
        }

        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-java-parameters")
            }
        }
    }
     */
}
