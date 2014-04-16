package com.zk.bobbyjump.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zk.bobbyjump.managers.Assets;

public class SplashScreen implements Screen {

	Game game;
	OrthographicCamera camera;
	SpriteBatch batch;
	
	float stateTime = 0;
	float alpha = 0;
	boolean reverse = false;
	TextureRegion libgdx;
	
	public SplashScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (alpha < 1 && stateTime == 0) {
			alpha += delta;
			alpha = Math.min(alpha, 1);
		}
		else if (alpha == 1) {
			stateTime += delta;
			if (stateTime >= 2)
				alpha -= delta;
		}
		else {
			alpha -= delta;
			if (alpha <= 0)
				game.setScreen(new GameScreen());
		}
			
		batch.setColor(1, 1, 1, alpha);
		batch.begin();
		batch.draw(libgdx, 
				(320-libgdx.getRegionWidth())/2, (480-libgdx.getRegionHeight())/2);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
