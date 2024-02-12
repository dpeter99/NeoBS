plugins {
    java
}

dependencies {
    implementation(project(":neobs:serialization"))
    implementation(project(":neobs:utils"))

    implementation("org.jetbrains:annotations:24.0.0")

    //implementation("com.mojang:datafixerupper:1.0.20")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}