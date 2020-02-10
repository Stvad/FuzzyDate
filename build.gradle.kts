plugins {
    id("org.jetbrains.kotlin.js") version "1.3.61"
}

group = "org.stvad"
version = "0.1.5"
description = "Alfred workflow to convert human date to computer date"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

kotlin {
    sourceSets["main"].dependencies {
        implementation(npm("alfy", "0.9.1"))
        implementation(npm("chrono-node", "1.4.2"))
        implementation(npm("dateformat", "*"))
    }
    target.useCommonJs()

    target.nodejs {
    }
}

val packageLocation = "build/js/packages/${project.name}"
val copyWorkflowResources by tasks.registering(Copy::class) {
    from("README.md", "workflow_resources", "illustrations")
    into(packageLocation)
}

tasks.assemble { dependsOn(copyWorkflowResources) }
