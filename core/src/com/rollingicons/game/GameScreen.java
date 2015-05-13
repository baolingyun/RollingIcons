package com.rollingicons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends ScreenAdapter {
	private IconsWorld iconsWorld = new IconsWorld();
	private OrthographicCamera camera = new OrthographicCamera(
			Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	private IconsWorldRenderer iconsWorldRenderer = new IconsWorldRenderer();

	public GameScreen() {

		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.update();

		iconsWorldRenderer.SetIconsWorld(iconsWorld);
		iconsWorldRenderer.SetCamera(camera);

		iconsWorld.CreateEdge(camera);
		
		iconsWorld.CreateIcon();
		//iconsWorld.CreateIcon();
		// iconsWorld.CreateIcon();
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(
				GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		if (Gdx.input.justTouched()) {
			Vector3 touchPoint = new Vector3();
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			Array<Fixture> fixtures = new Array<Fixture>();
			iconsWorld.physicalWorld.getFixtures(fixtures);

			for (Fixture fixture : fixtures) {
				if (fixture.testPoint(touchPoint.x, touchPoint.y)) {
					Body hitBody = fixture.getBody();
					hitBody.setLinearVelocity(0, 0);
					hitBody.setAngularVelocity(0);
				}
			}
		}

		iconsWorldRenderer.render();
		iconsWorld.physicalWorld.step(1 / 60f, 6, 2);
	}
}
