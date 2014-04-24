package com.arrowgames.zk.bobbyjump.screens;

import com.arrowgames.zk.bobbyjump.managers.WorldRenderer;
import com.arrowgames.zk.bobbyjump.managers.WorldController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {
	
	WorldController controller;
	WorldRenderer renderer;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(.4f, 0, .4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		controller.update(delta);
		renderer.update(delta);
		
		renderer.render();
		renderer.renderBound();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
		controller = new WorldController();
		renderer = new WorldRenderer(controller);
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
