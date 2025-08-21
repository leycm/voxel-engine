repositories {
    mavenCentral()
    mavenLocal()

    maven("https://leycm.github.io/repository")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-releases/")
}


dependencies {
    if (project.name != "api") implementation(project(":api"))
    if (project.name != "api" && project.name != "common") implementation(project(":api"))

    implementation("org.leycm.frames:the-frame:1.3.15")
    implementation("org.slf4j:slf4j-api:2.0.17")

    compileOnly("io.papermc.paper:paper-api:${project.properties["minecraft_version"]}-R0.1-SNAPSHOT")

    compileOnly("com.github.retrooper:packetevents-spigot:2.8.0")
}