package com.rewilderdev.systems;

import com.rewilderdev.game.Creature;

/**
 * Routes interactions to appropriate challenge type and resolves outcomes
 */
public class InteractionTypeDispatcher {

    private final BondingSystem bondingSystem;

    public InteractionTypeDispatcher(BondingSystem bondingSystem) {
        this.bondingSystem = bondingSystem;
    }

    public void attemptInteraction(Creature creature, String biomeId) {
        if (creature == null) return;

        // Route to appropriate interaction type
        switch (creature.interactionType) {
            case "timing_input":
                handleTimingInput(creature, biomeId);
                break;
            case "movement_sync":
                handleMovementSync(creature, biomeId);
                break;
            case "environmental_stabilisation":
                handleEnvironmentalStabilization(creature, biomeId);
                break;
            case "rhythm_alignment":
                handleRhythmAlignment(creature, biomeId);
                break;
        }
    }

    private void handleTimingInput(Creature creature, String biomeId) {
        // Simulate successful interaction for now
        bondingSystem.bond(creature, biomeId, BondingSystem.InteractionResult.SUCCESS);
    }

    private void handleMovementSync(Creature creature, String biomeId) {
        bondingSystem.bond(creature, biomeId, BondingSystem.InteractionResult.SUCCESS);
    }

    private void handleEnvironmentalStabilization(Creature creature, String biomeId) {
        bondingSystem.bond(creature, biomeId, BondingSystem.InteractionResult.SUCCESS);
    }

    private void handleRhythmAlignment(Creature creature, String biomeId) {
        bondingSystem.bond(creature, biomeId, BondingSystem.InteractionResult.SUCCESS);
    }

    public void update(float delta) {
        // Update interaction state machine
    }
}
