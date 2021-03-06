package com.arrowgames.zk.bobbyjump.utils;

public interface Constants {
	
	public static final float ViewportW = 10;
	public static final float viewportH = 15;
	
	public static final float Gravity = -10;
	public static final float Friction = .2f;
	
	public enum BobbyState {
		
		Falling,
		Jumping,
		Dying
	}
	
	public enum PlatformState {
		
		Normal,
		Broken
	}
}
