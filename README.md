# RE-WILDER

A 2D tile-based ecological RPG for Android.

You are a field ecologist. The world ended in silence. You are going to bring it back.

---

## BUILD

Every push to `main` triggers a GitHub Actions build.
The APK artifact appears under **Actions → latest workflow run → Artifacts**.

**Download → install to Android device → test.**

---

## PHASE STATUS

- [x] Phase 0 — Engine bootstrap
- [ ] Phase 1 — World movement core
- [ ] Phase 2 — Interaction core
- [ ] Phase 3 — Restoration core
- [ ] Phase 4 — Progression core

---

## TECH STACK

- Engine: LibGDX 1.12.1 (Java)
- Platform: Android APK
- Build: Gradle + GitHub Actions
- Maps: Tiled Editor (TMX)
- Data: JSON

---

## PROJECT STRUCTURE

```
re-wilder/
├── core/src/          Java gameplay systems
├── android/src/       AndroidLauncher only
├── assets/
│   ├── maps/          TMX biome files
│   ├── sprites/       Sprite PNGs
│   ├── tilesets/      Atlas files
│   ├── audio/         OGG audio files
│   ├── ui/            UI atlas files
│   └── data/          creatures.json, biomes.json
└── .github/workflows/ android.yml CI pipeline
```

---

## DESIGN

See GDD + TDD v3.0 for full design specification.
No combat. No damage. No loot. Restoration is the only progression.
