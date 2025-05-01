plugins {
    id("java")
}

group = "yjh.ontongsal"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
    }
}