plugins {
    val kotlinVersion = "1.9.23"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.16.0"
}

group = "top.xuansu.mirai.exchangeRateHelper"
version = "0.0.2"

repositories {
    //maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    compileOnly("com.squareup.okhttp3:okhttp:4.12.0")
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
    excludeDependency("org.slf4j", "slf4j-api")
}