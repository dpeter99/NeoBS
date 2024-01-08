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
