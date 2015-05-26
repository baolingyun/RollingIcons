package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class MainMenuScreen extends ScreenAdapter {

	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private IconsWorld iconsWorld;
    
	public MainMenuScreen(RollingIconsGame game) {
		iconsWorld = new IconsWorld();
		Icon icon = new Icon();
		icon.setBounds(5f, 5f, 1.8f, 1.8f);
		icon.texture = Assets.icons.get(0);
		icon.setRotation(30);
		iconsWorld.AddIcon(icon);
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		iconsWorld.render(delta);
		debugRenderer.render(iconsWorld.physicalWorld, iconsWorld.camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		// need to resize stage viewport ?
	}

	@Override
	public void dispose() {
		iconsWorld.dispose();
	}
}
