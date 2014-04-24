package com.arrowgames.zk.bobbyjump.managers;

import com.arrowgames.zk.bobbyjump.objects.Platform;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ObjectContainer {

	public static final ObjectContainer instance = new ObjectContainer();
	
	public Array<Platform> platforms;
	
	PoolPlatform poolPlatform;
	
	public void ini() {
		
		platforms = new Array<Platform>();
		
		poolPlatform = new PoolPlatform();
	}
	
	public void createPlatform(Vector2 position) {
		
		Platform platform = poolPlatform.obtain();
		
		platform.rebuild(position, true);
		
		platforms.add(platform);
	}
	
	public void freePlatform(Platform platform) {
		
		platforms.removeValue(platform, true);
		poolPlatform.free(platform);
	}
	
	public class PoolPlatform extends Pool<Platform> {

		@Override
		protected Platform newObject() {
			return new Platform();
		}
	}
}
