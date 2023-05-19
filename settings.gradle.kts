rootProject.name = "otus-kotlin-2022-12-yamangulov"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val bmuschkoVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("org.openapi.generator") version openapiVersion apply false

        id("com.bmuschko.docker-java-application") version bmuschkoVersion apply false
        id("com.bmuschko.docker-remote-api") version bmuschkoVersion apply false
    }
}

include("documentation")
include("api")
include("common")
include("mappers")
include("app-ktor")
include("stubs")
include("app-kafka")
include("biz")
include("lib-cor")

