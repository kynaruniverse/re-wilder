package com.rewilderdev.systems;

/**
 * Interface for systems that react to biome state changes
 * 
 * This is the sole mechanism for broadcasting biome state changes.
 * All systems that react to restoration events must implement this interface
 * and register via RestorationSystem.addListener().
 */
public interface RestorationEventListener {

    /**
     * Called when a biome's restoration state changes
     *
     * @param biomeId The ID of the biome that changed
     * @param previousState The previous restoration state (e.g., "dead")
     * @param newState The new restoration state (e.g., "partial")
     * @param restoration The current restoration value (0-100)
     */
    void onBiomeStateChanged(String biomeId, String previousState, String newState, float restoration);
}
