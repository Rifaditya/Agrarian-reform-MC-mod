<p align="center">
<a href="https://discord.gg/EV99bgAFqb"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&amp;logo=discord&amp;logoColor=white" alt="Join Discord"></a>
<a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&amp;logo=fabric" alt="Requires Fabric API"></a>
<a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-purple?style=for-the-badge" alt="Requires Dasik Library"></a>
<img src="https://img.shields.io/badge/Environment-Client_&amp;_Server-success?style=for-the-badge" alt="Client &amp; Server">
<img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&amp;logo=java" alt="Java 25">
<img src="https://img.shields.io/badge/License-GPLv3-red?style=for-the-badge" alt="License GPLv3">
<img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>
<h2>🌾 Agrarian Reform</h2>
<blockquote><p><strong>"Farming that honors the rhythm of the soil."</strong></p></blockquote>
<hr>
<h2>📖 Introduction</h2>
<p>In vanilla Minecraft, farming quickly devolves into tedious busywork: breaking crops leaves empty holes in your fields, water source mechanics force rigid, ugly 9x9 irrigation grids, accidental jumps turn rich tilled soil into useless dirt, and crops instantly stop growing the moment you log off or walk a few chunks away.</p>
<p><strong>Agrarian Reform</strong> completely revitalizes Minecraft's agriculture under the <strong>Vanilla Outsider</strong> philosophy. It introduces fluid right-click harvesting and instant replanting, realistic expanded hydrological irrigation, natural polyculture biodiversity growth bonuses, trample protection, universal bone-meal support for wild flora, and <strong>The Continuum</strong>—an intelligent offline persistence engine that simulates crop growth while you sleep or explore far-off dimensions.</p>
<blockquote><p><strong>1 Jar 1 Version Policy:</strong> I build <strong>1 dedicated JAR for each Minecraft version</strong> (e.g. MC 26.1, MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation. <strong>Dependency Callout:</strong> <em> <strong>Minecraft 26.x+ (Modern Era):</strong> Requires both <strong>Fabric API</strong> AND <strong>Dasik Library</strong> (<code>v1.8.15+</code>). </em> <strong>Minecraft 1.20.1 / 1.21.x (Legacy Era):</strong> Self-contained build requiring only <strong>Fabric API</strong>. * <strong>Dedicated Server Ready:</strong> Both client and server compatible. Vanilla clients can join servers running Agrarian Reform!</p></blockquote>
<p>Part of the <strong>Vanilla Outsider Collection</strong> — mods that deepen vanilla immersion through native gameplay loops.</p>
<hr>
<h2>✨ Features</h2>
<h3>🌾 Right-Click Quick Harvest &amp; Fluid Replanting</h3>
<ul>
  <li><strong>One-Click Replanting:</strong> Right-click any fully mature crop (Wheat, Carrots, Potatoes, Beetroot, Nether Wart, Cocoa, Torchflowers, Pitcher Plants) to instantly harvest drops and replant the seed at age 0 in a single fluid gesture.</li>
  <li><strong>Main-Hand Debounce:</strong> Equipped with anti-double-click protection to prevent accidental duplicate harvests or unintended seed placement.</li>
  <li><strong>Fortune &amp; Tool Synergy:</strong> If harvesting with a tool or hoe in hand, drops correctly calculate Fortune enchantments without consuming tool durability.</li>
</ul>
<h3>💧 Hydro-Dynamics &amp; Capillary Irrigation</h3>
<ul>
  <li><strong>Chebyshev Ring Irrigation:</strong> Expands the effective irrigation range of still water source blocks from vanilla's cramped 4 blocks to a spacious <strong>8 blocks</strong> by default (<code>agrarian_reform:hydration_source_range</code>), allowing expansive pastoral landscape farms.</li>
  <li><strong>Flowing Water Capillary Action:</strong> Flowing water provides up to <strong>4 blocks</strong> of capillary hydration (<code>agrarian_reform:hydration_flowing_range</code>), making natural streams and canals viable for authentic terrace farming.</li>
  <li><strong>Organic Moisture Retention:</strong> Farmland retains moisture significantly longer without harsh instant dry-outs.</li>
</ul>
<h3>🍃 Polyculture &amp; Biodiversity Growth Bonuses</h3>
<ul>
  <li><strong>Ecosystem Vitality:</strong> Planting diverse crops adjacently grants a <strong>+10% growth probability bonus</strong> (<code>agrarian_reform:growth_biodiversity_bonus</code>), encouraging natural mixed kitchen gardens and historically accurate companion planting over industrial monoculture grids.</li>
  <li><strong>Rain Growth Acceleration:</strong> Crops exposed to open sky during rainstorms receive extra random growth ticks (<code>agrarian_reform:rain_growth_acceleration</code>).</li>
</ul>
<h3>🛡️ Soil Resilience &amp; Trample Protection</h3>
<ul>
  <li><strong>Trample Immunity Guard:</strong> Optional configurable immunity against farmland destruction from player jumps, sprint-slides, and falling entities (<code>agrarian_reform:total_trample_immunity</code>).</li>
  <li><strong>Targeted Protection:</strong> Restrict trample immunity to players and tamed pets while leaving wild mobs subject to soil damage (<code>agrarian_reform:trample_immunity_players_only</code>).</li>
</ul>
<h3>⏳ The Continuum: Offline Growth Persistence</h3>
<ul>
  <li><strong>Time-Aware Simulation:</strong> Calculates the exact offline time delta when a world or chunk reloads, advancing crops through their growth stages according to natural tick probabilities. Your fields flourish even while you explore the Nether or sleep in real life!</li>
  <li><strong>Zero Chunk Freezing:</strong> Eliminates the frustration of returning home after hours of adventuring only to find your crops in the exact same immature state.</li>
</ul>
<h3>🦴 Universal Bone-Meal Cultivation</h3>
<ul>
  <li>Accelerates growth on non-natively bonemealable flora (Cactus, Sugar Cane, Nether Wart, Vines) using standard bone-meal item interactions.</li>
</ul>
<h3>🧩 Compatibility &amp; HUD Integration</h3>
<ul>
  <li><strong>100% Server-Side Compatible:</strong> Fully functional on dedicated servers; connecting vanilla clients experience smooth harvesting and visuals without client mods.</li>
  <li><strong>ModMenu &amp; YACL / Cloth Config:</strong> In-game graphical configuration screen in singleplayer to easily customize world defaults.</li>
  <li><strong>WTHIT / Jade Compatibility:</strong> Displays accurate growth percentages and moisture states in third-party tooltips.</li>
</ul>
<hr>
<h2>📊 Quick Reference &amp; Mechanics Matrix</h2>
<table>
  <thead>
    <tr>
      <th>Feature Dimension</th>
      <th>Vanilla Minecraft</th>
      <th>Agrarian Reform (<code>v1.2.0+</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Harvest Workflow</strong></td>
      <td>Break block &rarr; collect drops &rarr; re-select seed &rarr; replant</td>
      <td><strong>Right-click mature crop</strong>: instant drops &amp; auto-replant</td>
    </tr>
    <tr>
      <td><strong>Water Source Irrigation</strong></td>
      <td>4-block radius only (rigid 9x9 box)</td>
      <td><strong>8-block radius</strong> (Configurable Chebyshev rings)</td>
    </tr>
    <tr>
      <td><strong>Flowing Water Irrigation</strong></td>
      <td>0 blocks (Must be still source)</td>
      <td><strong>4-block capillary action</strong> (Streams hydrate soil)</td>
    </tr>
    <tr>
      <td><strong>Monoculture vs Polyculture</strong></td>
      <td>No growth difference</td>
      <td><strong>+10% growth speed bonus</strong> for mixed plantings</td>
    </tr>
    <tr>
      <td><strong>Farmland Trampling</strong></td>
      <td>Instantly breaks into dirt on jump</td>
      <td><strong>Configurable trample immunity</strong> (Total or Player-only)</td>
    </tr>
    <tr>
      <td><strong>Offline Crop Growth</strong></td>
      <td>Completely frozen when unloaded</td>
      <td><strong>The Continuum simulation</strong> catches up on reload</td>
    </tr>
    <tr>
      <td><strong>Bone-Meal Support</strong></td>
      <td>Blocked on Cactus, Sugar Cane, Nether Wart</td>
      <td><strong>Universal bone-meal</strong> applies to all cultivated flora</td>
    </tr>
    <tr>
      <td><strong>Rainstorm Growth</strong></td>
      <td>Standard random tick rate</td>
      <td><strong>Accelerated growth stages</strong> during rainfall</td>
    </tr>
  </tbody>
</table>
<hr>
<h2>🚀 In-Game Commands &amp; Quick Start</h2>
<p>Agrarian Reform parameters are native GameRules accessible via standard Minecraft commands:</p>
<pre><code># Query current water source irrigation radius
/gamerule agrarian_reform:hydration_source_range

# Expand water source irrigation to 12 blocks
/gamerule agrarian_reform:hydration_source_range 12

# Enable 100% player farmland trample immunity
/gamerule agrarian_reform:total_trample_immunity true
/gamerule agrarian_reform:trample_immunity_players_only true

# Set global crop growth speed to 150%
/gamerule agrarian_reform:global_growth_multiplier 150

# Freeze growth for a specific crop completely (-1)
/gamerule agrarian_reform:growth_minecraft_sugar_cane -1</code></pre>
<p>All modifications made via <code>/gamerule</code> take effect immediately without requiring a game restart.</p>
<hr>
<h2>⚙️ Configuration (Native GameRules)</h2>
<blockquote><p><strong>💡 Config vs. In-Game GameRules:</strong> The global configuration file (<code>config/agrarian-reform.json</code>) only defines default values for newly created worlds. In existing worlds, change settings in-game via the <strong>Edit Game Rules</strong> UI screen or the <code>/gamerule</code> command.</p></blockquote>
<table>
  <thead>
    <tr>
      <th>GameRule Name</th>
      <th>Type</th>
      <th>Default</th>
      <th>Valid Range</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>agrarian_reform:hydration_source_range</code></td>
      <td><code>Integer</code></td>
      <td><code>8</code></td>
      <td><code>0</code> to <code>32</code></td>
      <td>Block radius a still water source hydrates farmland.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:hydration_flowing_range</code></td>
      <td><code>Integer</code></td>
      <td><code>4</code></td>
      <td><code>0</code> to <code>32</code></td>
      <td>Block radius flowing water provides capillary hydration.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:growth_biodiversity_bonus</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Grants +10% growth speed bonus when different crops are planted adjacently.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:rain_growth_acceleration</code></td>
      <td><code>Integer</code></td>
      <td><code>1</code></td>
      <td><code>0</code> to <code>10</code></td>
      <td>Extra growth stages awarded to sky-exposed crops during rainstorms.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:total_trample_immunity</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>100% protection against farmland trample destruction.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:trample_immunity_players_only</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Restricts trample protection to players and tamed pets.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:always_wet_farmland</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Forces all farmland to maintain moisture level 7 without water.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:right_click_harvest</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Enables one-click harvest and auto-replant on mature crops.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:universal_bonemeal</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Allows bone-meal acceleration on Cactus, Sugar Cane, and Nether Wart.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:global_growth_multiplier</code></td>
      <td><code>Integer</code></td>
      <td><code>100</code></td>
      <td><code>0</code> to <code>2147483647</code></td>
      <td>Global crop growth speed percentage (100 = 1x vanilla).</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:ambient_crop_rustle</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Subtle ambient audio feedback when walking through crops.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:ambient_vitality_particles</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Emits green sparkle particles when crops advance a growth stage.</td>
    </tr>
  </tbody>
</table>
<hr>
<h2>📖 In-Depth How-To &amp; Gameplay Playbook</h2>
<h3>1. Drop-In Setup &amp; Baseline Initialization</h3>
<ol>
  <li>Place <code>agrarian-reform-*.jar</code> along with <strong>Fabric API</strong> and <strong>Dasik Library</strong> into your <code>mods</code> folder.</li>
  <li>Launch Minecraft. The mod will automatically generate <code>config/agrarian-reform.json</code> populated with recommended pastoral defaults.</li>
</ol>
<h3>2. Crafting Expansive Natural Terrace Farms</h3>
<ul>
  <li>Because water source blocks hydrate up to <strong>8 blocks away</strong>, you no longer need a water hole every 4 blocks. Place a single central pond or water well to hydrate an expansive <strong>17x17 field</strong>!</li>
  <li>Use winding streams and canals: flowing water hydrates 4 blocks outwards, allowing picturesque hillside terraces with zero artificial water grids.</li>
</ul>
<h3>3. Mastering Polyculture &amp; Companion Planting</h3>
<ul>
  <li>Alternate your crop rows (e.g. Wheat &rarr; Carrot &rarr; Potato &rarr; Beetroot). Adjacent different crop types trigger the <strong>+10% biodiversity growth bonus</strong>.</li>
  <li>Watch for gentle green vitality sparkles (<code>ambient_vitality_particles</code>): these confirm your crops are thriving and benefiting from companion planting.</li>
</ul>
<h3>4. Right-Click Harvesting &amp; Field Management</h3>
<ul>
  <li>Simply right-click any fully grown crop with an empty hand or tool. Drops will scatter naturally around your feet, and the crop will automatically reset to seed stage at age 0.</li>
  <li>Hold a Fortune III tool while right-clicking to safely multiply potato, carrot, or seed yields without taking tool wear.</li>
</ul>
<h3>5. Managing Trample &amp; Animal Grazing</h3>
<ul>
  <li>If you frequently walk across your fields or build farmsteads near villages, run <code>/gamerule agrarian_reform:total_trample_immunity true</code> to keep your soil permanently tilled.</li>
  <li>For balanced immersion, enable <code>trample_immunity_players_only true</code> so you can jump on your own crops while wild animals are still discouraged from trampling them.</li>
</ul>
<hr>
<h2>☕ Support</h2>
<p>If you enjoy <strong>Agrarian Reform</strong> and the <strong>Vanilla Outsider Collection</strong>, consider fueling future updates!</p>
<p align="center">
<a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&amp;logo=ko-fi&amp;logoColor=white" alt="Ko-fi"></a>
<a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
<a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>
<blockquote><p><strong>🇮🇩 Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!</p></blockquote>
<h3>💬 Join the Community &amp; Get Support</h3>
<p>Looking for help, want to test early beta builds, or vote on upcoming features? Join our official Discord community!</p>
<p align="center">
<a href="https://discord.gg/EV99bgAFqb">
<img src="https://img.shields.io/badge/💬_Discord-Join_Community-5865F2?style=for-the-badge&amp;logo=discord&amp;logoColor=white" alt="Join Official Discord">
</a>
</p>
<hr>
<h2>📜 Credits &amp; Modpack Permissions</h2>
<table>
  <thead>
    <tr>
      <th>Role / Property</th>
      <th>Author / Link</th>
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
      <td>Vanilla Outsider Collection</td>
    </tr>
    <tr>
      <td><strong>License</strong></td>
      <td><a href="https://www.gnu.org/licenses/gpl-3.0.html" target="_blank" rel="noopener">GNU General Public License v3.0 (GPLv3)</a></td>
    </tr>
    <tr>
      <td><strong>Source Code</strong></td>
      <td><a href="https://github.com/Rifaditya/vanilla-outsider-agrarian-reform" target="_blank" rel="noopener">GitHub - Rifaditya/vanilla-outsider-agrarian-reform</a></td>
    </tr>
    <tr>
      <td><strong>Issue Tracker</strong></td>
      <td><a href="https://github.com/Rifaditya/vanilla-outsider-agrarian-reform/issues" target="_blank" rel="noopener">GitHub Issues</a></td>
    </tr>
    <tr>
      <td><strong>Documentation / Wiki</strong></td>
      <td><a href="https://mod-portal.pages.dev/vanilla-outsider-agrarian-reform/" target="_blank" rel="noopener">Web Documentation Portal</a></td>
    </tr>
  </tbody>
</table>
<blockquote><p><strong>📦 Modpack Permissions &amp; Distribution:</strong><br> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (<strong>Modrinth</strong> or <strong>CurseForge</strong>). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited. <br><br> <strong>⚖️ License &amp; Fork Guidelines (No Zero-Change Re-uploads):</strong><br> This project is open-source under the <strong>GNU GPLv3</strong>. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br> <strong>However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.</strong></p></blockquote>
<hr>
<div align="center">
<strong>Made with ❤️ for the Minecraft community</strong>
<em>Part of the Vanilla Outsider Collection</em>
</div>
