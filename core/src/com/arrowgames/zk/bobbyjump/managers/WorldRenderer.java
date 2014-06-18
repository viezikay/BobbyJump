package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.objects.Spring;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class WorldRenderer {

	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer renderer;
	
	WorldController controller;
	
	public WorldRenderer(WorldController controller) {
		
		this.camera = new OrthographicCamera(Constants.ViewportW, Constants.viewportH);
		this.batch = new SpriteBatch();
		this.renderer = new ShapeRenderer();
		
		this.controller = controller;
	}
	
	public void render() {
		
		controller.cameraController.applyTo(camera);
		camera.update();
		
//		renderBackground();
		
		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);
		
//		for (Platform platform : controller.platforms)
//			platform.render(batch);
//		
//		for (Spring spring : controller.springs)
//			spring.render(batch);
		
		controller.platform.render(batch);
		controller.bobby.render(batch);
		
		renderBound();
	}
	
	public void renderBackground() {
		batch.begin();
		batch.end();
	}
	
	public void renderBound() {
		
		controller.bobby.renderBound(renderer);
		controller.platform.renderBound(renderer);
//		for (Platform platform : controller.platforms)
//			platform.renderBound(renderer);
//
//		for (Spring spring : controller.springs)
//			spring.renderBound(renderer);
	}
}
