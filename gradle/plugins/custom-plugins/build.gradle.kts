plugins {
  `kotlin-dsl` // https://plugins.gradle.org/plugin/org.gradle.kotlin.kotlin-dsl
}

val kotlinVersion: String by project
val semverVersion: String by project

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
  implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")

  implementation("com.javiersc.semver:semver-gradle-plugin:$semverVersion")

}
