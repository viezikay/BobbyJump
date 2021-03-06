package com.arrowgames.zk.bobbyjump.screens;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SplashScreen implements Screen {

	Game game;
	OrthographicCamera camera;
	SpriteBatch batch;
	
	TextureRegion libgdx;
	
	float stateTime = 0;
	float alpha = 0;
	
	public SplashScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stateTime += delta;
		
		if (stateTime < 1) {
			alpha += delta;
			alpha = Math.min(alpha, 1);
		}
		else if (stateTime > 3) {
			alpha -= delta;
			alpha = Math.max(alpha, 0);
			if (alpha <= 0)
				game.setScreen(new GameScreen());
		}
			
		batch.setColor(1, 1, 1, alpha);
		batch.begin();
		batch.draw(libgdx, (320-libgdx.getRegionWidth())/2, (480-libgdx.getRegionHeight())/2);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void show() {
		
		 camera = new OrthographicCamera(320, 480);
		 camera.position.set(160, 240, 0);
		 camera.update();
		 
		 batch = new SpriteBatch();
		 batch.setProjectionMatrix(camera.combined);
		 
		 libgdx = Assets.instance.splash.libgdx;
	}

	@Override
	public void hide() {		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {		
	}

	@Override
	public void dispose() {		
	}
}

