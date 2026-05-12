# RE-WILDER

A 2D Tile-Based Ecological RPG for Android

## Overview

RE-WILDER is a non-combat, restoration-focused ecological RPG set in a post-collapse world undergoing long-term ecological recovery. Players assume the role of a field ecologist tasked with restoring degraded biomes, forming cooperative bonds with surviving creatures, and unlocking new regions through progressive ecosystem recovery.

**Key Features:**
- Zero combat mechanics
- Tile-based exploration with touch controls
- Creature bonding system (non-ownership, trust-based)
- Biome restoration with visual and audio state transitions
- Four restoration states per biome: Dead, Partial, Active, Restored
- Village hub system with progressive upgrades
- Codex system for creature discovery
- Save/load system with autosave on transitions

## Technical Specifications

### Platform
- **Target:** Android API 24+ (Android 7.0 and above)
- **Orientation:** Landscape only
- **Input:** Touch controls (virtual joystick + action button)

### Engine Stack
- **Engine:** LibGDX 1.12.1
- **Language:** Java 17
- **Build System:** Gradle 8.7
- **Compiler Target:** Android SDK 35

### Performance Targets
- **FPS:** 60 preferred, 30 minimum on mid-range devices
- **Memory:** ≤ 128 MB heap during active gameplay
- **APK Size:** ≤ 50 MB (debug), ≤ 100 MB (release)

## Project Structure

```
re-wilder/
├── android/                    # Android module
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── assets/            # Game assets
│   │   │   ├── data/          # JSON configuration
│   │   │   ├── maps/          # TMX tile maps
│   │   │   ├── tilesets/      # Tile atlases
│   │   │   └── audio/         # OGG audio files
│   │   └── res/               # Android resources
│   └── build.gradle
├── core/                       # Core game logic
│   ├── src/main/java/
│   │   └── com/rewilderdev/
│   │       ├── GameMain.java
│   │       ├── AndroidLauncher.java
│   │       ├── game/           # Game entities
│   │       ├── systems/        # Game systems
│   │       ├── interactions/   # Bonding challenges
│   │       ├── data/           # Data loaders
│   │       └── util/           # Utilities
│   └── build.gradle
├── .github/workflows/          # CI/CD
├── build.gradle                # Root Gradle config
├── settings.gradle
├── gradle.properties
└── README.md
```

## Game Systems

### Core Gameplay Loop

1. **Player Movement** - Tile-quantized movement via virtual joystick
2. **Creature Interaction** - Bonding challenges when entering proximity zones
3. **Restoration** - Biome restoration values increase on successful bonding
4. **State Transitions** - Visual and audio changes at restoration thresholds
5. **Progression** - Gates unlock new biomes and traversal abilities

### Key Systems

**RestorationSystem** - Tracks biome restoration (0-100%), triggers state transitions at 25%, 50%, 75%, 100%

**BondingSystem** - Manages creature bonding outcomes, increments bond levels (0-5), applies restoration gains

**CreatureSystem** - Spawns creatures, manages passive restoration ticks, handles creature state

**InteractionTypeDispatcher** - Routes interactions to four challenge types:
- Timing Input: Press input in rhythm with creature signals
- Movement Sync: Mirror creature movement patterns
- Environmental Stabilization: Interact with tiles to stabilize zones
- Rhythm Alignment: Match multi-beat sequences

**GateSystem** - Manages progression gates and village upgrades (3 stages)

**ProgressionSystem** - Handles save/load and autosave on biome transitions

**AudioManager** - Manages ambient layers (4 per biome) with crossfades between states

**UIManager** - Renders HUD, menus, and virtual controls

## Building and Deployment

### Prerequisites
- JDK 17+
- Gradle 8.7+
- Android SDK 35+
- Android NDK (for native compilation)

### Local Build

```bash
# Build the project
gradle build

# Build debug APK
gradle assembleDebug

# Build release APK (requires signing configuration)
gradle assembleRelease
```

### GitHub Actions CI/CD

Every push to `main` triggers an automated build that:
1. Compiles Java code
2. Builds debug APK
3. Uploads APK as artifact
4. Uploads build reports

### SPCK Editor / Mobile Development

For development on Android via SPCK Editor:
1. Clone the repository
2. Open in SPCK Editor
3. Configure Gradle build settings
4. Build and test on device

## Game Content

### Biomes (MVP)

| Biome | Description | Creatures | Audio Layers |
|-------|-------------|-----------|-------------|
| forest_01 | Ancient Forest | Rootling | 4 (dead/partial/active/restored) |
| ruins_02 | Crumbling Ruins | Tunnelmole, Mossback | 4 (dead/partial/active/restored) |
| wetlands_03 | Stagnant Wetlands | Ash Crane, Driftfin | 4 (dead/partial/active/restored) |
| village | Sanctuary Village | Heronkeeper | 3 (outpost/settlement/sanctuary) |

### Creatures (MVP)

| ID | Name | Role | Traversal | Interaction |
|----|------|------|-----------|------------|
| creature_rootling | Rootling | Restoration | None | Timing Input |
| creature_crane | Ash Crane | Traversal | Shallow Water | Movement Sync |
| creature_tunnelmole | Tunnelmole | Traversal | Dig | Environmental Stabilization |
| creature_mossback | Mossback | Restoration | None | Rhythm Alignment |
| creature_driftfin | Driftfin | Traversal | Deep Water | Timing Input |
| creature_heronkeeper | Heronkeeper | Support | None | Movement Sync |

### Restoration Thresholds

| Threshold | State | Visual | Audio | NPCs |
|-----------|-------|--------|-------|------|
| 0–24% | Dead | Monochrome, barren | Wind, silence | None active |
| 25–49% | Partial | Color returning, flora | Wildlife sounds | Scout dialogue |
| 50–74% | Active | Vibrant, animated | Full ecosystem | Most active |
| 75–100% | Restored | Full ecosystem | Complete ambient | All active |

## Asset Pipeline

### Tiles
- Format: PNG pixel-art, 16×16 or 32×32 pixels
- States: Dead, Partial, Active, Restored (4 per biome)
- Atlases: One per biome per state (12 total for 3 biomes)

### Creatures
- Format: PNG sprite sheets with frame-based animation
- Animation: 8–12 FPS for idle, 6–8 FPS for ambient
- Naming: `creature_[name]_[state]_NNN.png`

### Audio
- Format: OGG Vorbis, 44.1 kHz, 16-bit
- Ambient: 2–4 second crossfades between states
- SFX: One-shot events (bonding, restoration, UI)

### Maps
- Format: TMX (Tiled Editor format)
- Layers: ground, collision, decoration, interaction, restoration_overlay
- Tile size: 16×16 or 32×32 (consistent per map)

## Data Files

### creatures.json
Defines creature properties: ID, role, traversal type, interaction type, abilities, biome affinity, restoration gain

### biomes.json
Defines biome metadata: restoration thresholds, audio layers, creature spawn tables, traversal gates

### npcs.json
Defines NPC properties: ID, role, biome affinity, activation threshold, dialogue states

### config.json
Game configuration: restoration gains, tick intervals, thresholds, audio crossfade duration, UI settings

## Save System

**Location:** `Gdx.files.local("savegame.json")`

**Format:** JSON with player position, biome restoration values, creature bond levels, codex entries, village stage

**Autosave Triggers:**
- On biome transition
- On successful bonding interaction
- On app pause

## Development Roadmap

### Phase 1: Core Systems (Weeks 1–8)
Player movement, map loading, camera, collision, biome transition, save/load

### Phase 2: Restoration & Bonding (Weeks 9–16)
Restoration system, bonding system, interaction types, bond levels, tile-set swaps, audio layers

### Phase 3: Content Systems (Weeks 17–24)
Codex, NPCs, village, all audio layers, full creature roster, full biome roster

### Phase 4: Content Production (Weeks 25–32)
All tile assets, creature sprites, NPC sprites, dialogue, tutorial, polish

### Phase 5: Release Prep (Weeks 33–36)
Optimization, QA, Google Play submission

## Performance Optimization

### Memory Management
- Biome audio pre-loaded on map load, disposed after transition
- Texture atlases compressed with ETC2 (release builds)
- Nearest-neighbor filtering for pixel-art textures

### Rendering
- Single SpriteBatch for all rendering
- Orthographic camera with clamped bounds
- Tile layer render order: ground → decoration → restoration_overlay

### Audio
- Ambient layers crossfade over 2–4 seconds
- SFX pooled and reused
- Non-active biome audio released on low memory

## Testing Strategy

### Phase Completion Checklist

**Phase 1:** Player moves, maps load, camera follows, collision works, transitions function

**Phase 2:** Creatures spawn, interactions trigger, bond levels increment, restoration values update, tile-sets change

**Phase 3:** Codex tracks discoveries, NPCs dialogue, village upgrades, all audio layers active

**Phase 4:** All assets loaded, no crashes, frame rate stable, save/load works

### Minimum Target Device
- Android 7.0 / API 24
- 2 GB RAM
- Adreno 505-class GPU or equivalent

## Deployment

### Google Play Store (Post-MVP)
- Build target: `bundleRelease` (AAB format)
- Signing: Keystore configured via GitHub Actions secrets
- ProGuard/R8: Enabled for release builds
- AAB size: ≤ 100 MB

### Direct APK Distribution
- Build: `assembleDebug` or `assembleRelease`
- Size: ≤ 50 MB (debug), ≤ 100 MB (release)
- Sideload via Android Studio or `adb install`

## Contributing

This is a solo developer project. For modifications:
1. Create a feature branch
2. Make changes
3. Test on minimum target device
4. Submit pull request with test results

## License

Proprietary - All rights reserved

## Support

For issues, bugs, or feature requests, please open an issue on GitHub.

## Acknowledgments

RE-WILDER is inspired by:
- Pokemon Ruby/Sapphire (GBA-era tile exploration)
- Spiritfaser (emotional resonance, non-violent progression)
- Stardew Valley (restoration loop, hub/field structure)
- Alba: A Wildlife Adventure (nature-positive theme)
