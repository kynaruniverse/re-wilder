package com.rewilderdev.systems;

/**
 * Manages biome transitions and screen fades
 */
public class TransitionManager {

    private final WorldManager worldManager;
    private final AudioManager audioManager;

    public TransitionManager(WorldManager worldManager, AudioManager audioManager) {
        this.worldManager = worldManager;
        this.audioManager = audioManager;
    }

    public void transitionToBiome(String biomeId) {
        // Fade out, load new biome, fade in
    }

    public void update(float delta) {
        // Update transition state
    }
}
