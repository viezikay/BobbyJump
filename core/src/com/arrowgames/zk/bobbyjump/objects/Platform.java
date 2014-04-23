package com.arrowgames.zk.bobbyjump.objects;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.arrowgames.zk.bobbyjump.utils.Constants.PlatformState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Platform extends GameObject {
	
	PlatformState state;
	boolean canBroken;

	@Override
	public void ini() {
		
		debugColor.set(Color.YELLOW);
		
		position = new Vector2(5, 5);
		dimension = new Vector2(2, .5f);
		origin = new Vector2(dimension.x/2, dimension.y/2);
		scale = new Vector2(1, 1);
		rotation = 0;
		velocity = new Vector2(0, 0);
		
		bound = new Rectangle(0, 0, dimension.x, dimension.y);
		
		canBroken = true;
		
//		setState(PlatformState.Broken);
		hit();
	}

	@Override
	public void update(float deltaTime) {
		
		stateTime += deltaTime;
		
		if (velocity.x != 0) position.x += velocity.x * deltaTime;
		
		if (velocity.y != 0) {
			
			velocity.y += Constants.Gravity * deltaTime;
			position.y += velocity.y * deltaTime;
			
			if (animation.isAnimationFinished(stateTime))
				velocity.y = 0;
		}
		
		bound.setPosition(position.x - bound.width/2,
				position.y - bound.height/2);
	}

	@Override
	public void render(SpriteBatch batch) {

		if (animation != null) {
			
			textureRegion = animation.getKeyFrame(stateTime);
			
			if (animation.isAnimationFinished(stateTime))
				return;
		}
		
		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				origin.x, origin.y, dimension.x, dimension.y, 
				scale.x, scale.y, rotation);
		batch.end();
	}
	
	public void hit() {
		
		if (!canBroken) return;
		
		setState(PlatformState.Broken);
		velocity.y = -.1f;
	}
	
	public void setState(PlatformState state) {
		
		if (this.state == state) return;
		this.state = state;
		switch (state) {
		case Normal:
			setAnimation(null);
			textureRegion = Assets.instance.game.platform.getKeyFrame(0);
			break;
		case Broken:
			setAnimation(Assets.instance.game.platform);
			break;
		default:
			break;		
		}
	}
	
	public void rebuild(Vector2 position, Vector2 velocity,
			boolean canBroken, boolean withSpring) {
		
		this.position.set(position);
		this.bound.setPosition(position);
		this.velocity.set(velocity);
		
		this.canBroken = canBroken;
		
	}
}