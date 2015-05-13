package com.rollingicons.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class IconsWorldRenderer {

	public IconsWorld iconsWorld;
	public OrthographicCamera camera;

	SpriteBatch batch = new SpriteBatch();
	
	public void SetIconsWorld(IconsWorld iw) {
		iconsWorld = iw;
	}

	public void SetCamera(OrthographicCamera cm) {
		camera = cm;
	}

	public IconsWorldRenderer() {

	}

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		//renderBackground();
		renderIcons();
		
		// DEBUG
		new Box2DDebugRenderer().render(iconsWorld.physicalWorld, camera.combined);
	}

	private void renderIcons() {

	}
	
	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.background, 0, 0 , 500, 500);
		batch.end();
	}
}
