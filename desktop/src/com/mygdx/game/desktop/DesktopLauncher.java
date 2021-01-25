package com.mygdx.game.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.backgroundFPS = MyGdxGame.fps;
		config.foregroundFPS = MyGdxGame.fps;
		config.forceExit = false;

		config.width = MyGdxGame.WIDTH;
		config.height = MyGdxGame.HEIGHT;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
