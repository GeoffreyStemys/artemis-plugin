import org.gradle.api.file.DuplicatesStrategy.*

plugins {
  id("common-plugins")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      groupId = project.group as String
      artifactId = project.name
      version = project.version.toString()
      from(components["java"])
    }
  }
}

tasks.named("publish") {
  dependsOn("build") // creates implicit dependencies
  mustRunAfter("build") // controls the execution order
}
