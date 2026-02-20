# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

IntelliJ Platform plugin (early stage, still based on the template scaffolding) intended to generate commit messages using AI. Built with Kotlin, targeting IntelliJ IDEA 2025.2+ (build 252+).

Based on the [JetBrains IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template).

## Build Commands

```bash
./gradlew build              # Full build (compile + test + checks)
./gradlew test               # Run tests only
./gradlew runIde             # Launch a sandboxed IDE instance with the plugin loaded
./gradlew buildPlugin        # Package the plugin as a ZIP
./gradlew verifyPlugin       # Run JetBrains plugin verification
./gradlew koverXmlReport     # Generate code coverage report
```

UI tests use a separate run configuration:
```bash
./gradlew runIdeForUiTests   # Launch IDE with Robot Server for UI testing
```

## Tech Stack

- **Language:** Kotlin (JVM toolchain 21, Zulu distribution via Foojay resolver)
- **Build:** Gradle 9.2.1 with Kotlin DSL, version catalog (`gradle/libs.versions.toml`)
- **Platform SDK:** IntelliJ Platform Gradle Plugin 2.x (`org.jetbrains.intellij.platform`)
- **Testing:** JUnit 4 + `BasePlatformTestCase` (IntelliJ test framework)
- **Quality:** Qodana static analysis, Kover code coverage, Codecov integration

## Architecture

- **Package root:** `com.github.jafou2004/AIGlobalPrompt`
- **Plugin manifest:** `src/main/resources/META-INF/plugin.xml` — registers extensions (tool window, startup activity) and declares platform dependencies
- **Resource bundle:** `src/main/resources/messages/MyBundle.properties` — i18n strings accessed via `MyBundle.message()`
- **Plugin description** is extracted from the `<!-- Plugin description -->` section in `README.md` at build time — do not remove those HTML comment markers

## Key Configuration Files

- `gradle.properties` — plugin metadata (group, name, version, platform version, sinceBuild)
- `CHANGELOG.md` — used by the Gradle Changelog Plugin; change notes are rendered into the plugin descriptor
- Plugin signing and publishing use environment variables (`CERTIFICATE_CHAIN`, `PRIVATE_KEY`, `PRIVATE_KEY_PASSWORD`, `PUBLISH_TOKEN`)
