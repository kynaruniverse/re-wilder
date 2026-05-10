package com.rewilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * RE-WILDER — WorldManager
 *
 * Phase 1: Loads TMX map, renders tiles, owns the camera.
 *
 * RESPONSIBILITY: Map loading, tile rendering, camera tracking.
 * PROHIBITED: Player input, restoration logic, creature logic.
 */
public class WorldManager {

    public static final int TILE_SIZE = 16;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    // The collision layer name in the TMX file
    private static final String COLLISION_LAYER = "collision";

    public WorldManager() {
        // Load the map
        map = new TmxMapLoader().load("maps/forest_01.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f);

        // Camera — shows a 320x180 pixel window (20x11.25 tiles at 16px)
        // This gives a GBA-style viewport that scales to any screen
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 180);
        camera.update();

        Gdx.app.log("WorldManager", "Map loaded: forest_01.tmx");
    }

    /**
     * Update camera to follow the player, clamped to map bounds.
     */
    public void update(float delta, float playerX, float playerY) {
        // Get map dimensions in pixels
        int mapWidthPx  = getMapWidthTiles()  * TILE_SIZE;
        int mapHeightPx = getMapHeightTiles() * TILE_SIZE;

        // Camera half-sizes
        float halfW = camera.viewportWidth  / 2f;
        float halfH = camera.viewportHeight / 2f;

        // Clamp camera so it never shows outside the map
        float camX = Math.max(halfW, Math.min(playerX + TILE_SIZE / 2f, mapWidthPx  - halfW));
        float camY = Math.max(halfH, Math.min(playerY + TILE_SIZE / 2f, mapHeightPx - halfH));

        camera.position.set(camX, camY, 0);
        camera.update();
    }

    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public void resize(int width, int height) {
        // Maintain fixed 320x180 logical resolution — pixel art stays crisp
        camera.setToOrtho(false, 320, 180);
        camera.update();
    }

    /**
     * Returns true if the given pixel-space rectangle overlaps a solid tile
     * in the collision layer. Called by Player for movement resolution.
     *
     * @param x      left edge of bounding box in pixels
     * @param y      bottom edge of bounding box in pixels
     * @param width  width of bounding box in pixels
     * @param height height of bounding box in pixels
     */
    public boolean isSolid(float x, float y, float width, float height) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(COLLISION_LAYER);
        if (layer == null) return false;

        // Check all four corners of the bounding box
        int tileSize = TILE_SIZE;

        int x1 = (int) (x / tileSize);
        int y1 = (int) (y / tileSize);
        int x2 = (int) ((x + width - 1) / tileSize);
        int y2 = (int) ((y + height - 1) / tileSize);

        for (int ty = y1; ty <= y2; ty++) {
            for (int tx = x1; tx <= x2; tx++) {
                TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                if (cell != null && cell.getTile() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMapWidthTiles() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        return layer.getWidth();
    }

    public int getMapHeightTiles() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        return layer.getHeight();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setTileSet(String state) {
        // Phase 3: swap tileset atlas based on restoration state
        // Valid states: "dead", "partial", "active", "restored"
    }

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
