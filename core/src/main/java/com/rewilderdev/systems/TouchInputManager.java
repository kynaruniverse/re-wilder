package com.rewilderdev.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * Manages touch input - virtual joystick and action button
 */
public class TouchInputManager extends InputAdapter {

    private Vector2 movementInput = new Vector2(0, 0);
    private boolean actionButtonPressed = false;
    private float controlAutoHideTimer = 0f;
    private static final float CONTROL_AUTO_HIDE_TIME = 3f;

    public TouchInputManager() {
        Gdx.input.setInputProcessor(this);
    }

    public void update(float delta) {
        // Update auto-hide timer
        controlAutoHideTimer += delta;
        if (controlAutoHideTimer > CONTROL_AUTO_HIDE_TIME) {
            // Controls would be hidden here
        }

        // Read keyboard input for testing (desktop)
        movementInput.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) movementInput.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) movementInput.y -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) movementInput.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) movementInput.x += 1;

        // Normalize diagonal movement
        if (movementInput.len() > 0) {
            movementInput.nor();
            controlAutoHideTimer = 0f; // Reset auto-hide timer on input
        }

        // Action button (SPACE for testing)
        actionButtonPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public Vector2 getMovementInput() {
        return movementInput;
    }

    public boolean isActionButtonPressed() {
        return actionButtonPressed;
    }

    public boolean isControlsHidden() {
        return controlAutoHideTimer > CONTROL_AUTO_HIDE_TIME;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controlAutoHideTimer = 0f;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        controlAutoHideTimer = 0f;
        return true;
    }
}
