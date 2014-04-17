package com.zk.bobbyjump.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zk.bobbyjump.managers.Assets;
import com.zk.bobbyjump.utils.Constants;
import com.zk.bobbyjump.utils.Constants.BobbyState;

public class Bobby extends GameObject {
	
	public static final float MaxVelocityX = 9;
	public static final float JumpVelocity = 8;
	
	BobbyState state;
	
	Animation fall;
	Animation jump;
	
	@Override
	public void ini() {
		
		position = new Vector2(Constants.ViewportW/2, 1);
		dimention = new Vector2(1, 1);
		origin = new Vector2(0.5f, 0.5f);
		scale = new Vector2(1, 1);
		rotation = 0;
		velocity = new Vector2(0, -2);
		
		bound = new Rectangle(0, 0, 0.6f, 1);
		
		fall = Assets.instance.bobby.fall;
		jump = Assets.instance.bobby.jump;
		
		move(4);
	}

	@Override
	public void update(float deltaTime) {
		
		stateTime += deltaTime;
		
		if (velocity.x != 0) {
			position.x += velocity.x * deltaTime;
		}
		
		if (velocity.y != 0) {
			
			velocity.y += Constants.Gravity * deltaTime;
			position.y += velocity.y * deltaTime;
			
			if (velocity.y < 0) {
				setState(BobbyState.Dying);
			}
			
		}
		
		if (position.y < 0.5f) {
			position.y = 0.5f;
			jump();
		}
		
		if (position.x < -dimention.x/2 && scale.x < 0)
			position.x = 10 + dimention.x/2;
		else if (position.x > 10 + dimention.x/2 && scale.x > 0)
			position.x = -dimention.x/2;
			
		bound.setPosition(position.x - bound.width/2, position.y - bound.height/2);
	}

	@Override
	public void render(SpriteBatch batch) {
		
		if (animation != null) 
			textureRegion = animation.getKeyFrame(stateTime);
		
		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				origin.x, origin.y, dimention.x, dimention.y, 
				scale.x, scale.y, rotation);
		batch.end();
	}
	
	public void move(float velocityX) {
		
		velocity.x = MathUtils.clamp(velocityX, -MaxVelocityX, MaxVelocityX);
		scale.x = Math.signum(velocityX);
		
	}
	
	public void jump() {
		
		setState(BobbyState.Jumping);
		
		velocity.y = JumpVelocity;
	}

	public void setState(BobbyState state) {
		
		if (this.state == state) return;
		
		this.state = state;
		switch (state) {
		case Dying:
			setAnimation(null);
			textureRegion = Assets.instance.bobby.die;
			break;
		case Jumping:
			setAnimation(jump);
			break;
		case Falling:
			setAnimation(fall);
			break;
		default:
			break;
		}
	}
	
	public boolean isJumping() {
		return state == BobbyState.Jumping;
	}
	
	public boolean isFalling() {
		return state == BobbyState.Falling;
	}
}
