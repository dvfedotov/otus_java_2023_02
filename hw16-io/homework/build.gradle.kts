dependencies {
    implementation ("org.slf4j:slf4j-api")
    implementation ("org.slf4j:slf4j-log4j12")
    implementation ("org.apache.logging.log4j:log4j")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation("org.projectlombok:lombok")
    implementation ("javax.json:javax.json-api:1.1.4")
    implementation ("com.google.guava:guava")
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("javax.json:javax.json-api")
    implementation ("org.glassfish:jakarta.json")
    implementation ("com.google.protobuf:protobuf-java-util")

    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.junit.jupiter:junit-jupiter-params")
    testImplementation ("org.assertj:assertj-core")
    testImplementation ("org.mockito:mockito-core")
    testImplementation ("org.mockito:mockito-junit-jupiter")
}

