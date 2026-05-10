package com.rewilder;

/**
 * RE-WILDER — WorldManager
 *
 * Phase 0: Stub only.
 *
 * RESPONSIBILITY: Map loading, tile rendering, world state transitions,
 * tileset swapping, and camera management.
 *
 * Will be fully implemented in Phase 1.
 */
public class WorldManager {

    public WorldManager() {
        // Phase 0: no initialisation required
    }

    public void update(float delta) {
        // Phase 1: update camera, check tile interactions
    }

    public void render() {
        // Phase 1: render tile map, entities
    }

    public void setTileSet(String state) {
        // Phase 3: swap tileset atlas based on restoration state
        // Valid states: "dead", "partial", "active", "restored"
    }

    public void dispose() {
        // Phase 1: dispose tile map and textures
    }
}
