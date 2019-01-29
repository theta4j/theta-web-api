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
    `java-library`
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

version = "1.0.0"

tasks {
    create<Jar>("sourceJar") {
        from(sourceSets["main"].java.srcDirs)
        classifier = "sources"
    }
    create<Jar>("javadocJar") {
        val javadoc = project.tasks["javadoc"] as Javadoc
        dependsOn(javadoc)
        from(javadoc.destinationDir)
        classifier = "javadoc"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "org.theta4j"
            artifactId = "theta-web-api"
            version = project.version as String
            artifact(tasks["sourceJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set("RICOH THETA Web API Client")
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
    }

    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri("$buildDir/repo")
        }
    }
}

bintray {
    user = properties["bintray.user"] as String
    key = properties["bintray.key"] as String
    setPublications("maven")
    pkg.apply {
        userOrg = "theta4j"
        repo = "maven"
        name = "theta-web-api"
        version.apply {
            name = project.version as String
            vcsTag = "v${project.version}"
            gpg.apply {
                sign = true
            }
            mavenCentralSync.apply {
                sync = true
                user = properties["ossrh.user"] as String
                password = properties["ossrh.password"] as String
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
    implementation("com.google.code.gson", "gson", "2.8.5")
    implementation("com.squareup.okhttp3", "okhttp", "3.12.1")
    implementation("com.burgstaller", "okhttp-digest", "1.18")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.0-RC1")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.4.0-RC1")
}