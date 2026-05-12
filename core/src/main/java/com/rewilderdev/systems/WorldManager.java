package com.rewilderdev.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Manages world rendering and map management
 */
public class WorldManager {

    private OrthographicCamera camera;
    private TiledMap currentMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private String currentBiomeId;
    private String currentBiomeState = "dead";

    public WorldManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void loadBiome(String biomeId) {
        this.currentBiomeId = biomeId;
        this.currentBiomeState = "dead";

        // Load TMX map
        String mapPath = "maps/" + biomeId + ".tmx";
        try {
            currentMap = new TmxMapLoader().load(mapPath);
            mapRenderer = new OrthogonalTiledMapRenderer(currentMap);
        } catch (Exception e) {
            System.err.println("Failed to load map: " + mapPath);
            e.printStackTrace();
        }
    }

    public void setTileSet(String biomeId, String state) {
        this.currentBiomeState = state;
        // In a full implementation, this would swap the tile atlas
        // For now, we just track the state
    }

    public void update(float delta) {
        // Update camera position
        camera.update();
    }

    public void render(SpriteBatch batch) {
        if (mapRenderer != null) {
            mapRenderer.setView(camera);
            mapRenderer.render();
        }
    }

    public void checkTextureRebind() {
        // Handle texture rebinding for Android devices
    }

    public void onLowMemory() {
        // Log memory usage for diagnostics
    }

    public void dispose() {
        if (mapRenderer != null) {
            mapRenderer.dispose();
        }
        if (currentMap != null) {
            currentMap.dispose();
        }
    }

    public String getCurrentBiomeId() {
        return currentBiomeId;
    }

    public String getCurrentBiomeState() {
        return currentBiomeState;
    }
}
