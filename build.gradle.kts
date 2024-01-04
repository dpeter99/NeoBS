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
}
