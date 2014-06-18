package com.arrowgames.zk.bobbyjump.utils;

import com.arrowgames.zk.bobbyjump.objects.GameObject;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraController {
	
	public Vector2 position;
	GameObject target;
	
	private Vector2 temp;
	private final float FollowVelocity = 1;
	
	public CameraController(GameObject target) {
	
		this.target = target;
		this.position = new Vector2();
		this.temp = new Vector2();
	}
	
	public void reset() {
		
		position.set(Constants.ViewportW/2, Constants.viewportH/2);
		temp.set(0, 0);
	}
	
	public void update(float deltaTime) {
		
		temp.set(Constants.ViewportW/2, target.position.y + 2);
		position.lerp(temp, FollowVelocity * deltaTime);
	}
	
	public void applyTo(OrthographicCamera camera) {
		
		camera.position.x = position.x;
		camera.position.y = position.y;
	}
}
