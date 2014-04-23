package com.arrowgames.zk.bobbyjump;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.arrowgames.zk.bobbyjump.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;

public class BobbyJump extends Game {
	
	FPSLogger fpsLogger;
	
	@Override
	public void create() {		
		Assets.instance.ini(new AssetManager());
		setScreen(new SplashScreen(this));
		
		fpsLogger = new FPSLogger();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		super.render();
		
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
