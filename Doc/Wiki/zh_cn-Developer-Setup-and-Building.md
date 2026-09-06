# 💻 开发者环境搭建与编译指南

This guide details environment requirements, Gradle build commands, multi-version anchor subprojects, and automated test execution for developers contributing to **Agrarian Reform**.

---

## 🛠️ 开发环境先决条件

* **Java Development Kit**: JDK 25 (`release = 25`).
* **Gradle Version**: Gradle 9.3+ (managed via Gradle Wrapper `./gradlew`).
* **Fabric Loom**: Loom 1.15.5.
* **Mappings**: Non-obfuscated Modern runtime (Mojang official mappings auto-handled natively).
* **IDE**: IntelliJ IDEA 2025.3+ or Eclipse with Java 25 support.

---

## 🏗️ 多版本子项目结构与编译

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

## 🧪 无头自动化单元测试

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

## 🔒 强制单行开源协议头

All Java source files must include the standard single-line copyright header:

```java
// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
```

---

*See also: [[架构设计与 Mixin 注入参考|zh_cn-Architecture-and-Mixins]] and [[版本兼容性与支持周期|zh_cn-Version-Compatibility]]*.
