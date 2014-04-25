package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.objects.Spring;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

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
		
		controller.cameraHelper.applyTo(camera);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		renderer.setProjectionMatrix(camera.combined);
		
		for (Platform platform : controller.platforms)
			platform.render(batch);
		
		for (Spring spring : controller.springs)
			spring.render(batch);
		
		controller.bobby.render(batch);
		
//		renderBound();
	}
	
	public void renderBackground() {
		
	}
	
	public void renderBound() {
		
		controller.bobby.renderBound(renderer);

		for (Platform platform : controller.platforms)
			platform.renderBound(renderer);

		for (Spring spring : controller.springs)
			spring.renderBound(renderer);
	}
}
