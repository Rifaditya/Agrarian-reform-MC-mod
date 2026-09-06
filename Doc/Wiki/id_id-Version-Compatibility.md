# 🔄 Kompatibilitas Versi & Siklus Dukungan

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Matriks Kompatibilitas Versi Minecraft

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ Prinsip 1 Jar 1 Version & Batasan Terbuka

Agrarian Reform secara ketat mematuhi kebijakan **1 Jar 1 Version** dan mandat **kompatibilitas maju**:
1. **Build Khusus**: Setiap file JAR dikompilasi, diuji, dan diarsipkan khusus untuk rilis Minecraft yang ditargetkan (`agrarian-reform-2.2.18+26.2.jar` dan `agrarian-reform-2.2.18+26.3.jar`).
2. **Batasan Terbuka**: Dalam `fabric.mod.json`, ketergantungan Minecraft ditentukan dengan batas terbuka:
   ```json
   "minecraft": ">=26.2-"
   ```
   dan untuk 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   Ini mencegah penguncian Fabric Loader saat bermain di versi patch minor.
3. **Wildcard Dependensi**: Dependensi seperti `dasik-library` menggunakan wildcard (`"*"`) untuk menghindari crash palsu saat pustaka diperbarui.

---

## 🔄 Arsitektur Modern & Codec SavedData

* **Penamaan Rilis Tahunan (Annual Drop)**: Minecraft beralih ke format `Year.Drop.Patch`. Versi 26.2 dan 26.3 merupakan rilis drop aktif saat ini.
* **Era Tanpa Obfuskasi**: Kode 26.x beroperasi penuh pada pemetaan resmi Mojang. Istilah lama Yarn telah sepenuhnya dihapus.
* **Modernisasi SavedData**: Penyimpanan persisten dunia menggunakan catatan Mojang `SavedDataType` dengan Codec eksplisit:
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

*Lihat juga: [[Panduan Minecraft 26.2 & 26.3|id_id-Minecraft-26.2-Guide]] dan [[Integrasi API & Pengaya|id_id-API-and-Addon-Integration]]*.
