plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
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

javafx {
    version = "21.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}
