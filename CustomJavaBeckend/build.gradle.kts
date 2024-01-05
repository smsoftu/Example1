plugins {
	java
	id("org.springframework.boot") version "3.2.2-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.apache.httpcomponents.client5:httpclient5")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
