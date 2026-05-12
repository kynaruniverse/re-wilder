package com.rewilderdev.systems;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages creature discovery and codex entries
 */
public class CodexSystem {

    private final Set<String> discoveredCreatures = new HashSet<>();
    private final Set<String> completedBiomes = new HashSet<>();

    public void onCreatureBonded(String creatureId, int bondLevel) {
        discoveredCreatures.add(creatureId);
    }

    public boolean isCreatureDiscovered(String creatureId) {
        return discoveredCreatures.contains(creatureId);
    }

    public void markBiomeComplete(String biomeId) {
        completedBiomes.add(biomeId);
    }

    public boolean isBiomeComplete(String biomeId) {
        return completedBiomes.contains(biomeId);
    }

    public Set<String> getDiscoveredCreatures() {
        return new HashSet<>(discoveredCreatures);
    }
}
