plugins {
  id("org.jetbrains.kotlin.jvm")
}

group = "ch.brw.hive"
val javaVersion = rootDir.resolve(".sdkmanrc")
  .useLines { it.toList() }
  .first { it.contains("java=") }
  .split("=")[1]
  .split(".")[0].toInt()

kotlin {
  jvmToolchain(javaVersion)
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
}
