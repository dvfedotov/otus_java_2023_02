plugins {
    id("java")
}

group = "ru.otus"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.slf4j:slf4j-api")
    implementation ("org.slf4j:slf4j-log4j12")
    implementation ("org.apache.logging.log4j:log4j")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    implementation("commons-collections:commons-collections:3.2.2")
    testImplementation ("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-junit-jupiter")
}