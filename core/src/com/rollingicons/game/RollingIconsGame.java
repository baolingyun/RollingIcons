package com.rollingicons.game;

import com.badlogic.gdx.Game;

public class RollingIconsGame extends Game {
	public MainMenuScreen mainMenuScreen;
    public GameScreen gameScreen;
	@Override
	public void create() {
		
		Assets.load();
		
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		
		setScreen(this.mainMenuScreen);
	}
}
