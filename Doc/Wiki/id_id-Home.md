# 🌾 Agrarian Reform: Bumi yang Hidup

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **"Dunia seharusnya tidak perlu menunggu Anda memperhatikannya untuk tetap bertumbuh."**

Selamat datang di Wiki resmi **Agrarian Reform**! **Agrarian Reform** adalah mod simulasi dan imersi pertanian yang mengubah pertanian Minecraft dari mesin berbasis kehadiran pemain menjadi ekosistem yang hidup dan persisten. Mod ini menghadirkan **The Continuum** (simulasi pertumbuhan luar jaringan yang dioptimalkan dengan palet sub-chunk), **registrasi otomatis tanaman universal** (deteksi dinamis dan pengali khusus untuk tanaman mod apa pun), ketahanan tanah mendalam dengan perlindungan injakan selektif untuk pemain dan hewan jinak, dinamika air Chebyshev konsentris, serta bonus keanekaragaman hayati polikultur.

> 📌 **Pernyataan Sumber Kode Repositori**: Dokumentasi dalam Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru atau fitur pengembangan sebelum rilis publik di CurseForge dan Modrinth.

---

## 🛠️ Gambaran Umum & Spesifikasi Teknis

| Parameter | Spesifikasi |
| :--- | :--- |
| **Pengenal Mod (Mod ID)** | `agrarian_reform` |
| **Versi Game yang Didukung** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Versi Mod Saat Ini** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Pemuat Mod (Mod Loader)** | Fabric Loader (`>=0.16.9`) |
| **Target Lingkungan Java** | Java 25 (Ketat) |
| **Ketergantungan Utama** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Integrasi Opsional** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Penulis & Pimpinan** | **Dasik (Rifaditya)** |
| **Lisensi** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Direktori Versi Minecraft

* [[Panduan Minecraft 26.2 & 26.3|id_id-Minecraft-26.2-Guide]] — Panduan komprehensif untuk build modern Minecraft 26.2 dan 26.3.
* [[Kompatibilitas Versi & Siklus Dukungan|id_id-Version-Compatibility]] — Matriks dukungan versi, batasan terbuka, dan keamanan Knot classloader.

---

## 🎮 Direktori Panduan Pemain & Fitur

* [[The Continuum (Pertumbuhan Luar Jaringan)|id_id-The-Continuum-Offline-Persistence]] — Pemfilteran palet sub-chunk, stempel waktu bongkar, batas pemangkasan 30 hari, antrean berhemat, dan jaminan nol penulisan disk.
* [[Registri Tanaman & Tanaman Universal|id_id-Plant-Registry-and-Crop-Types]] — Mesin penemu tanaman $O(1)$, inspeksi properti, pengindeksan tag `#c:crops`, dan pengali per tanaman.
* [[Dinamika Air & Irigasi Lanjutan|id_id-Hydro-Dynamics-and-Irrigation]] — Cincin kapiler Chebyshev konsentris (sumber 8 blok, mengalir 4), elevasi 3D ($y \in [-1, 1]$), dan mode air murni.
* [[Ketahanan Tanah & Perlindungan Injakan|id_id-Soil-Resilience-and-Trample-Logic]] — Perlindungan Soft Step dengan sepatu bot kulit/feather falling, pencegahan cepat bertelanjang kaki, dan filter pemain/hewan peliharaan.
* [[Panen Klik-Kanan & Tanam Ulang Otomatis|id_id-Right-Click-Harvest-and-Replanting]] — Perlindungan interaksi 6 dimensi, debounce tangan utama, jalan pintas jongkok, dan panen klik kanan.
* [[Polikultur & Bonus Keanekaragaman Hayati|id_id-Polyculture-and-Biodiversity]] — Insentif penanaman tanaman campur dan bonus peluang tumbuh +10%.
* [[Penaburan Benih & Penanaman Rumput|id_id-Seed-Sowing-and-Grass-Cultivation]] — Menabur benih pertanian pada tanah untuk memulihkan blok rumput secara instan.
* [[Tepung Tulang Universal|id_id-Universal-Bone-Meal]] — Pupuk tepung tulang untuk tebu, kaktus, nether wart, dan tanaman merambat.
* [[Pengali Pertumbuhan Global|id_id-Global-Growth-Multiplier]] — Penyetelan kecepatan global dan per tanaman: 0% (nonaktif), -1 (beku), 100% (vanilla), dipercepat (>100%).
* [[Performa & Pembatasan Antrean|id_id-Performance-and-Queue-Throttling]] — Perlindungan anggaran tick server (`CROPS_PER_TICK = 5`) dan antrean tugas bebas kunci.
* [[Aturan Permainan (GameRules)|id_id-GameRules]] — Daftar lengkap 15 aturan permainan `agrarian_reform:*`, aturan dinamis, dan sakelar diagnostik.
* [[Perintah Brigadier & Administrasi|id_id-Commands]] — Perintah administrasi Brigadier dan penggantian konfigurasi langsung.
* [[Konfigurasi Dua Tingkat|id_id-Configuration]] — Templat JSON Schema v2, penyimpanan dirty-tracking, dan antarmuka GUI YACL 3 tab.
* [[Estetika & Umpan Balik Lingkungan|id_id-Aesthetics-and-Ambient-Feedback]] — Audio gemerisik saat melewati tanaman, partikel vitalitas, dan embun pagi.
* [[Kemajuan & Progresi|id_id-Advancements]] — Integrasi alami dengan kemajuan Pertanian (Husbandry) vanilla.

---

## 💻 Direktori Referensi Pengembang

* [[Pengaturan Pengembang & Kompilasi|id_id-Developer-Setup-and-Building]] — Kompilasi dengan JDK 25, Gradle 9.3+, Loom 1.15+, dan pengujian unit JUnit 5 headless.
* [[Arsitektur & Target Mixin|id_id-Architecture-and-Mixins]] — Hierarki paket kode dan tabel pemetaan injeksi Mixin secara menyeluruh.
* [[Integrasi API & Pengaya|id_id-API-and-Addon-Integration]] — Fasade API DasikLibrary, tag datapack kustom (`#agrarian_reform:soft_step_boots`), dan kait ekstensi.

---

<p align="center">
  <em>Agrarian Reform dikembangkan sebagai bagian dari koleksi Instant Gratification dan Vanilla Outsider.</em>
</p>
