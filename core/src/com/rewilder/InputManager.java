package com.rewilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * RE-WILDER — InputManager
 *
 * RESPONSIBILITY: Abstracts all raw input into named game actions.
 * All systems query this — never Gdx.input directly.
 *
 * Phase 1: Keyboard only. Touch support added in a later phase.
 *
 * PERMITTED: Read Gdx.input, expose boolean action states.
 * PROHIBITED: Gameplay logic, rendering, state mutation.
 */
public class InputManager {

    /**
     * Returns true while the move-left action is held.
     * Arrow Left or A key.
     */
    public boolean isMoveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.A);
    }

    /**
     * Returns true while the move-right action is held.
     * Arrow Right or D key.
     */
    public boolean isMoveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                || Gdx.input.isKeyPressed(Input.Keys.D);
    }

    /**
     * Returns true while the move-up action is held.
     * Arrow Up or W key.
     */
    public boolean isMoveUp() {
        return Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyPressed(Input.Keys.W);
    }

    /**
     * Returns true while the move-down action is held.
     * Arrow Down or S key.
     */
    public boolean isMoveDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyPressed(Input.Keys.S);
    }

    /**
     * Returns true on the frame the interact action is first pressed.
     * Enter or Space. Used for bonding interaction confirmation (Phase 2+).
     */
    public boolean isInteractJustPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    /**
     * Returns true on the frame the pause action is first pressed.
     * Escape key.
     */
    public boolean isPauseJustPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
    }
}