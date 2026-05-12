package com.rewilderdev.systems;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages biome restoration values and state transitions
 */
public class RestorationSystem {

    private final Map<String, Float> biomeRestorationMap = new HashMap<>();
    private final Map<String, String> biomeStateCache = new HashMap<>();
    private final WorldManager worldManager;
    private final AudioManager audioManager;
    private final List<RestorationEventListener> listeners = new ArrayList<>();

    public RestorationSystem(WorldManager worldManager, AudioManager audioManager) {
        this.worldManager = worldManager;
        this.audioManager = audioManager;
    }

    public void addListener(RestorationEventListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void restore(String biomeId, float amount) {
        float current = biomeRestorationMap.getOrDefault(biomeId, 0f);
        current = Math.max(0f, Math.min(100f, current + amount));
        biomeRestorationMap.put(biomeId, current);
        updateBiomeState(biomeId, current);
    }

    private void updateBiomeState(String biomeId, float restoration) {
        final String newState;
        if (restoration <= 24f)      newState = "dead";
        else if (restoration <= 49f) newState = "partial";
        else if (restoration <= 74f) newState = "active";
        else                         newState = "restored";

        final String previousState = biomeStateCache.getOrDefault(biomeId, "");

        if (!newState.equals(previousState)) {
            biomeStateCache.put(biomeId, newState);
            worldManager.setTileSet(biomeId, newState);
            audioManager.onBiomeStateChanged(biomeId, newState);

            // Notify all listeners
            for (RestorationEventListener listener : listeners) {
                listener.onBiomeStateChanged(biomeId, previousState, newState, restoration);
            }
        }
    }

    public float getRestorationProgress(String biomeId) {
        return biomeRestorationMap.getOrDefault(biomeId, 0f);
    }

    public void initBiome(String biomeId, float savedValue) {
        biomeRestorationMap.put(biomeId, Math.max(0f, Math.min(100f, savedValue)));
        updateBiomeState(biomeId, biomeRestorationMap.get(biomeId));
    }

    public String getBiomeState(String biomeId) {
        return biomeStateCache.getOrDefault(biomeId, "dead");
    }
}
