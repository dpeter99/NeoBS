plugins {
    java
}

dependencies {
    implementation(project(":neobs:api"))
    implementation(project(":neobs:serialization"))
    implementation(project(":neobs:utils"))

    implementation("org.jetbrains:annotations:24.0.0")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("com.google.auto.service:auto-service:1.1.1")
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}