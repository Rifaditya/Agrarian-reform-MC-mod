<p align="center">
  <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&amp;logo=fabric" alt="Requires Fabric API"></a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/dasik-libary"><img src="https://img.shields.io/badge/Requires-Dasik_Library-8A2BE2?style=for-the-badge" alt="Requires Dasik Library"></a>
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&amp;logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

<h2>🌾 Agrarian Reform</h2>

<blockquote><p><strong>&ldquo;The world should not freeze just because you walked away.&rdquo;</strong></p></blockquote>

<blockquote><p><strong>1 Jar 1 Version Policy:</strong> I build <strong>1 dedicated JAR for each Minecraft version</strong> (e.g. MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.<br><br><strong>Dependency Requirement:</strong> For modern Minecraft 26.x releases (26.1.2, 26.2, 26.3+), this mod requires both <strong>Fabric API</strong> and <strong>Dasik Library</strong> (<code>v1.8.3+</code>). Legacy builds (1.20.1, 1.21.x) are self-contained and only require Fabric API.</p></blockquote>

<p>In vanilla Minecraft, crop farming is a fragile, proximity-based simulation. You till farmland, sow sprawling fields of wheat or vegetables, venture off on an epic mining expedition or Nether trek, and return home hours later only to find your crops completely frozen in the exact same growth stage you left them. Worse, an accidental hop instantly tramples tilled soil into coarse dirt, water canals only reach 4 blocks away, and stubborn crops like sugar cane or cactus completely ignore bone meal.</p>

<p><strong>Agrarian Reform</strong> transforms agriculture into an organic, persistent ecosystem. Powered by <strong>The Continuum</strong> offline growth simulation engine, crops calculate elapsed game time and grow organically while chunks are unloaded. Combined with smart trample protection (Leather Boots and Feather Falling save farmland), deep-reach 8-block source irrigation, companion polyculture growth bonuses, universal bone meal, and right-click harvesting, farming finally feels immersive, rewarding, and seamless.</p>

<p>Part of the <strong>Vanilla Outsider Collection</strong> &mdash; mods that refine vanilla mechanics with modern engineering standards.</p>

<hr>

<h2>✨ Features</h2>

<h3>🕰️ The Continuum: True Offline Crop Persistence</h3>
<p>Never sit around waiting for chunks to tick just so your harvest matures:</p>
<ul>
  <li><strong>Timestamped Chunk State:</strong> When you travel away and chunks unload, the mod records the exact game timestamp of the chunk.</li>
  <li><strong>Time-Delta Catch-Up:</strong> Upon re-entering the area, the simulation calculates the elapsed game time and advances plant growth stages accordingly.</li>
  <li><strong>Universal Plant Scope:</strong> Automatically simulates standard crops (<code>CropBlock</code>), Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.</li>
  <li><strong>Throttled Lag-Free Execution:</strong> Growth calculations are processed smoothly at 5 crop blocks per tick globally, guaranteeing zero frame stutter or TPS spikes even when returning to massive multi-thousand block farms.</li>
</ul>

<h3>🧤 Soil Resilience: Smart Trample Protection</h3>
<p>Tend your fields freely without fear of ruining hours of careful tilling:</p>
<ul>
  <li><strong>Soft-Step Safe Movement:</strong> Walking, running, and sprinting across crops and farmland is 100% safe.</li>
  <li><strong>Protective Footwear:</strong> Wearing <strong>Leather Boots</strong> (<code>#c:boots/soft</code>, <code>#agrarian_reform:soft_step_boots</code>) or any level of the <strong>Feather Falling</strong> enchantment completely prevents farmland from being trampled when jumping or falling.</li>
  <li><strong>Heavy Mob Threat Preservation:</strong> Massive raid beasts (Ravagers, Iron Golems) still crush farmland underfoot, preserving the environmental tension of village sieges.</li>
  <li><strong>Total Immunity Toggles:</strong> Enable <code>agrarian_reform:total_trample_immunity</code> for an absolute zero-trample experience, or <code>trample_immunity_players_only</code> to restrict immunity to players and tamed pets.</li>
</ul>

<h3>💧 Hydro-Dynamics: Deep Irrigation &amp; Rain Spurts</h3>
<p>Water hydrates soil with realistic spatial reach:</p>
<ul>
  <li><strong>8-Block Source Reach:</strong> Still water source blocks hydrate farmland up to <strong>8 blocks away</strong> (quadrupling the hydration surface area compared to vanilla's 4 blocks).</li>
  <li><strong>Flowing Water Canal Fallback:</strong> Flowing water streams retain standard 4-block hydration distance, rewarding dedicated still-water irrigation canals.</li>
  <li><strong>Natural Rain Hydration:</strong> Natural rainfall hydrates exposed open-sky farmland and grants active growth spurts with happy green vitality particles.</li>
  <li><strong>Permanent Wetness Option:</strong> Toggle <code>agrarian_reform:always_wet_farmland</code> to keep all farmland permanently hydrated regardless of water proximity.</li>
</ul>

<h3>🍀 Polyculture: Biodiversity Growth Bonus</h3>
<p>Organic farming practices are naturally rewarded over sterile monoculture grids:</p>
<ul>
  <li>Planting diverse adjacent crops (e.g. alternating rows or checkerboards of Wheat, Carrots, Potatoes, and Beetroots) grants an automatic <strong>+10% Biodiversity Growth Bonus</strong>.</li>
  <li>Discourages unnatural monoculture grid spam while honoring authentic companion planting.</li>
</ul>

<h3>🌾 Right-Click Harvesting &amp; Seed-to-Grass Detailing</h3>
<p>Streamlined field labor that eliminates tedious manual replanting:</p>
<ul>
  <li><strong>Right-Click Harvest:</strong> Right-click fully matured crops to harvest yields and instantly replant the seed in one fluid gesture. Works seamlessly with vanilla crops, cocoa, nether wart, and sugar cane columns (harvests upper stalks while keeping the root base intact).</li>
  <li><strong>Seed-to-Grass Landscaping:</strong> Right-click dirt or coarse dirt with any seed item (<code>#minecraft:chicken_food</code> tag) to sprout a vibrant grass block, making pasture restoration effortless.</li>
</ul>

<h3>🦴 Universal Bone Meal</h3>
<p>Apply natural fertilizers to stubborn plants that vanilla ignores:</p>
<ul>
  <li><strong>Sugar Cane &amp; Cactus:</strong> Fertilizing instantly grows the vertical column up by 1 block (up to the vanilla 3-block height ceiling).</li>
  <li><strong>Nether Wart &amp; Cocoa:</strong> Advances growth by 1 stage per application.</li>
  <li><strong>Vines:</strong> Grows the vine downwards by 1 block, automatically matching wall attachments.</li>
  <li><strong>Fair Consumption:</strong> Consumes exactly 1 bone meal item from your hand (creative mode bypassed) accompanied by authentic green vitality particles and audio cues.</li>
</ul>

<h3>⚡ Global Growth Tuning</h3>
<p>Fine-tune agricultural pacing across your entire world:</p>
<ul>
  <li><strong>Proportional Scaling:</strong> Scale crop growth speeds from <code>0%</code> (growth frozen) to <code>100%</code> (standard vanilla) up to <code>500%</code> (ultra-fast growth) via <code>agrarian_reform:global_growth_multiplier</code>.</li>
  <li><strong>Integrated Continuum Math:</strong> Offline catch-up calculations scale proportionally with the global growth multiplier setting.</li>
</ul>

<h3>🌿 Ambient Sensory Feedback</h3>
<ul>
  <li><strong>Crop Rustling Audio:</strong> Brushing through dense, mature wheat fields triggers subtle, organic foliage rustle sounds.</li>
  <li><strong>Vitality Growth Particles:</strong> Natural growth events and bonemeal surges emit soft green vitality sparkles.</li>
</ul>

<h3>🧩 Compatibility &amp; HUD Integration</h3>
<ul>
  <li><strong>Jade / WTHIT Tooltips:</strong> Seamlessly inspect crop growth percentages, hydration states, and polyculture bonuses in real-time.</li>
  <li><strong>ModMenu &amp; YACL Integration:</strong> Optional graphical configuration screen accessible directly from the title screen or pause menu.</li>
  <li><strong>100% Server-Side Compatible:</strong> Vanilla clients can connect to servers running Agrarian Reform without needing any client-side mod installed.</li>
</ul>

<hr>

<h2>📊 Mechanics &amp; Operational Matrix</h2>

<table>
  <thead>
    <tr>
      <th>Farming Mechanic</th>
      <th>Vanilla Minecraft</th>
      <th>Agrarian Reform (Modern 26.2+)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Offline Persistence</strong></td>
      <td>Growth completely frozen in unloaded chunks</td>
      <td><strong>The Continuum engine</strong> simulates and catches up growth on chunk load</td>
    </tr>
    <tr>
      <td><strong>Walking / Sprinting</strong></td>
      <td>Safe</td>
      <td><strong>100% Safe</strong> with ambient foliage rustle sounds</td>
    </tr>
    <tr>
      <td><strong>Jumping / Falling</strong></td>
      <td>Tramples farmland into coarse dirt</td>
      <td><strong>Zero damage</strong> when wearing Leather Boots or Feather Falling</td>
    </tr>
    <tr>
      <td><strong>Water Source Reach</strong></td>
      <td>4 blocks max reach</td>
      <td><strong>8 blocks reach</strong> (4x more coverage per water source)</td>
    </tr>
    <tr>
      <td><strong>Flowing Water Reach</strong></td>
      <td>4 blocks reach</td>
      <td><strong>4 blocks reach</strong> (rewards still water canals)</td>
    </tr>
    <tr>
      <td><strong>Crop Intercropping</strong></td>
      <td>No effect</td>
      <td><strong>+10% Polyculture Growth Bonus</strong> for adjacent diverse crops</td>
    </tr>
    <tr>
      <td><strong>Harvesting Mature Crops</strong></td>
      <td>Punches/destroys block, requires replanting</td>
      <td><strong>Right-click harvests &amp; auto-replants in 1 click</strong></td>
    </tr>
    <tr>
      <td><strong>Bone Meal on Sugar Cane</strong></td>
      <td>Green particles only (zero growth)</td>
      <td><strong>Grows stalk upward +1 block</strong> (up to 3 blocks max)</td>
    </tr>
    <tr>
      <td><strong>Bone Meal on Cactus</strong></td>
      <td>Green particles only (zero growth)</td>
      <td><strong>Grows stalk upward +1 block</strong> (up to 3 blocks max)</td>
    </tr>
    <tr>
      <td><strong>Bone Meal on Vines</strong></td>
      <td>No effect</td>
      <td><strong>Grows vine downward +1 block</strong></td>
    </tr>
    <tr>
      <td><strong>Seeds on Dirt Blocks</strong></td>
      <td>No effect</td>
      <td><strong>Sprouts short grass block</strong></td>
    </tr>
  </tbody>
</table>

<hr>

<h2>🚀 In-Game Commands &amp; Quick Start</h2>

<p>Adjust agricultural simulation parameters live in your active world without restarting using standard Minecraft <code>/gamerule</code> commands:</p>

<pre><code>/gamerule agrarian_reform:hydration_source_range 8
/gamerule agrarian_reform:growth_biodiversity_bonus true
/gamerule agrarian_reform:right_click_harvest true
/gamerule agrarian_reform:universal_bonemeal true
/gamerule agrarian_reform:global_growth_multiplier 100</code></pre>

<p>All modifications made via <code>/gamerule</code> take effect immediately and synchronize across all connected players.</p>

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
      <td><code>1</code> to <code>32</code></td>
      <td>Maximum block distance a still water source hydrates farmland.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:hydration_flowing_range</code></td>
      <td><code>Integer</code></td>
      <td><code>4</code></td>
      <td><code>0</code> to <code>16</code></td>
      <td>Maximum block distance flowing water hydrates farmland.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:rain_growth_acceleration</code></td>
      <td><code>Integer</code></td>
      <td><code>1</code></td>
      <td><code>0</code> to <code>5</code></td>
      <td>Extra growth stages granted to exposed open-sky crops during rainfall.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:pure_water_hydration_only</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>When true, only pure water source blocks hydrate farmland.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:growth_biodiversity_bonus</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Grants a +10% growth speed bonus when different crop types are planted adjacently.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:ambient_crop_rustle</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Plays subtle foliage rustle sounds when walking or sprinting through mature crops.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:ambient_vitality_particles</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Emits green vitality particles when crops advance in growth stage.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:total_trample_immunity</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Completely prevents all farmland trampling by all entities.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:trample_immunity_players_only</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Restricts trample immunity to players and tamed pets, letting mobs still trample soil.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:always_wet_farmland</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Keeps all farmland permanently hydrated regardless of water proximity.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:seeds_grow_grass</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Right-clicking dirt or coarse dirt with chicken food/seeds sprouts a grass block.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:right_click_harvest</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Right-clicking mature crops harvests drops and replants automatically.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:universal_bonemeal</code></td>
      <td><code>Boolean</code></td>
      <td><code>true</code></td>
      <td><code>true / false</code></td>
      <td>Allows bone meal to fertilize sugar cane, cactus, nether wart, cocoa, and vines.</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:global_growth_multiplier</code></td>
      <td><code>Integer</code></td>
      <td><code>100</code></td>
      <td><code>0</code> to <code>500</code></td>
      <td>Global crop growth rate percentage (100 = vanilla speed, 0 = frozen, 200 = 2x speed).</td>
    </tr>
    <tr>
      <td><code>agrarian_reform:debug_mode</code></td>
      <td><code>Boolean</code></td>
      <td><code>false</code></td>
      <td><code>true / false</code></td>
      <td>Enables detailed diagnostic logging for crop ticks. Resets to false on restart.</td>
    </tr>
  </tbody>
</table>

<hr>

<h2>📖 In-Depth How-To &amp; Operational Playbook</h2>

<h3>1. Drop-In Setup &amp; Baseline Initialization</h3>
<ol>
  <li>Place <code>agrarian-reform-*.jar</code> along with <strong>Fabric API</strong> and <strong>Dasik Library</strong> into your <code>mods</code> folder.</li>
  <li>Launch Minecraft. The mod will automatically generate <code>config/agrarian-reform.json</code> populated with recommended agricultural defaults.</li>
</ol>

<h3>2. High-Efficiency Irrigation Layouts (8-Block Chebyshev Radii)</h3>
<ul>
  <li>Because still water sources hydrate farmland up to <strong>8 blocks away</strong>, you only need a single water source block in the center of a <strong>17x17 plot</strong> of farmland (289 blocks of farmland from 1 bucket of water!).</li>
  <li>For aesthetics, build irrigation canals with flowing water streams: flowing water hydrates 4 blocks out, allowing realistic river channels across your fields.</li>
</ul>

<h3>3. Maximizing Polyculture Companion Planting (+10% Bonus)</h3>
<ul>
  <li>Avoid massive single-crop monoculture fields. Instead, plant alternating rows: Wheat &rarr; Carrots &rarr; Potatoes &rarr; Beetroots.</li>
  <li>Every crop that has at least one neighboring crop of a different type receives an automatic <strong>+10% growth speed bonus</strong>.</li>
</ul>

<h3>4. Safe Farmland Navigation &amp; Trample Prevention</h3>
<ul>
  <li>Equip any pair of <strong>Leather Boots</strong> or armor enchanted with <strong>Feather Falling</strong> (even Level I). You can now sprint and jump across your fields without converting farmland back into dirt.</li>
  <li>Wear soft boots during harvest season to tend crops quickly without worrying about movement errors.</li>
</ul>

<h3>5. Fluid Right-Click Harvesting &amp; Perennial Sugar Cane Care</h3>
<ul>
  <li>When crops reach maturity, simply right-click with an empty hand or tool. The harvest drops are placed into your inventory or ground, and the crop automatically resets to stage 0.</li>
  <li>When right-clicking a 3-block tall Sugar Cane column, the mod harvests the top 2 stalks while keeping the base root plant untouched, allowing continuous passive growth.</li>
</ul>

<h3>6. Universal Bone Meal Application</h3>
<ul>
  <li>Right-click Sugar Cane or Cactus with Bone Meal in hand to force an immediate vertical growth spurt (up to 3 blocks high).</li>
  <li>Right-click Nether Wart or Cocoa to advance their maturity stages instantly.</li>
  <li>Right-click Vines to grow them downwards by 1 block along walls.</li>
</ul>

<h3>7. Pasture Landscaping with Seeds</h3>
<ul>
  <li>Hold any seed item (<code>Wheat Seeds</code>, <code>Beetroot Seeds</code>, <code>Melon Seeds</code>, <code>Pumpkin Seeds</code>) and right-click on bare dirt or coarse dirt. The block instantly transforms into a lush green grass block with green growth particles and audio feedback.</li>
</ul>

<hr>

<h2>🧩 Recommended Sister Mods</h2>

<p>If you enjoy <strong>Agrarian Reform</strong>, these companion mods from the <strong>Vanilla Outsider Collection</strong> plug in seamlessly:</p>

<ul>
  <li>🐾 <a href="https://www.curseforge.com/minecraft/mc-mods/vo-natural-reproduction"><strong>Natural Reproduction</strong></a>: Organic autonomous livestock breeding, herd dynamics, and genetic traits that bring your pastures to life.</li>
  <li>🛏️ <a href="https://www.curseforge.com/minecraft/mc-mods/vo-true-sleep"><strong>True Sleep</strong></a>: Accelerates furnace smelting, brewing, crop growth, and tile entities during sleep instead of instantly skipping the night.</li>
  <li>🦇 <a href="https://www.curseforge.com/minecraft/mc-mods/vo-better-bats"><strong>Better Bats</strong></a>: Dynamic bat swarms that roost upside down, fertilize farmland with guano, and hunt crop pests.</li>
</ul>

<p><em>Explore the full <a href="https://www.curseforge.com/members/dasikigaijin/projects"><strong>Vanilla Outsider Collection</strong></a> for more vanilla enhancements.</em></p>

<hr>

<h2>☕ Support</h2>

<p>If you enjoy the <strong>Vanilla Outsider Collection</strong>, consider fueling future development!</p>

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&amp;logo=ko-fi&amp;logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

<blockquote><p><strong>🇮🇩 Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!</p></blockquote>

<blockquote><p><strong>Dedicated Server Hosting Partner:</strong><br>Looking for a reliable server to play with friends? Check out <strong>BisectHosting</strong> for 1-click modpack installations, automated backups, and 24/7 dedicated customer support.</p></blockquote>

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
      <td><strong>Collection</strong></td>
      <td><a href="https://www.curseforge.com/members/dasikigaijin/projects">Vanilla Outsider Collection</a></td>
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
