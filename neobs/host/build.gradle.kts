
plugins {
    java
}

dependencies {
    implementation(project(":neobs:api"))

    runtimeOnly(project(":neobs:kts:extension"))
}