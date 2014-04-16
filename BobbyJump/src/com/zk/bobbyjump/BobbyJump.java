package com.zk.bobbyjump;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.zk.bobbyjump.managers.Assets;
import com.zk.bobbyjump.screens.SplashScreen;

public class BobbyJump extends Game {
	
	@Override
	public void create() {		
		Assets.instance.ini(new AssetManager());
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		super.render();
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
