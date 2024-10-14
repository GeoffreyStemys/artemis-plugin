plugins {
  id("deploy-app")
}

dependencies {
  
  implementation(libs.kotlinLogging)
  
  implementation(libs.artemis)
  implementation(libs.micrometer)
  implementation(libs.micrometerPrometheus)
  implementation(libs.jakarta)
  
  implementation(libs.jetty)
  implementation(libs.jakarta)
  
}

