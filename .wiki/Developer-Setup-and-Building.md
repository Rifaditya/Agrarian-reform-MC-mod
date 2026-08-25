# 💻 Developer Setup & Building

This guide details environment requirements, Gradle build commands, multi-version anchor subprojects, and automated test execution for developers contributing to **Agrarian Reform**.

---

## 🛠️ Environment Prerequisites

* **Java Development Kit**: JDK 25 (`release = 25`).
* **Gradle Version**: Gradle 9.3+ (managed via Gradle Wrapper `./gradlew`).
* **Fabric Loom**: Loom 1.15.5.
* **Mappings**: Non-obfuscated Modern runtime (Mojang official mappings auto-handled natively).
* **IDE**: IntelliJ IDEA 2025.3+ or Eclipse with Java 25 support.

---

## 🏗️ Multi-Version Subproject Structure

Agrarian Reform follows the strict **1 Jar 1 Version Law** with dedicated subproject folders per Minecraft version anchor:

```
Agrarian Reform/
├── Agrarian Reform v26.2/
│   └── agrarian-reform/ (Targets MC 26.2 / ">=26.2-")
├── Agrarian Reform v26.3/
│   └── agrarian-reform/ (Targets MC 26.3 / ">=26.3-")
└── Archive Jar of all versions/
    ├── MC 26.2/
    └── MC 26.3/
```

### Building From Source

```bash
# Clone the repository
git clone https://github.com/Rifaditya/Agrarian-reform-MC-mod.git

# Build MC 26.2 JAR
cd "Agrarian Reform v26.2/agrarian-reform"
./gradlew check test build --no-daemon

# Build MC 26.3 JAR
cd "../../Agrarian Reform v26.3/agrarian-reform"
./gradlew check test build --no-daemon
```

Compiled JARs (`agrarian-reform-2.2.18+26.2.jar` and `agrarian-reform-2.2.18+26.3.jar`) are automatically verified and archived into `Archive Jar of all versions/`.

---

## 🧪 Headless Automated Unit Testing

Following the **Automated GameTest Verification Law**, headless test suites are executed during `./gradlew test` and `./gradlew check`:

1. **`ContinuumMathTest.java`**:
   * Asserts offline time delta scaling across standard and modded growth curves.
   * Asserts frozen multiplier ($-1 \to \text{effectiveDelta} = 0$).
   * Asserts 30-day stale timestamp pruning ceiling ($51,840,000\text{ ticks}$).
   * Asserts concentric Chebyshev hydration ring geometries ($r = \max(|dx|, |dz|)$).
2. **`AgrarianConfigTest.java`**:
   * Asserts default configuration schema invariants and version stability.
   * Asserts multiplier hierarchy resolution (forced specific override, frozen, global fallback).
   * Asserts modded crop discovery deduplication.
   * Asserts thread-safe dirty-tracking state transitions.

```bash
# Run all automated tests
./gradlew test
```

---

## 🔒 Mandatory License Header

All Java source files must include the standard single-line copyright header:

```java
// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
```

---

*See also: [[Architecture & Mixins|Architecture-and-Mixins]] and [[Version Compatibility|Version-Compatibility]]*.
