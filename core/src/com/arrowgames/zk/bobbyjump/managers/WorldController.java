package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.objects.Bobby;
import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.utils.CameraHelper;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.arrowgames.zk.bobbyjump.utils.ObjectContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {

	CameraHelper cameraHelper;
	
	Bobby bobby;
	Array<Platform> platforms;
	
	float nextY;
	
	private ApplicationType applicationType = Gdx.app.getType();
	
	public WorldController() {
		
		ObjectContainer.instance.ini();
		
		cameraHelper = new CameraHelper(bobby);
		
		bobby = new Bobby();
		platforms = ObjectContainer.instance.platforms;
		nextY = MathUtils.random(1f, 2f);
		
		rebuild();
	}
	
	public void rebuild() {
		
		cameraHelper.reset();
		
		nextY = 0;
		bobby.respawn();
		
		if (platforms.size > 0) platforms.clear();
		
		while (nextY < Constants.viewportH+2)
			createPlatform();
	}
	
	public void createPlatform() {
		
		float x = MathUtils.random(0, Constants.ViewportW);
		float y = nextY;

		ObjectContainer.instance.createPlatform(new Vector2(x, y));

		nextY += MathUtils.random(1f, 2f);
	}
	
	public void update(float deltaTime) {
		
		inputHandle();
		
		updateObject(deltaTime);
		
		collisionChecking();
	}
	
	public void inputHandle() {
		
		if (applicationType == ApplicationType.Android 
				|| applicationType == ApplicationType.iOS) {
			
			bobby.move(Gdx.input.getAccelerometerX());
		}
		else if (applicationType == ApplicationType.Desktop) {
			
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				bobby.move(6);
			else if (Gdx.input.isKeyPressed(Keys.LEFT))
				bobby.move(-6);
			
		}
		
		if (bobby.isDeath() && Gdx.input.justTouched())
			rebuild();
	}
	
	public void updateObject(float deltaTime) {
		
		bobby.update(deltaTime);
		
		if (cameraHelper.position.y < bobby.position.y + 2) {
			cameraHelper.position.y = bobby.position.y + 2;
			bobby.deadPoint = cameraHelper.position.y - 8.5f;
		}
		
		for (Platform platform : platforms)
			platform.update(deltaTime);

		if (cameraHelper.position.y > nextY - 9)
			createPlatform();

		for (Platform platform : platforms) {
			if (platform.position.y < cameraHelper.position.y - 8)
				platform.recycle();
		}
	}
	
	public void collisionChecking() {

		for (Platform platform : platforms)
			if (bobby.isFalling()) {
				if (bobby.bound.overlaps(platform.bound)) 
					if (bobby.position.y > platform.position.y + bobby.bound.height/2) {
					bobby.jump();
					platform.hit();
				}
			}
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		return false;
	}
}
