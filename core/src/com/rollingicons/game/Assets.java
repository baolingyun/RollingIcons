package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	public static void load () {
		background = loadTexture("data/background.jpg");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
	}
}
