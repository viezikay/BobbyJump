package com.zk.bobbyjump.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
	public static final Assets instance = new Assets();
	
	public AssetSplash splash;
	public AssetsBobby bobby;
	
	private AssetManager assetManager;
	
	public void ini(AssetManager assetManager) {
		this.assetManager = assetManager;
		
		assetManager.load("bobbyjump.pack", TextureAtlas.class);
		assetManager.finishLoading();
		
		TextureAtlas atlas = assetManager.get("bobbyjump.pack", TextureAtlas.class);
		
		splash = new AssetSplash(atlas);
		bobby = new AssetsBobby(atlas);
	}
	
	public class AssetSplash {
		
		public TextureRegion libgdx;
		
		public AssetSplash(TextureAtlas atlas) {
			libgdx = atlas.findRegion("splash");
		}
	}
	
	public class AssetsBobby {
		
		public TextureRegion die;
		public Animation fall;
		public Animation jump;
		
		public AssetsBobby(TextureAtlas atlas) {
			
			Array<AtlasRegion> keyFrames;
			
			die = atlas.findRegion("die");
			
			keyFrames = atlas.findRegions("stand");
			fall = new Animation(0.2f, keyFrames, Animation.LOOP);
			
			keyFrames = atlas.findRegions("jump");
			jump = new Animation(0.2f, keyFrames, Animation.LOOP);
		}
	}

	@Override
	public void dispose() {
		
		assetManager.dispose();
	}
}
