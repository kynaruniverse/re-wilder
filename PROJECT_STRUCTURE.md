# RE-WILDER Game Project Structure

## Directory Layout

```
re-wilder/
├── android/                          # Android module
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── assets/               # Game assets
│   │       │   ├── data/
│   │       │   │   ├── creatures.json
│   │       │   │   ├── biomes.json
│   │       │   │   ├── npcs.json
│   │       │   │   └── config.json
│   │       │   ├── maps/             # TMX tile maps
│   │       │   │   ├── forest_01.tmx
│   │       │   │   ├── ruins_02.tmx
│   │       │   │   ├── wetlands_03.tmx
│   │       │   │   └── village.tmx
│   │       │   ├── tilesets/         # Tile atlases
│   │       │   │   ├── biome_forest_dead.atlas
│   │       │   │   ├── biome_forest_partial.atlas
│   │       │   │   ├── biome_forest_active.atlas
│   │       │   │   ├── biome_forest_restored.atlas
│   │       │   │   ├── biome_ruins_dead.atlas
│   │       │   │   ├── biome_ruins_partial.atlas
│   │       │   │   ├── biome_ruins_active.atlas
│   │       │   │   ├── biome_ruins_restored.atlas
│   │       │   │   ├── biome_wetlands_dead.atlas
│   │       │   │   ├── biome_wetlands_partial.atlas
│   │       │   │   ├── biome_wetlands_active.atlas
│   │       │   │   ├── biome_wetlands_restored.atlas
│   │       │   │   ├── biome_village_outpost.atlas
│   │       │   │   ├── biome_village_settlement.atlas
│   │       │   │   ├── biome_village_sanctuary.atlas
│   │       │   │   ├── creatures.atlas
│   │       │   │   └── ui.atlas
│   │       │   ├── audio/            # OGG audio files
│   │       │   │   ├── ambient/
│   │       │   │   │   ├── forest_dead.ogg
│   │       │   │   │   ├── forest_partial.ogg
│   │       │   │   │   ├── forest_active.ogg
│   │       │   │   │   ├── forest_restored.ogg
│   │       │   │   │   ├── ruins_dead.ogg
│   │       │   │   │   ├── ruins_partial.ogg
│   │       │   │   │   ├── ruins_active.ogg
│   │       │   │   │   ├── ruins_restored.ogg
│   │       │   │   │   ├── wetlands_dead.ogg
│   │       │   │   │   ├── wetlands_partial.ogg
│   │       │   │   │   ├── wetlands_active.ogg
│   │       │   │   │   ├── wetlands_restored.ogg
│   │       │   │   │   ├── village_outpost.ogg
│   │       │   │   │   ├── village_settlement.ogg
│   │       │   │   │   └── village_sanctuary.ogg
│   │       │   │   └── sfx/
│   │       │   │       ├── bonding_success.ogg
│   │       │   │       ├── bonding_failure.ogg
│   │       │   │       ├── restoration_threshold.ogg
│   │       │   │       └── ui_click.ogg
│   │       └── res/
│   │           ├── drawable/
│   │           │   └── ic_launcher.png
│   │           └── values/
│   │               └── strings.xml
│   └── build.gradle
├── core/                             # Core game logic (Java)
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── rewilderdev/
│   │                   ├── GameMain.java
│   │                   ├── AndroidLauncher.java
│   │                   ├── game/
│   │                   │   ├── Player.java
│   │                   │   ├── Creature.java
│   │                   │   └── NPC.java
│   │                   ├── systems/
│   │                   │   ├── TouchInputManager.java
│   │                   │   ├── WorldManager.java
│   │                   │   ├── RestorationSystem.java
│   │                   │   ├── RestorationEventListener.java
│   │                   │   ├── BondingSystem.java
│   │                   │   ├── InteractionTypeDispatcher.java
│   │                   │   ├── CreatureSystem.java
│   │                   │   ├── CodexSystem.java
│   │                   │   ├── GateSystem.java
│   │                   │   ├── ProgressionSystem.java
│   │                   │   ├── AudioManager.java
│   │                   │   ├── UIManager.java
│   │                   │   ├── TransitionManager.java
│   │                   │   └── WorldEventSystem.java
│   │                   ├── interactions/
│   │                   │   ├── TimingInputChallenge.java
│   │                   │   ├── MovementSyncChallenge.java
│   │                   │   ├── EnvironmentalStabilizationChallenge.java
│   │                   │   └── RhythmAlignmentChallenge.java
│   │                   ├── data/
│   │                   │   ├── CreatureData.java
│   │                   │   ├── BiomeData.java
│   │                   │   ├── NPCData.java
│   │                   │   └── ConfigData.java
│   │                   └── util/
│   │                       ├── JsonLoader.java
│   │                       ├── SaveGameManager.java
│   │                       └── Constants.java
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── .github/
│   └── workflows/
│       └── android-build.yml
├── .gitignore
├── README.md
└── LICENSE
```

## Key Files

### Configuration Files

- **build.gradle** - Root Gradle configuration
- **settings.gradle** - Gradle module settings
- **gradle.properties** - Gradle properties
- **android/build.gradle** - Android module configuration
- **core/build.gradle** - Core module configuration

### Game Data (JSON)

- **creatures.json** - Creature definitions (ID, role, abilities, biome affinity)
- **biomes.json** - Biome metadata (restoration thresholds, audio layers)
- **npcs.json** - NPC definitions (dialogue, positions, activation thresholds)
- **config.json** - Game configuration (restoration gains, tick intervals, thresholds)

### Maps (TMX)

- **forest_01.tmx** - Forest biome map
- **ruins_02.tmx** - Ruins biome map
- **wetlands_03.tmx** - Wetlands biome map
- **village.tmx** - Village hub map

### Audio Assets

- **Ambient layers** - 4 per biome (dead, partial, active, restored)
- **Village ambient** - 3 stages (outpost, settlement, sanctuary)
- **Sound effects** - Bonding, restoration, UI interactions

### Tile Atlases

- **Biome atlases** - 4 states × 3 biomes = 12 tile atlases
- **Village atlases** - 3 upgrade stages
- **Creatures atlas** - All creature sprites
- **UI atlas** - HUD and menu elements

## Build Pipeline

### GitHub Actions Workflow

The `.github/workflows/android-build.yml` file automates:
1. Gradle build on every push to `main`
2. APK generation
3. Artifact upload for download

### Local Build

```bash
gradle build
gradle assembleDebug
```

## Asset Generation

### Tiles (16×16 or 32×32 pixel art)

- Dead state: Monochrome, barren appearance
- Partial state: Color returning, flora beginning to grow
- Active state: Vibrant colors, wildlife visible
- Restored state: Full ecosystem, animated elements

### Creatures (Sprite sheets)

- Idle animation: 8–12 FPS
- Interaction states: Attention, engaged, bonded
- Multiple bond levels: Visual progression

### Audio

- Format: OGG Vorbis, 44.1 kHz, 16-bit
- Ambient loops: 2–4 second crossfades between states
- SFX: One-shot events for interactions

## Save Data

Location: `Gdx.files.local("savegame.json")`

Structure:
```json
{
  "playerPosition": {"biomeId": "forest_01", "x": 10, "y": 10},
  "biomes": {
    "forest_01": {"restoration": 45.0, "state": "partial"},
    "ruins_02": {"restoration": 0.0, "state": "dead"},
    "wetlands_03": {"restoration": 0.0, "state": "dead"}
  },
  "creatures": {
    "creature_rootling": {"bonded": true, "bondLevel": 3, "assignedBiome": "forest_01"},
    "creature_crane": {"bonded": false, "bondLevel": 0, "assignedBiome": null}
  },
  "codex": {
    "discovered": ["creature_rootling", "creature_crane"],
    "completed": []
  },
  "village": {
    "stage": 1,
    "npcRoster": ["npc_ecologist_01", "npc_historian_01"]
  }
}
```

## Performance Targets

- **FPS**: 60 preferred, 30 minimum
- **Memory**: ≤ 128 MB heap
- **APK Size**: ≤ 50 MB (debug), ≤ 100 MB (release)
- **Texture Atlas**: ≤ 8 MB per biome
- **Audio Total**: ≤ 20 MB

## Development Phases

1. **Core Systems** (Weeks 1–8): Player movement, map loading, save/load
2. **Restoration & Bonding** (Weeks 9–16): Interaction types, bond system, restoration
3. **Content Systems** (Weeks 17–24): Codex, NPCs, village, all audio layers
4. **Content Production** (Weeks 25–32): All assets, dialogue, tutorial, polish
5. **Release Prep** (Weeks 33–36): Optimization, QA, submission

## Notes

- All file paths use snake_case
- All IDs follow the naming conventions in PMR Section 4
- JSON files are the source of truth for all game content
- No hardcoded game data in Java code
- All systems use constructor injection; no singletons
