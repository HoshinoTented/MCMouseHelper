import net.minecraftforge.gradle.common.util.MinecraftExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val JAVA_VERSION = JavaVersion.VERSION_1_8

plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("net.minecraftforge.gradle") version "4.1.14"
    kotlin("jvm") version "1.5.30"
}

group = "top.hoshino9.mousehelper"
version = "1.0"

val classesOutput = project.layout.buildDirectory.dir("classes").get().asFile

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
    destinationDir = classesOutput
}

sourceSets {
    main {
        output.setResourcesDir(classesOutput)
    }
}

java {
    sourceCompatibility = JAVA_VERSION
    targetCompatibility = JAVA_VERSION
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JAVA_VERSION.majorVersion
    targetCompatibility = JAVA_VERSION.majorVersion
}

extensions.configure<MinecraftExtension>("minecraft") {
    mappings("snapshot", "20180704-1.12")

    runs {
        create("client") {
            workingDirectory("run")
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")
        }

        create("server") {
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")
            property("forge.logging.console.level", "debug")
        }
    }
}

val shadowJar: ShadowJar by tasks
val build by tasks

task("forgeShadow", ShadowJar::class) {
    archiveClassifier.set("mod")

    from(zipTree(project.buildDir.resolve("reobfJar").resolve("output.jar")))
    configurations.add(project.configurations.getByName("runtimeClasspath"))

    dependencies {
        include {
            it.moduleGroup == "org.jetbrains.kotlin"
        }
    }
}.dependsOn("reobfJar")

repositories {
    maven("https://maven.aliyun.com/repository/central")
    mavenCentral()
}

dependencies {
    val minecraft = configurations.getByName("minecraft")

    minecraft("net.minecraftforge", "forge", "1.12.2-14.23.5.2855")

    kotlin("stdlib", "1.5.30")
}

