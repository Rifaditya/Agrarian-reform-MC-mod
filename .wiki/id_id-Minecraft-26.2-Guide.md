# 📦 Panduan Modern Minecraft 26.2 & 26.3

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Infobox Metadata Versi

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

## 🚀 Instalasi & Pengaturan

1. **Instal Fabric Loader**: Unduh dan pasang Fabric Loader versi `0.16.9` atau lebih baru untuk Minecraft 26.2 atau 26.3.
2. **Pasang Pustaka yang Diperlukan**:
   - Tempatkan **Fabric API** ke dalam folder `.minecraft/mods/`.
   - Tempatkan **DasikLibrary** (`1.8.3` atau lebih baru) ke dalam folder `.minecraft/mods/`.
3. **Pasang Agrarian Reform**: Masukkan `agrarian-reform-2.2.18+26.2.jar` (atau `+26.3.jar`) ke dalam `.minecraft/mods/`.
4. **Peningkatan Klien Opsional**: Pasang **ModMenu** dan **YetAnotherConfigLib v3 (YACL)** untuk mengakses menu konfigurasi grafis 3 tab.

---

## 🔑 Fitur Utama di 26.2 & 26.3

* **Penemuan Otomatis Tanaman Universal**: Deteksi tanaman mod apa pun (`#c:crops`) tanpa konfigurasi manual, mendaftarkan GameRule dinamis dan kontrol GUI.
* **The Continuum**: Simulasi pertumbuhan offline dengan pemfilteran palet sub-chunk dan pemangkasan otomatis stempel waktu 30 hari.
* **Dinamika Air Chebyshev Konsentris**: Radius irigasi 8 blok untuk sumber air dengan evaluasi cincin persegi dan mode air murni.
* **Ketahanan Tanah & Perlindungan Selektif**: Soft Step (sepatu bot kulit/feather falling) dengan pembatasan pemain dan hewan jinak (`trample_immunity_players_only`).
* **Pengawal Interaksi 6D**: Panen dan tanam ulang klik kanan dengan debounce tangan utama dan pintasan jongkok.
* **Keanekaragaman Hayati Polikultur**: Bonus kecepatan tumbuh +10% saat menanam varietas tanaman berbeda secara berdampingan.

---

## 🔒 Perlindungan ModVersionGuard

Agrarian Reform menjalankan pemeriksaan kelas `ModVersionGuard` bebas ketergantungan selama inisialisasi awal (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Jika versi Minecraft yang tidak kompatibel dan tidak memiliki kelas inti dimuat, Knot classloader mendeteksi ketidakcocokan tersebut secara instan dan mencatat pesan kesalahan yang jelas alih-alih mengalami crash tiba-tiba di tengah permainan.

---

*Lihat juga: [[Kompatibilitas Versi & Siklus Dukungan|id_id-Version-Compatibility]] dan [[Pengaturan Pengembang & Kompilasi|id_id-Developer-Setup-and-Building]]*.
