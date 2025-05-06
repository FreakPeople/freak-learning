plugins {
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
}

/**
 * 자바, 코틀린 컴파일러가 JDK21 사용하도록 지정.
 */
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

/**
 * 코틀린 컴파일러가 명시적으로 JDK21 사용하도록 지정.
 */
kotlin {
    jvmToolchain(21)
}

dependencies {
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

/**
 * 코틀린 컴파일러 추가옵션 설정.
 * java 코드에서 @Nullable 또는 @NonNull 어노테이션이 명시된 부분에 대해 코틀린 null 처리방식을 엄격하게 적용한다.
 */
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}