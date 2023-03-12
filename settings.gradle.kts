rootProject.name = "otus-kotlin-2022-12-yamangulov"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.openapi.generator") version openapiVersion apply false
    }
}

include("documentation")
include("api")
include("common")
include("mappers")

