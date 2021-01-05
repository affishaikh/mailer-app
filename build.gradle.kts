plugins {
    application
    kotlin("jvm") version "1.4.0"
}

application.mainClassName = "org.example.Application"

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    implementation("javax.mail:mail:1.4")
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = "org.example.Application"
    }

    // To add all of the dependencies otherwise a "NoClassDefFoundError" error
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
