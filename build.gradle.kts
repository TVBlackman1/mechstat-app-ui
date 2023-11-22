import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
//    id("kotlinx-serialization")
    kotlin("plugin.serialization") version "1.8.20"

}

group = "tvblackman1.ru"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jitpack.io")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
//    implementation("com.github.Gurupreet:FontAwesomeCompose:1.0.0")
//    implementation("androidx.compose.material:material-icons-extended:1.5.3")
    implementation(compose.desktop.currentOs)

    val voyagerVersion = "1.0.0-rc10"
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    // BottomSheetNavigator
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    val ktor_version = "1.6.4"
    implementation("io.ktor:ktor-client-core:$ktor_version")

// HTTP engine: The HTTP client used to perform network requests.

    implementation("io.ktor:ktor-client-android:$ktor_version")

// The serialization engine used to convert objects to and from JSON.
    implementation("io.ktor:ktor-client-serialization:$ktor_version")

// Logging
    implementation("io.ktor:ktor-client-logging:$ktor_version")

    implementation("io.ktor:ktor-client-cio:$ktor_version")

    val serialization_version = "1.3.0"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "mechstat"
            packageVersion = "1.0.0"
        }
    }
}
