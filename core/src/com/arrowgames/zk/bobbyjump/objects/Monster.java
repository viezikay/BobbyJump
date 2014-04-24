package com.arrowgames.zk.bobbyjump.objects;

import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Monster extends GameObject {

	@Override
	public void ini() {

		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2(dimension.x/2, dimension.y/2);
		scale = new Vector2(1, 1);
		rotation = 0;
		velocity = new Vector2();
		
		bound = new Rectangle(0, 0, dimension.x, dimension.y);
	}
	
	public void respawn(float x, float y, float vx, float vy) {
		
	}

	@Override
	public void update(float deltaTime) {
		
		stateTime += deltaTime;
		
		if (velocity.x != 0)
			position.x += velocity.x * deltaTime;
		
		if (velocity.y != 0) {
			velocity.y += Constants.Gravity * deltaTime;
			position.y += velocity.y * deltaTime;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		
		textureRegion = animation.getKeyFrame(stateTime);

		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				origin.x, origin.y, dimension.x, dimension.y, 
				scale.x, scale.y, rotation);
		batch.end();
	}

}
