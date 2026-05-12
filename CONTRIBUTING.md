# Contributing to RE-WILDER

## Development Workflow

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/re-wilder.git
cd re-wilder
```

### 2. Set Up Development Environment

```bash
# Copy local properties template
cp local.properties.example local.properties

# Edit with your SDK path
nano local.properties

# Make gradlew executable
chmod +x gradlew

# Verify setup
./gradlew --version
```

### 3. Create Feature Branch

```bash
git checkout -b feature/your-feature-name
# or for bug fixes:
git checkout -b bugfix/issue-description
```

### 4. Make Changes

Follow these guidelines:

**Code Style:**
- Use Java 17 features where appropriate
- Follow Google Java Style Guide
- Keep methods focused and single-responsibility
- Add meaningful comments for complex logic

**File Organization:**
- Game logic in `core/src/main/java/com/rewilderdev/`
- Assets in `android/src/main/assets/`
- Tests in `*/src/test/java/`

**Naming Conventions:**
- Classes: PascalCase (e.g., `RestorationSystem`)
- Methods: camelCase (e.g., `updateBiomeState`)
- Constants: UPPER_SNAKE_CASE (e.g., `MAX_BOND_LEVEL`)
- Files: Match class names

### 5. Test Your Changes

```bash
# Run all tests
./gradlew test

# Build debug APK
./gradlew assembleDebug

# Test on device or emulator
adb install -r android/build/outputs/apk/debug/android-debug.apk
```

### 6. Commit Changes

```bash
# Stage changes
git add .

# Commit with descriptive message
git commit -m "feat: add bonding system for creatures

- Implement BondingSystem class
- Add creature bond level tracking (0-5)
- Implement restoration gain on successful bonding
- Add unit tests for bonding logic"
```

**Commit Message Format:**
- Type: `feat`, `fix`, `refactor`, `docs`, `test`, `chore`
- Scope: System or component name
- Description: Clear, concise explanation
- Body: Detailed changes (optional)

### 7. Push and Create Pull Request

```bash
git push origin feature/your-feature-name
```

Then create a PR on GitHub with:
- Clear title describing the change
- Description of what was changed and why
- Reference to any related issues (#123)
- Screenshots or videos if UI-related

## Code Review Process

1. **Automated Checks:** GitHub Actions runs:
   - Gradle build
   - Unit tests
   - Code quality checks
   - Lint analysis

2. **Manual Review:** Maintainers check:
   - Code quality and style
   - Architecture alignment
   - Test coverage
   - Documentation

3. **Approval & Merge:** Once approved:
   - Squash commits if needed
   - Merge to main branch
   - Delete feature branch

## Reporting Issues

### Bug Reports

Include:
- Device/OS version
- Steps to reproduce
- Expected vs actual behavior
- Logs or error messages
- Screenshots if applicable

### Feature Requests

Include:
- Use case and motivation
- Proposed solution
- Alternative approaches
- Related issues

## Documentation

### Code Documentation

```java
/**
 * Manages biome restoration values and state transitions.
 *
 * Tracks restoration progress (0-100%) and triggers state changes
 * at thresholds: dead (0-24%), partial (25-49%), active (50-74%), restored (75-100%).
 *
 * @see RestorationEventListener
 */
public class RestorationSystem {
    // ...
}
```

### README Updates

- Update README.md for user-facing changes
- Update SETUP.md for build/environment changes
- Add examples for new features

## Testing Guidelines

### Unit Tests

```java
@Test
public void testBondingIncrementsLevel() {
    Creature creature = new Creature();
    creature.bondLevel = 2;
    
    bondingSystem.bond(creature, "forest_01", BondingSystem.InteractionResult.SUCCESS);
    
    assertEquals(3, creature.bondLevel);
}
```

### Integration Tests

- Test system interactions
- Verify save/load functionality
- Test audio crossfades

### Manual Testing

- Test on minimum target device (API 24)
- Test on high-end device
- Test on tablet and phone
- Test all biomes and creatures

## Performance Considerations

- Keep frame time under 16ms (60 FPS)
- Monitor memory usage (target: ≤128MB)
- Profile with Android Profiler
- Optimize hot paths

## Asset Contribution

### Sprites & Tiles
- Format: PNG, 16×16 or 32×32 pixels
- Color palette: 256 colors max
- Naming: `biome_[name]_[state]_[tile_id].png`

### Audio
- Format: OGG Vorbis, 44.1 kHz, 16-bit
- Ambient loops: 2-4 seconds
- SFX: 0.5-3 seconds
- Naming: `[type]_[biome]_[state].ogg`

### Maps
- Format: TMX (Tiled Editor)
- Tile size: 16×16 or 32×32 (consistent)
- Layers: ground, collision, decoration, interaction

## Release Process

1. **Version Bump:** Update version in `build.gradle`
2. **Changelog:** Document changes in CHANGELOG.md
3. **Tag Release:** `git tag v1.0.0`
4. **Push Tag:** `git push origin v1.0.0`
5. **GitHub Release:** Create release with APK/AAB
6. **Google Play:** Submit AAB to Play Store

## Getting Help

- **Questions:** Open a discussion on GitHub
- **Bugs:** Create an issue with bug report template
- **Features:** Create an issue with feature request template
- **Chat:** Join Discord community (if available)

## Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Acknowledge contributions
- Report inappropriate behavior

## License

By contributing, you agree that your contributions will be licensed under the same license as the project (Proprietary).

---

Thank you for contributing to RE-WILDER! 🌱
