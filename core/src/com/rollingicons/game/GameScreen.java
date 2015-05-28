package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {

	private IconsWorld iconsWorld = new IconsWorld();

	public GameScreen(RollingIconsGame game) {

		iconsWorld.AddBackground(Assets.background);

		for (int i = 0; i < 10; ++i) {
			iconsWorld.AddGameIcon(Assets.icons.get(i));
			iconsWorld.AddGameIcon(Assets.icons.get(i));
		}
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		iconsWorld.physicalWorld.step(1 / 60f, 6, 2);
		iconsWorld.render(delta);
	}

	@Override
	public void dispose() {
		iconsWorld.dispose();
	}
}
