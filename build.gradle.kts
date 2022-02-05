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

import java.util.*

plugins {
    `java-library`
    `maven-publish`
    signing
}

version = "1.6.0"

tasks {
    javadoc {
        options.locale = "en_US"
    }
    create<Jar>("sourceJar") {
        from(sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].java.srcDirs)
        archiveClassifier.set("sources")
    }
    create<Jar>("javadocJar") {
        val javadoc = project.tasks[JavaPlugin.JAVADOC_TASK_NAME] as Javadoc
        dependsOn(javadoc)
        from(javadoc.destinationDir)
        archiveClassifier.set("javadoc")
    }
}

// Integration test for each models.
listOf("V", "Z1").forEach { modelName ->
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
        groupId = "org.theta4j"
        artifactId = "theta-web-api"
        version = project.version as String
        artifact(tasks["sourceJar"])
        artifact(tasks["javadocJar"])
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
        maven { url = uri("$buildDir/repo") }

        maven {
            val props = Properties().apply { rootProject.file("local.properties").inputStream().use(this::load) }
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = props.getProperty("ossrh.user")
                password = props.getProperty("ossrh.password")
            }
        }
    }
}

signing {
    sign(publishing.publications["ossrh"])
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
    implementation("com.google.code.gson", "gson", "2.8.7")
    implementation("com.squareup.okhttp3", "okhttp", "4.9.1")
    implementation("io.github.rburgst", "okhttp-digest", "2.5")
    implementation("commons-io", "commons-io", "2.9.0")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.2")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.7.2")
}
