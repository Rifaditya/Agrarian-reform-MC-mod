# 🌾 Agrarian Reform: 살아 숨쉬는 대지

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **"세상은 당신이 바라보고 있을 때만 움직여서는 안 됩니다."**

**Agrarian Reform** 공식 위키에 오신 것을 환영합니다! **Agrarian Reform**은 마인크래프트의 농경 시스템을 플레이어의 근접 대기에 의존하는 기계적 장치에서 벗어나, 지속적이고 살아 숨쉬는 생태계로 탈바꿈시키는 농업 시뮬레이션 및 몰입형 모드입니다. 본 모드는 **컨티넘 (The Continuum)**(서브청크 팔레트 최적화 오프라인 성장 따라잡기 시뮬레이션), **범용 모드 작물 자동 감지 및 등록**(외부 모드 작물 자동 탐색 및 개별 성장 배율 튜닝), 플레이어 및 길들인 펫 전용 짓밟기 보호를 포함한 정밀 경작지 물리, 체비쇼프 동심원 유체역학, 다모작 생물다양성 보너스를 제공합니다.

> 📌 **저장소 소스 코드 고지 사항**: 본 위키의 문서는 **저장소의 현재 소스 코드 상태**를 반영하고 있으며, CurseForge 및 Modrinth의 공개 빌드 이전의 개발 중인 기능이나 최신 커밋이 포함될 수 있습니다.

---

## 🛠️ 모드 개요 및 기술 사양

| 매개변수 | 상세 사양 |
| :--- | :--- |
| **모드 식별자 (Mod ID)** | `agrarian_reform` |
| **지원 마인크래프트 버전** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **현재 모드 버전** | `2.2.18+26.2` / `2.2.18+26.3` |
| **모드 로더** | Fabric Loader (`>=0.16.9`) |
| **대상 Java 버전** | Java 25 (엄격 준수) |
| **필수 핵심 종속성** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **선택적 통합 지원** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **제작자 및 개발 리드** | **Dasik (Rifaditya)** |
| **라이선스** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 마인크래프트 버전 디렉터리

* [[Minecraft 26.2 및 26.3 가이드|ko_kr-Minecraft-26.2-Guide]] — 현대 Minecraft 26.2 및 26.3 빌드를 위한 종합 안내서.
* [[버전 호환성 및 수명 주기|ko_kr-Version-Compatibility]] — 버전 지원 매트릭스, 개방형 종속성 범위, Knot 클래스로더 안전 가드.

---

## 🎮 플레이어 및 핵심 기능 가이드

* [[컨티넘 (오프라인 성장 지속)|ko_kr-The-Continuum-Offline-Persistence]] — 서브청크 팔레트 사전 필터링, 언로드 타임스탬프, 30일 상한 정리, 대기열 조절 및 0-디스크 기록 최적화.
* [[작물 레지스트리 및 범용 작물|ko_kr-Plant-Registry-and-Crop-Types]] — $O(1)$ 동적 작물 감지, 블록 속성 검사, `#c:crops` 태그 인덱싱 및 작물별 개별 배율.
* [[유체역학 및 고급 관개 시스템|ko_kr-Hydro-Dynamics-and-Irrigation]] — 체비쇼프 동심 정사각형 고리 관개 (수원 8블록, 흐르는 물 4블록), 3D 높낮이 ($y \in [-1, 1]$), 순수 물 모드.
* [[경작지 내구성 및 짓밟기 보호|ko_kr-Soil-Resilience-and-Trample-Logic]] — 가죽 부츠/가벼운 착지를 통한 소프트 스텝 보호, 맨발 빠른 검사, 플레이어/펫 보호.
* [[우클릭 수확 및 자동 재파종|ko_kr-Right-Click-Harvest-and-Replanting]] — 6차원 상호작용 보호, 주손 디바운스, 웅크리기 우회 및 우클릭 자동 수확/재파종.
* [[다모작 및 생물다양성 보너스|ko_kr-Polyculture-and-Biodiversity]] — 다양한 작물 혼합 식재 장려 및 +10% 성장 확률 가산 보너스.
* [[씨앗 파종 및 잔디 블록 육성|ko_kr-Seed-Sowing-and-Grass-Cultivation]] — 농업용 씨앗을 흙 블록에 우클릭하여 즉시 잔디 블록으로 전환.
* [[범용 골분 시스템|ko_kr-Universal-Bone-Meal]] — 사탕수수, 선인장, 네더 와트, 덩굴에 대한 골분 비료 지원 확대.
* [[글로벌 성장 배율 및 개별 조정|ko_kr-Global-Growth-Multiplier]] — 글로벌 및 개별 작물 속도 조정: 0%(비활성화), -1(동결), 100%(바닐라 기준), 가속(>100%).
* [[성능 최적화 및 대기열 조절|ko_kr-Performance-and-Queue-Throttling]] — 서버 틱 예산 보호 (`CROPS_PER_TICK = 5`), 비차단 작업 대기열.
* [[게임 규칙 (GameRules) 레퍼런스|ko_kr-GameRules]] — 15개의 네임스페이스 게임 규칙 `agrarian_reform:*`, 동적 규칙 및 디버그 토글 레퍼런스.
* [[Brigadier 명령어 및 서버 관리|ko_kr-Commands]] — Brigadier 명령어 기반 런타임 서버 관리 및 동적 설정 재정의.
* [[2계층 설정 시스템|ko_kr-Configuration]] — Schema v2 JSON 설정 파일, 더티 트래킹 자동 저장, 3탭 YACL GUI 설정 화면.
* [[심미적 피드백 및 환경 음향|ko_kr-Aesthetics-and-Ambient-Feedback]] — 작물 사이를 지날 때의 바스락거리는 음향, 활력 파티클, 아침 이슬 시각 효과.
* [[발전 과제 연동|ko_kr-Advancements]] — 바닐라 농업 발전 과제와의 자연스러운 연동.

---

## 💻 개발자 기술 레퍼런스

* [[개발 환경 설정 및 빌드|ko_kr-Developer-Setup-and-Building]] — JDK 25, Gradle 9.3+, Loom 1.15+ 기반 빌드 및 JUnit 5 헤드리스 단위 테스트.
* [[아키텍처 및 Mixin 주입 대상|ko_kr-Architecture-and-Mixins]] — 패키지 구조 및 Mixin 주입 대상 상세 매핑 테이블.
* [[API 및 애드온 통합|ko_kr-API-and-Addon-Integration]] — DasikLibrary API 파사드, 데이터팩 태그 (`#agrarian_reform:soft_step_boots`), 확장 훅.

---

<p align="center">
  <em>Agrarian Reform은 Instant Gratification 및 Vanilla Outsider 컬렉션의 일환으로 개발되었습니다.</em>
</p>
