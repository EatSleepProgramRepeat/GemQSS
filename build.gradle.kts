plugins {
    id("java")
    id("application")
//    id("com.github.johnrengelman.shadow") version "8.4.1"
}

group = "com.GemQSS"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.GemQSS.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.GemQSS.Main"
    }
}