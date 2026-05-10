package com.rewilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * RE-WILDER — Player
 *
 * Phase 1: 4-direction movement with tile collision. Renders player sprite.
 *
 * RESPONSIBILITY: Input reading, movement, collision resolution, sprite rendering.
 * PROHIBITED: Direct restoration calls, save operations, bonding logic.
 */
public class Player {

    // Movement speed in pixels per second
    private static final float SPEED = 80f;

    // Bounding box is slightly smaller than tile for forgiving collision
    private static final float BBOX_W = 12f;
    private static final float BBOX_H = 12f;

    // Offset from position to bounding box top-left
    private static final float BBOX_OFFSET_X = 2f;
    private static final float BBOX_OFFSET_Y = 2f;

    // Position — bottom-left of the 16x16 sprite in world pixels
    private float x;
    private float y;

    private WorldManager worldManager;
    private Texture sprite;
    private SpriteBatch batch;

    public Player(WorldManager worldManager) {
        this.worldManager = worldManager;

        // Spawn at tile (2, 2) — safe open area confirmed in TMX
        this.x = 2 * WorldManager.TILE_SIZE;
        this.y = 2 * WorldManager.TILE_SIZE;

        sprite = new Texture(Gdx.files.internal("sprites/player.png"));
        batch = new SpriteBatch();

        Gdx.app.log("Player", "Spawned at (" + x + ", " + y + ")");
    }

    public void update(float delta) {
        float dx = 0;
        float dy = 0;

        // Read input — both D-pad keys and WASD
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)  || Gdx.input.isKeyPressed(Input.Keys.A)) dx -= SPEED * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) dx += SPEED * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)  || Gdx.input.isKeyPressed(Input.Keys.S)) dy -= SPEED * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)    || Gdx.input.isKeyPressed(Input.Keys.W)) dy += SPEED * delta;

        // Resolve X movement independently from Y (axis-separated collision)
        if (dx != 0) {
            float newX = x + dx;
            if (!worldManager.isSolid(newX + BBOX_OFFSET_X, y + BBOX_OFFSET_Y, BBOX_W, BBOX_H)) {
                x = newX;
            }
        }

        // Resolve Y movement independently from X
        if (dy != 0) {
            float newY = y + dy;
            if (!worldManager.isSolid(x + BBOX_OFFSET_X, newY + BBOX_OFFSET_Y, BBOX_W, BBOX_H)) {
                y = newY;
            }
        }

        // Clamp to map bounds
        int mapW = worldManager.getMapWidthTiles()  * WorldManager.TILE_SIZE;
        int mapH = worldManager.getMapHeightTiles() * WorldManager.TILE_SIZE;
        x = Math.max(0, Math.min(x, mapW - WorldManager.TILE_SIZE));
        y = Math.max(0, Math.min(y, mapH - WorldManager.TILE_SIZE));

        // Render — inside update so we draw with the current camera
        render();
    }

    private void render() {
        batch.setProjectionMatrix(worldManager.getCamera().combined);
        batch.begin();
        batch.draw(sprite, x, y, WorldManager.TILE_SIZE, WorldManager.TILE_SIZE);
        batch.end();
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void dispose() {
        sprite.dispose();
        batch.dispose();
    }
}
