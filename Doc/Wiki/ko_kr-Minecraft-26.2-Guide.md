# 📦 Minecraft 26.2 및 26.3 최신 가이드

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 버전 메타데이터 인포박스

| Property | Minecraft 26.2 Build | Minecraft 26.3 Build |
| :--- | :--- | :--- |
| **Minecraft Target** | `26.2` (Stable) | `26.3` (Snapshot 6+) |
| **Mod Version** | `2.2.18+26.2` | `2.2.18+26.3` |
| **JAR File** | `agrarian-reform-2.2.18+26.2.jar` | `agrarian-reform-2.2.18+26.3.jar` |
| **Fabric Loader** | `>=0.16.9` | `>=0.16.9` |
| **Fabric API** | `*` | `*` |
| **DasikLibrary** | `>=1.8.3` | `>=1.8.3` |
| **JDK Required** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Dependency Constraint** | `"minecraft": ">=26.2-"` | `"minecraft": ">=26.3-"` |

---

## 🚀 설치 및 설정

1. **Fabric Loader 설치**: Minecraft 26.2 또는 26.3용 Fabric Loader `0.16.9` 이상을 다운로드하여 설치합니다.
2. **필수 라이브러리 설치**:
   - **Fabric API**를 `.minecraft/mods/` 폴더에 넣습니다.
   - **DasikLibrary** (`1.8.3` 이상)를 `.minecraft/mods/` 폴더에 넣습니다.
3. **Agrarian Reform 설치**: `agrarian-reform-2.2.18+26.2.jar` (또는 `+26.3.jar`)를 `.minecraft/mods/`에 넣습니다.
4. **선택적 클라이언트 향상**: **ModMenu** 및 **YetAnotherConfigLib v3 (YACL)**을 설치하면 게임 내 3탭 그래픽 설정 창을 사용할 수 있습니다.

---

## 🔑 26.2 및 26.3의 핵심 기능

* **범용 작물 완전 자동 감지 및 등록**: 모드 작물 (`#c:crops`)을 무설정으로 자동 감지하여 동적 GameRule 및 GUI 배율 조정 컨트롤 생성.
* **컨티넘 (The Continuum)**: 서브청크 팔레트 기반 오프라인 성장 시뮬레이션 및 30일 경과 타임스탬프 자동 정리.
* **체비쇼프 동심원 유체역학**: 수원 블록 기준 8블록 모세관 관개 범위, 동심 정사각형 링 탐색, 순수 물 필터링 옵션.
* **경작지 내구성 및 선택적 보호**: 소프트 스텝 보호 (가죽 부츠/가벼운 착지) 및 플레이어/길들인 펫 전용 보호 옵션 (`trample_immunity_players_only`).
* **6차원 상호작용 수확 가드**: 주손 디바운스 및 웅크리기 우회를 포함한 우클릭 자동 수확 및 재파종.
* **다모작 생물다양성 보너스**: 인접한 블록에 서로 다른 작물을 식재할 경우 +10% 성장 확률 가산 보너스 지급.

---

## 🔒 ModVersionGuard 보호 기능

Agrarian Reform은 모드 초기화 시점 (`onInitialize()`)에 무종속 `ModVersionGuard` 클래스 검사를 실행합니다:
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
핵심 클래스가 누락된 호환되지 않는 마인크래프트 버전이 로드된 경우, Knot 클래스로더가 즉시 불일치를 감지하여 틱 루프 중 갑작스러운 충돌을 방지하고 명확한 진단 오류 로그를 출력합니다.

---

*참고 항목: [[버전 호환성 및 수명 주기|ko_kr-Version-Compatibility]] 및 [[개발 환경 설정 및 빌드|ko_kr-Developer-Setup-and-Building]]*.
