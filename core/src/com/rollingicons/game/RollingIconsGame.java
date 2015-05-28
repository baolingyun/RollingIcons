package com.rollingicons.game;

import com.badlogic.gdx.Game;

public class RollingIconsGame extends Game {
	public MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;

	@Override
	public void create() {
		// Load resources
		Assets.load();
		// Create screens
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		// Set start screen
		setScreen(this.mainMenuScreen);
	}
}
