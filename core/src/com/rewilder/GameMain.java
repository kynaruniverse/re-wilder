package com.rewilder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * RE-WILDER — GameMain
 *
 * Phase 0: Engine bootstrap only.
 * Renders a blank screen to confirm the pipeline is working.
 *
 * RESPONSIBILITY: Application lifecycle and system wiring only.
 * No gameplay logic lives here.
 */
public class GameMain extends ApplicationAdapter {

    @Override
    public void create() {
        Gdx.app.log("RE-WILDER", "Phase 0 — Engine bootstrap confirmed.");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.10f, 0.10f, 0.10f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        // Nothing to dispose at Phase 0
    }
}
