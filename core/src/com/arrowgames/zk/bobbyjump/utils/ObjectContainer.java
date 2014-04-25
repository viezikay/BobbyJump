package com.arrowgames.zk.bobbyjump.utils;

import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.arrowgames.zk.bobbyjump.objects.Spring;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ObjectContainer {

	public static final ObjectContainer instance = new ObjectContainer();
	
	public Array<Platform> platforms;
	public Array<Spring> springs;
	
	PoolPlatform poolPlatform;
	PoolSpring poolSpring;
	
	public void ini() {
		
		platforms = new Array<Platform>();
		springs = new Array<Spring>();
		
		poolPlatform = new PoolPlatform();
		poolSpring = new PoolSpring();
	}
	
	public void createPlatform(float x, float y) {
		
		Platform platform = poolPlatform.obtain();
		
		platform.rebuild(x, y, true);
		
		platforms.add(platform);
		
		boolean haveSpring = MathUtils.random(0, 99) < 20;
		if (haveSpring) {
			
			Spring spring = poolSpring.obtain();
			spring.rebuild(x, y+.25f);
			springs.add(spring);
			
			Gdx.app.log("SPRING POOL", "FREE OBJECT: " + poolSpring.getFree());
		}
	}
	
	public void freePlatform(Platform platform) {
		
		platforms.removeValue(platform, true);
		poolPlatform.free(platform);
	}
	
	public void freeSpring(Spring spring) {
		
		springs.removeValue(spring, true);
		poolSpring.free(spring);
	}
	
	public class PoolPlatform extends Pool<Platform> {

		@Override
		protected Platform newObject() {
			return new Platform();
		}
	}
	
	public class PoolSpring extends Pool<Spring> {

		@Override
		protected Spring newObject() {
			return new Spring();
		}
	}
}
