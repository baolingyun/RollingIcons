package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameScreen extends ScreenAdapter {

	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private IconsWorld iconsWorld = new IconsWorld();

	public GameScreen(RollingIconsGame game) {
		iconsWorld.Start();
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		iconsWorld.render(delta);
		debugRenderer.render(iconsWorld.physicalWorld,
				iconsWorld.camera.combined);
	}

	@Override
	public void dispose() {
		iconsWorld.dispose();
	}
}
