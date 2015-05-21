package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


class ButtonPlayClicked extends ClickListener {
	public ButtonPlayClicked(RollingIconsGame game) {
		this.game = game;
	}
	private RollingIconsGame game;
	public void clicked (InputEvent event, float x, float y) {
	    game.setScreen(game.gameScreen);
	}
}


public class MainMenuScreen extends ScreenAdapter  {

	private RollingIconsGame game;
	
	private Viewport viewport;
	private Table table;
	private Stage stage;
	private TextButton btnPlay;
	private TextButton btnSettings;
	private TextButton btnExit;
	
	public MainMenuScreen(RollingIconsGame game) {
		
		this.game = game;
		
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		
		// Define buttons
		TextButton.TextButtonStyle button_stype = new TextButton.TextButtonStyle();
		button_stype.font = new BitmapFont();
		button_stype.up = new TextureRegionDrawable(new TextureRegion(Assets.button_up));
		button_stype.over = new TextureRegionDrawable(new TextureRegion(Assets.button_over));
		button_stype.down = new TextureRegionDrawable(new TextureRegion(Assets.button_down));
		
		btnPlay = new TextButton("PLAY", button_stype);
		btnSettings = new TextButton("SETTINGS", button_stype);
		btnExit = new TextButton("EXIT", button_stype);
		
		// Create table
		table = new Table();
		table.debug(); // Enables debug
		
		table.row();
		table.add(btnPlay).padTop(10f).colspan(2);
		table.row();
		table.add(btnSettings).padTop(10f).colspan(2);
		table.row();
		table.add(btnExit).padTop(10f).colspan(2);
		
		// Pack table
		table.setFillParent(true);
		table.pack();
		
		// Set table's alpha to 0
		table.getColor().a = 0f;

		// Adds created table to stage
		stage.addActor(table);

		// To make the table appear smoothly
		table.addAction(Actions.fadeIn(2f));
		
		ButtonPlayClicked listener = new ButtonPlayClicked(this.game);
		// Play button listener
		btnPlay.addListener(listener);
		
    }
	
	@Override
	public void render(float delta) {
		
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
		stage.draw();
	}
}
