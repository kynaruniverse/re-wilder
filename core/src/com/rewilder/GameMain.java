package com.rewilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * RE-WILDER — GameMain
 *
 * Phase 1: Application lifecycle and system wiring only.
 * Owns WorldManager and Player. Delegates all logic to them.
 *
 * PROHIBITED: Gameplay logic, rendering logic, input reading.
 */
public class GameMain extends ApplicationAdapter {

    private WorldManager worldManager;
    private Player player;

    @Override
    public void create() {
        worldManager = new WorldManager();
        player = new Player(worldManager);
        Gdx.app.log("RE-WILDER", "Phase 1 — World movement core initialised.");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.10f, 0.10f, 0.10f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);
        worldManager.update(delta, player.getX(), player.getY());
        worldManager.render();
    }

    @Override
    public void resize(int width, int height) {
        worldManager.resize(width, height);
    }

    @Override
    public void dispose() {
        worldManager.dispose();
        player.dispose();
    }
}
