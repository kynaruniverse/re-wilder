package com.rewilderdev.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rewilderdev.systems.TouchInputManager;
import com.rewilderdev.systems.InteractionTypeDispatcher;

/**
 * Player entity - handles movement, collision, and interaction state
 */
public class Player {

    public Vector2 position;
    public Vector2 velocity;
    public float speed = 100f; // pixels per second

    private TouchInputManager touchInputManager;
    private InteractionTypeDispatcher interactionDispatcher;
    private Creature activeCreature;
    private String currentBiomeId;

    public Player(TouchInputManager touchInputManager, InteractionTypeDispatcher interactionDispatcher) {
        this.touchInputManager = touchInputManager;
        this.interactionDispatcher = interactionDispatcher;
        this.position = new Vector2(10, 10); // Starting position
        this.velocity = new Vector2(0, 0);
        this.currentBiomeId = "forest_01";
    }

    public void update(float delta) {
        // Read input from touch manager
        Vector2 input = touchInputManager.getMovementInput();

        // Update velocity based on input
        velocity.set(input).scl(speed);

        // Update position
        position.add(velocity.x * delta, velocity.y * delta);

        // Clamp to tile grid (16-pixel tiles)
        position.x = Math.round(position.x / 16) * 16;
        position.y = Math.round(position.y / 16) * 16;

        // Check for creature interactions
        if (touchInputManager.isActionButtonPressed()) {
            interactionDispatcher.attemptInteraction(activeCreature, currentBiomeId);
        }
    }

    public void render(SpriteBatch batch) {
        // Render player sprite at position
        // TODO: Load and render player sprite from atlas
    }

    public void setActiveCreature(Creature creature) {
        this.activeCreature = creature;
    }

    public void setCurrentBiome(String biomeId) {
        this.currentBiomeId = biomeId;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getCurrentBiomeId() {
        return currentBiomeId;
    }
}
