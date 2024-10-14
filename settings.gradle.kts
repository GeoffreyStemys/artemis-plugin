rootProject.name = rootDir.name
include("metrics")
include("web")

pluginManagement {
    repositories.gradlePluginPortal()
    includeBuild("gradle/plugins")
}