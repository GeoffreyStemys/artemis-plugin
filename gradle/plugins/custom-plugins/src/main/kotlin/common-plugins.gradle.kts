plugins {
  id("kotlin-base")
  `maven-publish`
  
  id("com.javiersc.semver")
  id("project-report")
  jacoco
}

val nexusRepoReleases: String by project
val nexusRepoSnapshots: String by project
val nexusRepoStemysPlatform: String by project
val nexusRepoNexusStemys: String by project
val nexusUsername: String by project
val nexusPassword: String by project
val eclipseReleaseRepository: String by project

repositories {
  mavenLocal()
  maven {
    url = uri(nexusRepoStemysPlatform)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri(nexusRepoNexusStemys)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri(nexusRepoReleases)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri(nexusRepoSnapshots)
    credentials {
      username = nexusUsername
      password = nexusPassword
    }
  }
  maven {
    url = uri("https://repo.akka.io/maven/")
  }
  mavenCentral()
  maven {
    url = uri(eclipseReleaseRepository)
  }
}

publishing {
  repositories {
    maven {
      val theVersion = project.version.toString()
      val nexusRepoUrl = when (theVersion.lowercase().endsWith("snapshot")) {
        true -> nexusRepoSnapshots
        false -> nexusRepoReleases
      }
      url = uri(nexusRepoUrl)
      credentials {
        username = nexusUsername
        password = nexusPassword
      }
    }
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
  dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
}
