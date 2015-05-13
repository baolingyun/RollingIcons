package com.rollingicons.game;

import com.badlogic.gdx.Game;

public class RollingIconsGame extends Game {
	@Override
	public void create() {
		Assets.load();
		setScreen(new GameScreen());
	}
}
