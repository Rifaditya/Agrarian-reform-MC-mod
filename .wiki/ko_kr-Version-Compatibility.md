# 🔄 버전 호환성 및 수명 주기 지원

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 마인크래프트 버전 호환성 매트릭스

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version 원칙 및 개방형 버전 범위

Agrarian Reform은 엄격한 **1 Jar 1 Version 원칙**과 **전방 호환성 규약**을 준수합니다:
1. **전용 타깃 빌드**: 컴파일된 각 JAR 파일은 특정 마인크래프트 릴리스 전용으로 빌드, 테스트 및 아카이브됩니다 (`agrarian-reform-2.2.18+26.2.jar` 및 `agrarian-reform-2.2.18+26.3.jar`).
2. **개방형 버전 하한선**: `fabric.mod.json`에서 마인크래프트 종속성은 개방형 하한선으로 지정됩니다:
   ```json
   "minecraft": ">=26.2-"
   ```
   26.3 타깃:
   ```json
   "minecraft": ">=26.3-"
   ```
   이를 통해 마이너 패치 업데이트 시 Fabric Loader의 오작동 버전 잠금을 방지합니다.
3. **종속성 와일드카드**: `dasik-library`와 같은 핵심 라이브러리 종속성에 와일드카드(`"*"`)를 사용하여 라이브러리 패치 업데이트 시 충돌을 방지합니다.

---

## 🔄 현대적 아키텍처 및 SavedData Codec

* **연간 드롭 버전 체계 (Annual Drop)**: Minecraft는 `Year.Drop.Patch` 체계로 전환되었습니다. 버전 26.2 및 26.3은 현재 활성화된 메인 드롭입니다.
* **비난독화 런타임 시대**: 26.x 코드베이스는 공식 Mojang 매핑에서 실행되며, 레거시 Yarn 용어는 완전히 제거되었습니다.
* **SavedData 현대화**: 영구 세계 데이터 저장은 구형 NBT 처리 대신 명시적 Codec을 갖춘 Mojang `SavedDataType` 레코드를 사용합니다:
```java
public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
    Identifier.fromNamespaceAndPath("agrarian_reform", "continuum"),
    ContinuumData::create,
    Codec.unboundedMap(Codec.STRING, Codec.LONG).xmap(
        map -> {
            ContinuumData data = new ContinuumData();
            map.forEach((k, v) -> data.timestamps.put(Long.parseLong(k), v));
            return data;
        },
        data -> {
            Map<String, Long> map = new HashMap<>();
            data.timestamps.forEach((k, v) -> map.put(k.toString(), v));
            return map;
        }
    ),
    DataFixTypes.SAVED_DATA_MAP_DATA
);
```

---

*참고 항목: [[Minecraft 26.2 및 26.3 가이드|ko_kr-Minecraft-26.2-Guide]] 및 [[API 및 애드온 통합|ko_kr-API-and-Addon-Integration]]*.
