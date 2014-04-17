package com.zk.bobbyjump.utils;

public interface Constants {
	
	public static final float Gravity = -10;
	
	public static final float ViewportW = 10;
	public static final float viewportH = 15;
	
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
