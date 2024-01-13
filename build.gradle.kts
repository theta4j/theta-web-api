/*
 * Copyright (C) 2019 theta4j project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    `maven-publish`
    signing
}

version = "2.0.0-alpha"

tasks {
    val sourcesJar by registering(Jar::class) {
        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    val javadocJar by registering(Jar::class) {
        dependsOn("dokkaJavadoc")
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }
}

// Integration test for each model.
listOf("X").forEach { modelName ->
    val name = "integrationTest$modelName"
    sourceSets.create(name) {
        compileClasspath += sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].output + configurations.testCompileClasspath
        runtimeClasspath += output + compileClasspath + configurations.testRuntimeClasspath
    }
    tasks.create<Test>(name) {
        description = "Runs the integration tests for THETA $modelName"
        group = JavaBasePlugin.VERIFICATION_GROUP
        testClassesDirs = sourceSets[name].output.classesDirs
        classpath = sourceSets[name].runtimeClasspath
        outputs.upToDateWhen { false }
    }
}

publishing {
    publications.create<MavenPublication>("ossrh") {
        from(components["java"])
        artifact(tasks["sourcesJar"])
        artifact(tasks["javadocJar"])
        groupId = "org.theta4j"
        artifactId = "theta-web-api"
        version = project.version as String
        pom {
            name.set("THETA Web API Client")
            description.set("Client implementation of RICOH THETA API.")
            url.set("https://github.com/theta4j/theta-web-api")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
                developers {
                    developer {
                        name.set("theta4j Project")
                        email.set("info@theta4j.org")
                    }
                }
                scm {
                    url.set("https://github.com/theta4j/theta-web-api.git")
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("$buildDir/repo")
        }

        if (hasProperty("ossrh.user") && hasProperty("ossrh.password")) {
            maven {
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = property("ossrh.user") as String
                    password = property("ossrh.password") as String
                }
            }
        }
    }
}

signing {
    publishing.publications["ossrh"]?.let { sign(it) }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("io.github.rburgst:okhttp-digest:3.0")
    implementation("commons-io:commons-io:2.11.0")

    implementation("org.slf4j:slf4j-api:2.0.6")
    testImplementation("org.slf4j:slf4j-simple:2.0.6")

    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0")
}
