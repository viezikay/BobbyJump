package com.arrowgames.zk.bobbyjump.objects;

import com.arrowgames.zk.bobbyjump.managers.Assets;
import com.arrowgames.zk.bobbyjump.utils.Constants;
import com.arrowgames.zk.bobbyjump.utils.Constants.BobbyState;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bobby extends GameObject {
	
	public static final float MaxVelocityX = 6;
	public static final float JumpVelocity = 10;
	
	public float deadPoint;
	
	BobbyState state;
	
	Animation fall;
	Animation jump;
	
	boolean die;
	
	@Override
	public void ini() {
		
		position = new Vector2();
		dimension = new Vector2(1.0f, 1.0f);
		origin = new Vector2(dimension.x/2, dimension.y/2);
		scale = new Vector2(1, 1);
		rotation = 0;
		velocity = new Vector2(0, -2);
		
		bound = new Rectangle(0, 0, dimension.x*0.6f, dimension.y);
		
		fall = Assets.instance.bobby.fall;
		jump = Assets.instance.bobby.jump;
	}
	
	public void respawn() {
		
		die = false;
		deadPoint = 0;
		
		position.set(Constants.ViewportW/2, 2);
		velocity.set(0, -2);
	}

	@Override
	public void update(float deltaTime) {
		
		if (die) return;
		
		stateTime += deltaTime;
		
		if (velocity.x != 0) {
			
			velocity.x -= Math.signum(velocity.x)*Constants.Friction;
			position.x += velocity.x * deltaTime;
		}
		
		if (velocity.y != 0) {
			
			velocity.y += Constants.Gravity * deltaTime;
			position.y += velocity.y * deltaTime;
			
			if (velocity.y < 0) {
				setState(BobbyState.Falling);
			}
			
		}
		
		if (position.y < 0.5f) {
			position.y = 0.5f;
			jump();
		}
		
		if (position.y < deadPoint)
			die = true;
		
		if (position.x < -dimension.x/2 && scale.x < 0)
			position.x = 10 + dimension.x/2;
		else if (position.x > 10 + dimension.x/2 && scale.x > 0)
			position.x = -dimension.x/2;
			
		bound.setPosition(position.x - bound.width/2, position.y - bound.height/2);
	}

	@Override
	public void render(SpriteBatch batch) {
		
		if (animation != null) 
			textureRegion = animation.getKeyFrame(stateTime);
		
		batch.begin();
		batch.draw(textureRegion, position.x - origin.x, position.y - origin.y, 
				origin.x, origin.y, dimension.x, dimension.y, 
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
	
	public void superJump() {
		
		setState(BobbyState.Jumping);
		
		velocity.y = JumpVelocity*1.5f;
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
	
	public boolean isDeath() {
		return die;
	}
}
