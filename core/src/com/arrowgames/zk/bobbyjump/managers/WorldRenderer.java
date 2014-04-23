package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class WorldRenderer {

	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer renderer;
	
	WorldController controller;
	
	public WorldRenderer(SpriteBatch batch, WorldController controller) {
		
		this.camera = new OrthographicCamera(Constants.ViewportW, Constants.viewportH);
		this.batch = batch;
		this.controller = controller;
		
	}
	
	public void reset() {
		
		camera.position.set(Constants.ViewportW/2,  Constants.viewportH/2, 0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void render() {
		
	}
	
	public void renderBackground() {
		
	}
	
	public void renderBound() {
		
	}
}
