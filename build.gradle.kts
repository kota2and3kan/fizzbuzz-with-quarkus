import org.gradle.internal.os.OperatingSystem

plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-picocli")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit")
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

val os = OperatingSystem.current()
if (os.isLinux) {
    // Use Mandrel container to build a native image on Linux.
    System.setProperty("quarkus.native.container-build", "true")
    System.setProperty("quarkus.native.builder-image", "mandrel")
} else if (os.isMacOsX) {
    // Use GraalVM to build a native image on macOS. Don't use container image to build.
    System.setProperty("quarkus.native.container-build", "false")
} else if (os.isWindows) {
    // This project does not support building native images on Windows at this time.
}
