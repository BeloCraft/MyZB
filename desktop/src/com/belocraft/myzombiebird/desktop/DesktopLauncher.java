package com.belocraft.myzombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.belocraft.myzombiebird.MZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "My Zombie Bird";
                config.width = 272;
                config.height = 408;
                new LwjglApplication(new MZBGame(), config);
	}
}
