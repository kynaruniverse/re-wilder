package com.rewilderdev.systems;

import com.rewilderdev.game.Creature;

/**
 * Manages creature bonding and interaction outcomes
 */
public class BondingSystem {

    private final RestorationSystem restorationSystem;
    private final CodexSystem codexSystem;

    public enum InteractionResult {
        SUCCESS,
        FAILURE,
        INTERRUPTED
    }

    public BondingSystem(RestorationSystem restorationSystem, CodexSystem codexSystem) {
        this.restorationSystem = restorationSystem;
        this.codexSystem = codexSystem;
    }

    public void bond(Creature creature, String currentBiomeId, InteractionResult result) {
        switch (result) {
            case SUCCESS:
                creature.bonded = true;
                final int previousBondLevel = creature.bondLevel;
                creature.bondLevel = Math.min(creature.bondLevel + 1, 5);

                final float gain = creature.bondingRestorationGain > 0f
                    ? creature.bondingRestorationGain
                    : 10.0f;

                restorationSystem.restore(currentBiomeId, gain);
                codexSystem.onCreatureBonded(creature.id, creature.bondLevel);
                break;

            case FAILURE:
                // No state changes. InteractionTypeDispatcher resets to idle.
                break;

            case INTERRUPTED:
                // No state changes. No failure record. InteractionTypeDispatcher resets to idle.
                break;
        }
    }

    public void update(float delta) {
        // Bonding system update logic if needed
    }
}
