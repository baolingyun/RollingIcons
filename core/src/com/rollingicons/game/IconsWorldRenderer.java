package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class IconsWorldRenderer {
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	public IconsWorld iconsWorld;
	public OrthographicCamera camera;
	
	public void SetIconsWorld(IconsWorld iw) {
		iconsWorld = iw;
	}

	public IconsWorldRenderer() {
		camera = new OrthographicCamera(C.PPM(Gdx.graphics.getWidth()),
				C.PPM(Gdx.graphics.getHeight()));
		camera.setToOrtho(false, C.PPM(Gdx.graphics.getWidth()),
				C.PPM(Gdx.graphics.getHeight()));
		camera.update();
	}

	public void render() {
		renderIcons();
		debugRenderer.render(iconsWorld.physicalWorld, camera.combined);
	}

	private void renderIcons() {

	}
}
