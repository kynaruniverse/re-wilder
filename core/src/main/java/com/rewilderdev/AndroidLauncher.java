package com.rewilderdev;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Android platform bootstrap for RE-WILDER
 * 
 * No gameplay logic in this class - it only initializes the Android platform
 * and passes control to GameMain.
 */
public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useWakelock = true;
        config.hideStatusBar = true;

        initialize(new GameMain(), config);
    }
}
