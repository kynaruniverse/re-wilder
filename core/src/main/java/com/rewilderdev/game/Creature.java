package com.rewilderdev.game;

import com.badlogic.gdx.utils.Array;

/**
 * Creature data container - no logic, data fields only
 */
public class Creature {

    // Identity
    public String id;
    public String displayName;

    // Role classification: "traversal" | "restoration" | "support"
    public String role;

    // Traversal capability: "water_shallow" | "water_deep" | "climb" | "dig" | "flight" | "none"
    public String traversalType;

    // Interaction type: "timing_input" | "movement_sync" | "environmental_stabilisation" | "rhythm_alignment"
    public String interactionType;

    // Bond state
    public boolean bonded;
    public int bondLevel; // 0–5, never decreases

    // Abilities unlocked at current and prior bond levels
    public Array<String> abilities;

    // Biome affinity: list of biome IDs this creature is native to
    public Array<String> biomeAffinity;

    // Restoration gain granted to current biome on successful bonding
    public float bondingRestorationGain;

    // Assignment state
    public boolean unlocked;
    public String assignedBiome;   // null if not assigned
    public String assignmentSlot;  // "biome" | "village" | "none"

    // Description for codex
    public String description;

    public Creature() {
        this.abilities = new Array<>();
        this.biomeAffinity = new Array<>();
        this.bonded = false;
        this.bondLevel = 0;
        this.unlocked = false;
        this.assignedBiome = null;
        this.assignmentSlot = "none";
    }
}
