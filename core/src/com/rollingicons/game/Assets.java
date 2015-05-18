package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static List<Texture> icons = new ArrayList<Texture>();
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	public static void load () {
		background = loadTexture("data/background.jpg");
		
		icons.add(loadTexture("data/icons/1.png"));
		icons.add(loadTexture("data/icons/2.png"));
		icons.add(loadTexture("data/icons/3.png"));
		icons.add(loadTexture("data/icons/4.png"));
		icons.add(loadTexture("data/icons/5.png"));
		icons.add(loadTexture("data/icons/6.png"));
		icons.add(loadTexture("data/icons/7.png"));
		icons.add(loadTexture("data/icons/8.png"));
		icons.add(loadTexture("data/icons/9.png"));
		icons.add(loadTexture("data/icons/10.png"));
		icons.add(loadTexture("data/icons/11.png"));
		icons.add(loadTexture("data/icons/12.png"));
		icons.add(loadTexture("data/icons/13.png"));
		icons.add(loadTexture("data/icons/14.png"));
		icons.add(loadTexture("data/icons/15.png"));
		icons.add(loadTexture("data/icons/16.png"));
		icons.add(loadTexture("data/icons/17.png"));
		icons.add(loadTexture("data/icons/18.png"));
		icons.add(loadTexture("data/icons/19.png"));
		icons.add(loadTexture("data/icons/20.png"));
		icons.add(loadTexture("data/icons/21.png"));
		icons.add(loadTexture("data/icons/22.png"));
		icons.add(loadTexture("data/icons/23.png"));
		icons.add(loadTexture("data/icons/24.png"));
		icons.add(loadTexture("data/icons/25.png"));
		icons.add(loadTexture("data/icons/26.png"));
		icons.add(loadTexture("data/icons/27.png"));
		icons.add(loadTexture("data/icons/28.png"));
		icons.add(loadTexture("data/icons/29.png"));
		icons.add(loadTexture("data/icons/30.png"));
	}
}
