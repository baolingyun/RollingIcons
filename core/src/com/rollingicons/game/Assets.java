package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static List<Texture> icons = new ArrayList<Texture>();
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	public static void load () {
		background = loadTexture("data/background.jpg");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		
		icons.add(loadTexture("data/icons/1.png"));
		icons.add(loadTexture("data/icons/2.png"));
		icons.add(loadTexture("data/icons/3.png"));
		icons.add(loadTexture("data/icons/4.png"));
		icons.add(loadTexture("data/icons/5.png"));
		icons.add(loadTexture("data/icons/6.png"));
	}
}
