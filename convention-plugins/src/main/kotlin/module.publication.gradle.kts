import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Formz Kotlin")
            description.set("A Multiplatform Form Validation Library")
            url.set("https://github.com/zainulhassan815/formz-kotlin")

            licenses {
                license {
                    name.set("Apache License")
                    url.set("https://opensource.org/license/apache-2-0")
                }
            }
            developers {
                developer {
                    id.set("zainulhassan815")
                    name.set("Zain Ul Hassan")
                    url.set("https://github.com/zainulhassan815")
                    email.set("zainulhassan4330@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/zainulhassan815/formz-kotlin")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
