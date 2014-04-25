package com.arrowgames.zk.bobbyjump.objects;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.arrowgames.zk.bobbyjump.utils.ObjectContainer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Spring extends GameObject {

	boolean active;
	
	@Override
	public void ini() {

		debugColor = Color.GREEN;
		active = false;
		
		position = new Vector2();
		dimension = new Vector2(.65f, .3f);
		origin = new Vector2(dimension.x/2, 0);
		bound = new Rectangle(0, 0, dimension.x, dimension.y);
		
		textureRegion = Assets.instance.game.spring;
	}
	
	public void rebuild(float x, float y) {
		
		active = true;
		
		position.set(x, y);
		dimension.set(.65f, .3f);
		
		bound.setPosition(position.x - bound.width/2, position.y - bound.height/2);
	}

	@Override
	public void update(float deltaTime) {
		
		if (!active) return;
	}

	@Override
	public void render(SpriteBatch batch) {
		
		if (!active) return;
		
		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				dimension.x, dimension.y);
		batch.end();
	}
	
	public void recycle() {

		bound.setPosition(-500, -500);
		active = false;
		
		ObjectContainer.instance.freeSpring(this);
	}

	public void hit() {
		dimension.y = .6f;
	}
}
