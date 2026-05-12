package com.rewilderdev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rewilderdev.systems.*;
import com.rewilderdev.game.Player;

/**
 * RE-WILDER Game Main Loop
 * 
 * Core execution model per frame:
 * 1. TouchInputManager.update(delta)
 * 2. Player.update(delta)
 * 3. InteractionTypeDispatcher.update(delta)
 * 4. BondingSystem.update(delta)
 * 5. RestorationSystem.update(delta)
 * 6. GateSystem.update(delta)
 * 7. CreatureSystem.update(delta)
 * 8. TransitionManager.update(delta)
 * 9. ProgressionSystem.update(delta)
 * 10. AudioManager.update(delta)
 * 11. Render sequence
 */
public class GameMain extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    // Core systems
    private TouchInputManager touchInputManager;
    private WorldManager worldManager;
    private RestorationSystem restorationSystem;
    private BondingSystem bondingSystem;
    private CodexSystem codexSystem;
    private CreatureSystem creatureSystem;
    private InteractionTypeDispatcher interactionTypeDispatcher;
    private GateSystem gateSystem;
    private ProgressionSystem progressionSystem;
    private AudioManager audioManager;
    private UIManager uiManager;
    private TransitionManager transitionManager;
    private WorldEventSystem worldEventSystem;
    private Player player;

    @Override
    public void create() {
        // Initialize graphics
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize systems with dependency injection
        touchInputManager = new TouchInputManager();
        audioManager = new AudioManager();
        worldManager = new WorldManager(camera);

        restorationSystem = new RestorationSystem(worldManager, audioManager);

        codexSystem = new CodexSystem();
        creatureSystem = new CreatureSystem();
        bondingSystem = new BondingSystem(restorationSystem, codexSystem);

        gateSystem = new GateSystem(worldManager, audioManager);
        restorationSystem.addListener(gateSystem);

        worldEventSystem = new WorldEventSystem(audioManager, worldManager, codexSystem);
        interactionTypeDispatcher = new InteractionTypeDispatcher(bondingSystem);
        transitionManager = new TransitionManager(worldManager, audioManager);
        progressionSystem = new ProgressionSystem();
        uiManager = new UIManager();
        player = new Player(touchInputManager, interactionTypeDispatcher);

        // Load initial biome
        worldManager.loadBiome("forest_01");
    }

    @Override
    public void render() {
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 0.05f); // Cap at 50ms

        // Update systems in order
        touchInputManager.update(delta);
        worldManager.update(delta);
        player.update(delta);
        creatureSystem.update(delta);
        bondingSystem.update(delta);
        interactionTypeDispatcher.update(delta);
        gateSystem.update(delta);
        worldEventSystem.update(delta);
        transitionManager.update(delta);
        progressionSystem.update(delta);
        audioManager.update(delta);

        // Render
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldManager.render(batch);
        creatureSystem.render(batch);
        player.render(batch);
        batch.end();

        uiManager.render(batch);
    }

    @Override
    public void pause() {
        progressionSystem.triggerAutosave();
        audioManager.pauseAll();
        creatureSystem.pausePassiveTick();
    }

    @Override
    public void resume() {
        audioManager.resumeAll();
        creatureSystem.resumePassiveTick();
        worldManager.checkTextureRebind();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        uiManager.onResize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        worldManager.dispose();
        audioManager.dispose();
        creatureSystem.dispose();
        uiManager.dispose();
    }
}
