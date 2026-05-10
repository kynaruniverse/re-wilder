package com.rewilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * RE-WILDER — GameMain
 *
 * RESPONSIBILITY: Application lifecycle and system wiring only.
 * Owns all top-level system references. Runs the update/render loop.
 *
 * PERMITTED: create(), render(), resize(), dispose() — system wiring only.
 * PROHIBITED: Gameplay logic, rendering logic, input handling,
 *             save operations, direct game state changes.
 */
public class GameMain extends ApplicationAdapter {

    private InputManager  inputManager;
    private WorldManager  worldManager;
    private Player        player;

    @Override
    public void create() {
        inputManager = new InputManager();
        worldManager = new WorldManager();
        player       = new Player(worldManager, inputManager);

        Gdx.app.log("RE-WILDER", "Phase 1 — World movement core initialised.");
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0.10f, 0.10f, 0.10f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        // UPDATE ORDER (per TDD §4):
        // 1. Player resolves movement for this frame
        // 2. WorldManager positions camera on the resolved player position
        // 3. WorldManager renders tiles then player sprite
        player.update(delta);
        worldManager.update(delta, player.getX(), player.getY());
        worldManager.render(player.getX(), player.getY());
    }

    @Override
    public void resize(int width, int height) {
        worldManager.resize(width, height);
    }

    @Override
    public void dispose() {
        worldManager.dispose();
        // Player owns no disposable resources in Phase 1
    }
}