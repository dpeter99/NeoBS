plugins {
    java
}

dependencies {
    implementation(project(":neobs:api"))
    implementation(project(":neobs:serialization"))

    implementation("com.bertramlabs.plugins:hcl4j:0.7.3")

    //implementation("com.mojang:datafixerupper:1.0.20")

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