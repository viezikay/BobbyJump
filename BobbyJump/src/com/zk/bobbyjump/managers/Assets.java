package com.zk.bobbyjump.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
	public static final Assets instance = new Assets();
	
	public AssetSplash splash;
	
	private AssetManager assetManager;
	
	public void ini(AssetManager assetManager) {
		this.assetManager = assetManager;
		
		assetManager.load("bobbyjump.pack", TextureAtlas.class);
		assetManager.finishLoading();
		
		TextureAtlas atlas = assetManager.get("bobbyjump.pack", TextureAtlas.class);
		
		splash = new AssetSplash(atlas);
	}
	
	public class AssetSplash {
		
		public TextureRegion libgdx;
		
		public AssetSplash(TextureAtlas atlas) {
			libgdx = atlas.findRegion("splash");
		}
	}

	@Override
	public void dispose() {
		
		assetManager.dispose();
	}
}
