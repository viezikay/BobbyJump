package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.Gdx;
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
		this.renderer = new ShapeRenderer();
		
		this.controller = controller;
				
		reset();
	}
	
	public void reset() {
		
		camera.position.set(Constants.ViewportW/2,  Constants.viewportH/2, 0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);
	}
	
	public void update(float deltaTime) {
		
		if (camera.position.y < controller.bobby.position.y + 2) {
			camera.position.y = controller.bobby.position.y + 2;
			controller.bobby.deadPoint = camera.position.y - 7.5f;
		}
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);
		
		if (camera.position.y > controller.nextY - 9)
			controller.createPlatform();			

		for (Platform platform : controller.platforms)
			if (platform.position.y < camera.position.y - 8)
				platform.recycle();

		
		if (controller.bobby.isDeath() && Gdx.input.justTouched()) {
			controller.rebuild();
			reset();
		}
	}
	
	public void render() {
		
		for (Platform platform : controller.platforms)
			platform.render(batch);
		
		controller.bobby.render(batch);
	}
	
	public void renderBackground() {
		
	}
	
	public void renderBound() {
		
		controller.bobby.renderBound(renderer);

		for (Platform platform : controller.platforms)
			platform.renderBound(renderer);
	}
}
