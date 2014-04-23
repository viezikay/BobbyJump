package com.arrowgames.zk.bobbyjump.screens;

import com.arrowgames.zk.bobbyjump.objects.Bobby;
import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {

	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer renderer;
	
	Bobby bobby;
	Platform platform;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(.4f, 0, .4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		bobby.update(delta);
		platform.update(delta);
		
		bobby.render(batch);
		platform.render(batch);
		
//		bobby.renderBound(renderer);
//		platform.renderBound(renderer);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
		camera = new OrthographicCamera(Constants.ViewportW, Constants.viewportH);
		camera.position.set(Constants.ViewportW/2, Constants.viewportH/2, 0);
		camera.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		renderer = new ShapeRenderer();
		renderer.setProjectionMatrix(camera.combined);
		
		bobby = new Bobby();
		platform = new Platform();
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