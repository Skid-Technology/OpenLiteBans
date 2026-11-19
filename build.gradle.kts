plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.21-R0.4")
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    compileOnly("net.kyori:adventure-api:4.17.0")
    compileOnly("net.kyori:adventure-text-serializer-gson:4.17.0")
    compileOnly("net.kyori:adventure-text-serializer-legacy:4.17.0")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.3.0")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
    compileOnly("org.slf4j:slf4j-api:2.0.17")
    compileOnly("org.jetbrains:annotations:24.1.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    compileOnly(files("libs/ServerListPlus-3.5.0-Universal.jar"))
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

// Fabric/mixin sources need Yarn + Mojang mapped Minecraft; this build targets the Bukkit jar.
sourceSets.main {
    java {
        exclude(
            "litebans/FabricPlugin.java",
            "litebans/FabricMixinCheckIn.java",
            "litebans/mixin/**",
            "litebans/platform/fabric/**",
            "litebans/platform/bukkit/AuthMeLoginListener.java",
            "litebans/command/CommandPermissionMapper.java",
            "litebans/command/MappedCommand.java",
        )
    }
}

tasks {
    register("printCompileClasspath") {
        doLast { println(sourceSets.main.get().compileClasspath.asPath) }
    }
    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "10000"))
    }
}
