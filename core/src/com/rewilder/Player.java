package com.rewilder;

/**
 * RE-WILDER — Player
 *
 * RESPONSIBILITY: Player movement, collision resolution, position state.
 * Queries InputManager for input. Queries WorldManager for collision.
 *
 * PERMITTED: Reading InputManager, tile-aligned movement, collision queries,
 *            holding active creature reference (Phase 2+).
 * PROHIBITED: Rendering logic, SpriteBatch ownership, Gdx.input access,
 *             direct restoration calls, save operations, bonding logic.
 */
public class Player {

    // Movement speed in pixels per second
    private static final float SPEED = 80f;

    // Bounding box slightly smaller than tile for forgiving collision
    private static final float BBOX_W        = 12f;
    private static final float BBOX_H        = 12f;
    private static final float BBOX_OFFSET_X = 2f;
    private static final float BBOX_OFFSET_Y = 2f;

    // Position — bottom-left of the 16x16 sprite cell in world pixels
    private float x;
    private float y;

    private final WorldManager worldManager;
    private final InputManager inputManager;

    public Player(WorldManager worldManager, InputManager inputManager) {
        this.worldManager = worldManager;
        this.inputManager = inputManager;

        // Spawn at tile (2, 2) — confirmed open area in forest_01.tmx
        this.x = 2 * WorldManager.TILE_SIZE;
        this.y = 2 * WorldManager.TILE_SIZE;
    }

    /**
     * Update player position based on input and collision.
     * Called once per frame from GameMain. No rendering here.
     */
    public void update(float delta) {
        float dx = 0;
        float dy = 0;

        if (inputManager.isMoveLeft())  dx -= SPEED * delta;
        if (inputManager.isMoveRight()) dx += SPEED * delta;
        if (inputManager.isMoveDown())  dy -= SPEED * delta;
        if (inputManager.isMoveUp())    dy += SPEED * delta;

        // Resolve X independently from Y — prevents corner-sticking
        if (dx != 0) {
            float newX = x + dx;
            if (!worldManager.isSolid(newX + BBOX_OFFSET_X, y + BBOX_OFFSET_Y, BBOX_W, BBOX_H)) {
                x = newX;
            }
        }

        if (dy != 0) {
            float newY = y + dy;
            if (!worldManager.isSolid(x + BBOX_OFFSET_X, newY + BBOX_OFFSET_Y, BBOX_W, BBOX_H)) {
                y = newY;
            }
        }

        // Clamp to map bounds — player can never exit the tile map
        int mapW = worldManager.getMapWidthTiles()  * WorldManager.TILE_SIZE;
        int mapH = worldManager.getMapHeightTiles() * WorldManager.TILE_SIZE;
        x = Math.max(0, Math.min(x, mapW - WorldManager.TILE_SIZE));
        y = Math.max(0, Math.min(y, mapH - WorldManager.TILE_SIZE));
    }

    // ─── Accessors ────────────────────────────────────────────────────────────

    /** Bottom-left X of the player sprite in world pixels. */
    public float getX() { return x; }

    /** Bottom-left Y of the player sprite in world pixels. */
    public float getY() { return y; }
}