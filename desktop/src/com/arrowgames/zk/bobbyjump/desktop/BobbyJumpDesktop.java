package com.arrowgames.zk.bobbyjump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arrowgames.zk.bobbyjump.BobbyJump;

public class BobbyJumpDesktop {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 320;
		config.height = 480;
		new LwjglApplication(new BobbyJump(), config);
	}
}
