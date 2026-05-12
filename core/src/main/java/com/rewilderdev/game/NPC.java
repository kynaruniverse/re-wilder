package com.rewilderdev.game;

import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;

/**
 * NPC data container - dialogue state and position
 */
public class NPC {

    public String id;
    public String displayName;
    public String role; // "guide" | "lore" | "merchant"
    public String biomeAffinity;
    public int activationThreshold; // Restoration % required to activate

    public Vector2 position;
    public String currentDialogueState; // "initial" | "partial" | "active" | "restored"
    public Map<String, String> dialogue; // State -> dialogue text

    public NPC() {
        this.position = new Vector2(0, 0);
        this.currentDialogueState = "initial";
        this.dialogue = new HashMap<>();
    }

    public String getDialogue() {
        return dialogue.getOrDefault(currentDialogueState, "...");
    }

    public void setDialogueState(String state) {
        this.currentDialogueState = state;
    }
}
