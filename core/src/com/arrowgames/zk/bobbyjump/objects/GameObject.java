package com.arrowgames.zk.bobbyjump.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 scale;
	public Vector2 origin;
	public float rotation;
	public Vector2 velocity;
	
	float stateTime;
	
	TextureRegion textureRegion;
	Animation animation;
	
	public Rectangle bound;
	Color debugColor;
	
	public GameObject() {
		
		debugColor = new Color(1, 1, 1, 1);
		ini();
		
	}
	
	public abstract void ini();
	public abstract void update(float deltaTime);
	public abstract void render(SpriteBatch batch);
	
	
	public void renderBound(ShapeRenderer renderer) {
		renderer.setColor(debugColor);
		renderer.begin(ShapeType.Line);
		renderer.rect(bound.x, bound.y, bound.width, bound.height);
		renderer.end();
	}
	
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
		this.stateTime = 0;
	}
}