package com.arrowgames.zk.bobbyjump.objects;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.arrowgames.zk.bobbyjump.utils.ObjectContainer;
import com.arrowgames.zk.bobbyjump.utils.Constants.PlatformState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Platform extends GameObject {
	
	PlatformState state;
	boolean broken;
	boolean active;

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
		
		broken = false;
		active = false;
		
		setState(PlatformState.Normal);
	}
	
	public void rebuild (float x, float y, boolean broken) {
		rebuild(x, y, 0, 0, broken);
	}
	
	public void rebuild(float x, float y, 
			float velocityX, float velocityY, boolean broken) {

		this.position.set(x, y);
		this.bound.setPosition(position);
		this.velocity.set(velocityX, velocityY);
		
		this.broken = broken;
		this.active = true;
		setState(PlatformState.Normal);
	}

	@Override
	public void update(float deltaTime) {
		if (!active) return;
		
		stateTime += deltaTime;
		
		if (velocity.x != 0) position.x += velocity.x * deltaTime;
		
		if (velocity.y != 0) {
			
			velocity.y += Constants.Gravity * deltaTime;
			position.y += velocity.y * deltaTime;
			
			if (animation.isAnimationFinished(stateTime))
				recycle();
		}
		
		bound.setPosition(position.x - bound.width/2,
					position.y - bound.height/2);
	}

	@Override
	public void render(SpriteBatch batch) {

		if (!active) return;
		
		if (animation != null)
			textureRegion = animation.getKeyFrame(stateTime);
		
		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				origin.x, origin.y, dimension.x, dimension.y, 
				scale.x, scale.y, rotation);
		batch.end();
	}
	
	public void hit() {
		
		if (!broken) return;
		
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
	
	public void recycle() {
		
		velocity.y = 0;
		bound.setPosition(-500, -500);
		active = false;
		
		ObjectContainer.instance.freePlatform(this);
	}
}