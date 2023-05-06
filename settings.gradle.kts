rootProject.name = "otusJava"
include ("hw01-gradle")
include("hw04-generics")
include("hw06-annotaions")
include("hw08-gc:homework")
include("hw10-aop")
include("hw12-solid")
include("hw15-patterns:homework")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
    }
}

