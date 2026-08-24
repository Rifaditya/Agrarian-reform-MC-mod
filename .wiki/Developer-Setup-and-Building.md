# 💻 Developer Setup & Building

This guide details environment requirements, Gradle build commands, and testing procedures for developers contributing to **Agrarian Reform**.

---

## 🛠️ Environment Prerequisites

* **Java Development Kit**: JDK 25 (Strict Requirement).
* **Gradle Version**: Gradle 9.3+ (managed via Gradle Wrapper `./gradlew`).
* **Fabric Loom**: Loom 1.15+.
* **IDE**: IntelliJ IDEA 2025.3+ or Eclipse with Java 25 support.

---

## 🏗️ Building from Source

To compile Agrarian Reform from source:

```bash
# Clone the repository
git clone https://github.com/Rifaditya/Agrarian-reform-MC-mod.git
cd Agrarian-reform-MC-mod/agrarian-reform

# Execute build with Gradle wrapper (without daemon lock)
./gradlew build --no-daemon
```

The output release artifact will be generated in `build/libs/agrarian-reform-2.2.4+26.2.jar`.

---

## 🧪 Automated GameTest Verification

Following the **Automated GameTest Verification Law**, unit tests for crop catch-up math and hydration logic are executed prior to release:

```bash
# Run headless unit & GameTest suites
./gradlew test
```

---

## 🔒 Mandatory License Header

All Java source files must include the standard single-line copyright header:

```java
// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
```

---

*See also: [[Architecture & Mixins|Architecture-and-Mixins]] and [[API & Addon Integration|API-and-Addon-Integration]]*.
