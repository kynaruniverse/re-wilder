package com.rewilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * RE-WILDER — WorldManager
 *
 * RESPONSIBILITY: Map loading, tile rendering, camera management,
 *                 player sprite rendering, tileset state swapping (Phase 3).
 *
 * PERMITTED: TMX loading/disposal, tile rendering, camera tracking,
 *            receiving biome state change notifications (Phase 3+),
 *            rendering all world-space sprites including the player.
 * PROHIBITED: Direct save data writes, creature spawn logic,
 *             progression decisions, input reading.
 */
public class WorldManager {

    public static final int TILE_SIZE = 16;

    // Viewport size in world pixels — GBA-style fixed logical resolution.
    // Scales to any screen size while keeping pixel art crisp.
    private static final float VIEWPORT_W = 320f;
    private static final float VIEWPORT_H = 180f;

    private static final String COLLISION_LAYER = "collision";

    private TiledMap          map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera          camera;

    // SpriteBatch is owned here — single global instance per TDD §5.2
    private SpriteBatch batch;

    // Player sprite loaded and drawn by WorldManager, not Player
    private Texture playerTexture;

    public WorldManager() {
        map         = new TmxMapLoader().load("maps/forest_01.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_W, VIEWPORT_H);
        camera.update();

        batch         = new SpriteBatch();
        playerTexture = new Texture(Gdx.files.internal("sprites/player.png"));

        Gdx.app.log("WorldManager", "Loaded: forest_01.tmx");
    }

    /**
     * Update camera to follow the player, clamped to map bounds.
     * Called from GameMain after player.update() so camera tracks
     * the player's already-resolved position for this frame.
     *
     * @param playerX player's bottom-left X in world pixels
     * @param playerY player's bottom-left Y in world pixels
     */
    public void update(float delta, float playerX, float playerY) {
        int mapWidthPx  = getMapWidthTiles()  * TILE_SIZE;
        int mapHeightPx = getMapHeightTiles() * TILE_SIZE;

        float halfW = camera.viewportWidth  / 2f;
        float halfH = camera.viewportHeight / 2f;

        // Centre camera on player sprite centre, clamped to map edges
        float camX = Math.max(halfW, Math.min(playerX + TILE_SIZE / 2f, mapWidthPx  - halfW));
        float camY = Math.max(halfH, Math.min(playerY + TILE_SIZE / 2f, mapHeightPx - halfH));

        camera.position.set(camX, camY, 0);
        camera.update();
    }

    /**
     * Render the tile map then the player sprite on top.
     * Called once per frame from GameMain after all updates.
     *
     * @param playerX player's bottom-left X in world pixels
     * @param playerY player's bottom-left Y in world pixels
     */
    public void render(float playerX, float playerY) {
        // 1. Tile map
        mapRenderer.setView(camera);
        mapRenderer.render();

        // 2. Player sprite — drawn in world space using the same camera
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerTexture, playerX, playerY, TILE_SIZE, TILE_SIZE);
        batch.end();
    }

    /**
     * Resize handler — maintains fixed logical resolution.
     * Pixel art stays crisp at any physical screen size.
     */
    public void resize(int width, int height) {
        camera.setToOrtho(false, VIEWPORT_W, VIEWPORT_H);
        camera.update();
    }

    /**
     * Returns true if the given pixel-space rectangle overlaps any solid
     * cell in the collision layer. Called by Player for movement resolution.
     *
     * @param x      left edge of bounding box in world pixels
     * @param y      bottom edge of bounding box in world pixels
     * @param width  width of bounding box in pixels
     * @param height height of bounding box in pixels
     */
    public boolean isSolid(float x, float y, float width, float height) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(COLLISION_LAYER);
        if (layer == null) return false;

        int x1 = (int) (x / TILE_SIZE);
        int y1 = (int) (y / TILE_SIZE);
        int x2 = (int) ((x + width  - 1) / TILE_SIZE);
        int y2 = (int) ((y + height - 1) / TILE_SIZE);

        for (int ty = y1; ty <= y2; ty++) {
            for (int tx = x1; tx <= x2; tx++) {
                TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                if (cell != null && cell.getTile() != null) return true;
            }
        }
        return false;
    }

    // ─── Tileset swap (Phase 3 stub) ─────────────────────────────────────────

    /**
     * Swap the active tileset to reflect a new restoration state.
     * Valid states: "dead", "partial", "active", "restored"
     * Full implementation in Phase 3.
     */
    public void setTileSet(String state) {
        Gdx.app.log("WorldManager", "setTileSet called: " + state + " (Phase 3)");
    }

    // ─── Map dimension helpers ────────────────────────────────────────────────

    public int getMapWidthTiles() {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
    }

    public int getMapHeightTiles() {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getHeight();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    // ─── Lifecycle ────────────────────────────────────────────────────────────

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        batch.dispose();
        playerTexture.dispose();
    }
}