package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


class IconStartInputListener extends InputListener {
	public RollingIconsGame game;

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		game.setScreen(game.gameScreen);
		return true;
	}
	
	@Override
	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	}
}

public class MainMenuScreen extends ScreenAdapter {

	private IconsWorld iconsWorld;
	private RollingIconsGame game;

	private Icon icon_exit;
	private Icon icon_settings;
	private Icon icon_start;

	public MainMenuScreen(RollingIconsGame game) {

		this.game = game;

		iconsWorld = new IconsWorld();

		// Create edges around the screen
		iconsWorld.AddEdges(iconsWorld.camera.viewportWidth,
				iconsWorld.camera.viewportHeight);
		
		icon_exit = new Icon();
		icon_exit.setBounds(4f, 5, 2, 4);
		iconsWorld.AddIcon(icon_exit);

		icon_settings = new Icon();
		icon_settings.setBounds(2.5f, 6f, 2, 4);
		iconsWorld.AddIcon(icon_settings);

		icon_start = new Icon();
		icon_start.texture = Assets.button_up;
		icon_start.setBounds(0, 5, 2, 4);
		IconStartInputListener isInputListener = new IconStartInputListener();
		isInputListener.game = game;
		icon_start.addListener(isInputListener);
		iconsWorld.AddIcon(icon_start);
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
	public void resize(int width, int height) {
		// need to resize stage viewport ?
	}

	@Override
	public void show () {
		iconsWorld.Active(true);
	}

	@Override
	public void hide () {
		iconsWorld.Active(false);
	}
	
	@Override
	public void dispose() {
		iconsWorld.dispose();
	}
}
