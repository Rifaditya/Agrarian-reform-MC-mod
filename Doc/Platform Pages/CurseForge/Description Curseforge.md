<div align="center">

<!-- Banner placeholder — replace URL when banner is uploaded -->
<!-- ![Agrarian Reform Banner](https://files.catbox.moe/9hc07g.png) -->

</div>
<p align="center">
    <a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>

# ⚒️ Agrarian Reform

**No Backports:** This mod targets **Minecraft 26.1+** (Snapshot 10). Older versions are unsupported.

> **"The world should not wait for you to watch it."**

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

Part of the **Vanilla Outsider Collection** — mods that refine the vanilla experience with modern standards.

---

## ✨ Features

### 🕰️ The Continuum (Offline Persistence)
Stop waiting for chunks to load for your crops to grow. **The Continuum** introduces true offline persistence:
- **Timestamped State**: When a chunk unloads, the exact state of every crop is saved.
- **Time Delta Simulation**: Upon re-entering the area, the mod calculates exactly how much time has passed and simulates growth stages accordingly.
- **Performance Optimized**: Uses a throttled, distributed update system to ensure no lag spikes upon returning to a large farm.

### 🧤 Soil Resilience (Smart Trample Logic)
Protect your fields without sacrificing immersion:
- **Soft Step**: Wearing **Leather Boots** or having the **Feather Falling** enchantment prevents crops from being trampled.
- **Total Immunity**: A dedicated GameRule allows admins to disable trampling entirely for a more relaxed experience.

### 💧 Hydro-Dynamics (Advanced Irrigation)
Water behaves more logically, rewarding strategic placement and natural weather:
- **Deep Reach**: Hydration sources now reach up to **8 blocks** away (configurable).
- **Rainfall Celebration**: Natural rain hydrates all exposed farmland and provides a slight growth acceleration.

### 🍀 Polyculture (Biodiversity Bonus)
Nature thrives in variety. Planting different types of crops adjacent to each other provides a **Biodiversity Bonus**, speeding up growth rates and rewarding organic farm designs over monoculture grids.

---

## ⚙️ Configuration (Native Game Rules)

No messy config files. Everything lives in the **Edit Game Rules** screen or via standard commands.

- `agrarian_reform:hydration_source_range`: How far still water hydrates farmland. (Default: 8)
- `agrarian_reform:hydration_flowing_range`: How far flowing water hydrates farmland. (Default: 4)
- `agrarian_reform:total_trample_immunity`: Toggle whether trampling is disabled entirely.
- `agrarian_reform:growth_biodiversity_bonus`: Adjust the speed bonus from mixed crop planting.
- `agrarian_reform:rain_growth_acceleration`: Adjust the growth boost during rainy weather.

<hr>

<h2>☕ Support</h2>

<p>If you enjoy <strong>Agrarian Reform</strong> and the <strong>Vanilla Outsider Collection</strong>, consider fueling future updates!</p>

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&amp;logo=ko-fi&amp;logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

<blockquote><p><strong>🇮🇩 Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!</p></blockquote>

<blockquote><p><strong>Dedicated Server Hosting Partner:</strong><br>Looking for a high-performance server to host your community or play with friends? Check out <strong>BisectHosting</strong> for 1-click modpack installations, automated backups, and 24/7 dedicated customer support. Use promo code <strong><code>Dasik</code></strong> for 25% off your first month!</p></blockquote>

<h3>💬 Join the Community &amp; Get Support</h3>
<p>Looking for help, want to test early beta builds, or vote on upcoming features? Join our official Discord community!</p>
<p align="center">
  <a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener">
    <img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&amp;logo=discord&amp;logoColor=white" alt="Join Official Discord">
  </a>
</p>

<hr>

<h2>📜 Credits &amp; Modpack Permissions</h2>

<table>
  <thead>
    <tr>
      <th>Property</th>
      <th>Information</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Creator / Author</strong></td>
      <td><strong>Dasik</strong> (Rifaditya)</td>
    </tr>
    <tr>
      <td><strong>Community</strong></td>
      <td><a href="https://discord.gg/EV99bgAFqb" target="_blank" rel="noopener">Official Discord</a></td>
    </tr>
    <tr>
      <td><strong>Collection</strong></td>
      <td><a href="https://www.curseforge.com/members/dasikigaijin/projects">Vanilla Outsider</a></td>
    </tr>
    <tr>
      <td><strong>License</strong></td>
      <td><a href="https://www.gnu.org/licenses/gpl-3.0.html">GNU General Public License v3.0 (GPLv3)</a></td>
    </tr>
    <tr>
      <td><strong>Source Code</strong></td>
      <td><a href="https://github.com/Rifaditya/Agrarian-reform-MC-mod">GitHub - Rifaditya/Agrarian-reform-MC-mod</a></td>
    </tr>
    <tr>
      <td><strong>Issue Tracker</strong></td>
      <td><a href="https://github.com/Rifaditya/Agrarian-reform-MC-mod/issues">GitHub Issues</a></td>
    </tr>
    <tr>
      <td><strong>Documentation / Wiki</strong></td>
      <td><a href="https://github.com/Rifaditya/Agrarian-reform-MC-mod/wiki">GitHub Wiki</a></td>
    </tr>
  </tbody>
</table>

<blockquote>
  <p><strong>📦 Modpack Permissions &amp; Distribution:</strong><br>
  You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (<strong>CurseForge</strong> or <strong>Modrinth</strong>). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.</p>
  <p><strong>⚖️ License &amp; Fork Guidelines (No Zero-Change Re-uploads):</strong><br>
  This project is open-source under the <strong>GNU GPLv3</strong>. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports&mdash;provided your project remains open-source under GPLv3 with proper attribution.<br>
  <strong>However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.</strong></p>
</blockquote>

<hr>

<p align="center">
  <strong>Made with ❤️ for the Minecraft community</strong><br>
  <em>Part of the Vanilla Outsider Collection</em>
</p>
