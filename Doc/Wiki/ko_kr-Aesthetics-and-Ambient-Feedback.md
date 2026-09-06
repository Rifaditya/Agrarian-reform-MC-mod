# 🎨 심미적 피드백 및 환경 음향

**Agrarian Reform** enriches the visual and auditory feedback of farming to make fields feel dynamic, responsive, and alive.

---

## 🌾 작물 통과 시 바스락거리는 환경음

When players or entities walk through fully matured crops, the engine triggers physical crop rustle sound effects (`SoundEvents.GRASS_HIT`):

```java
if (isMaxAge && DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.AMBIENT_CROP_RUSTLE)) {
    if (SoundHelper.shouldPlayRustle(entityId, time)) {
        if (entity.getDeltaMovement().horizontalDistance() > 0.01D) {
            float pitch = 0.8F + serverLevel.getRandom().nextFloat() * 0.4F;
            float volume = 0.2F + serverLevel.getRandom().nextFloat() * 0.1F;
            serverLevel.playSound(null, pos, SoundEvents.GRASS_HIT, SoundSource.BLOCKS, volume, pitch);
        }
    }
}
```

* **Pitch Variation**: Pitch varies dynamically between $0.8$ and $1.2$ to avoid auditory repetition.
* **Debounce Throttle**: `SoundHelper` throttles sound triggers per entity ID to prevent audio clutter when running through dense fields.

---

## ✨ 생명력 녹색 파티클

When a crop advances a growth stage (either natively, via rain growth acceleration, or via bone meal), **Happy Villager** green sparkles (`ParticleTypes.HAPPY_VILLAGER`) burst from the crop:

* **Particle Count**: 3 particles per growth event.
* **Spread Area**: $0.25\text{m} \times 0.25\text{m} \times 0.25\text{m}$ around block center.
* **GameRule Control**: Toggle via `/gamerule agrarian_reform:ambient_vitality_particles false`.

---

## 🌅 시각적 아침 이슬 효과

Farmland rendering is updated subtly at sunrise to simulate morning dew across moist tilled soil, visually celebrating early morning harvests.

---

*See also: [[경작지 내구성 및 짓밟기 보호|ko_kr-Soil-Resilience-and-Trample-Logic]] and [[게임 규칙 (GameRules) 레퍼런스|ko_kr-GameRules]]*.
