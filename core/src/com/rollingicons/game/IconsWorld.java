package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class IconsWorld implements Disposable {

	// Debug
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	public OrthographicCamera camera;
	public World physicalWorld;

	private ScreenViewport viewport;
	private Stage stage;

	public IconsWorld() {

		// Create the physical world
		physicalWorld = new World(new Vector2(9.8f, 0), true);

		// Set up camera and viewport
		camera = new OrthographicCamera(Gdx.graphics.getWidth()
				* Constants.units_per_pixel, Gdx.graphics.getHeight()
				* Constants.units_per_pixel);
		viewport = new ScreenViewport(camera);
		viewport.setUnitsPerPixel(Constants.units_per_pixel);

		// Create scene2d objects
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		// Create edges around the screen
		AddEdges(camera.viewportWidth - Constants.static_area_width,
				camera.viewportHeight);
	}

	@Override
	public void dispose() {
		stage.dispose();
		physicalWorld.dispose();
	}

	public void render(float delta) {

		// Let the actors move
		stage.act(delta);

		// Draw actors
		stage.draw();

		// Debug only
		debugRenderer.render(physicalWorld, camera.combined);
	}

	public void AddEdges(float width, float height) {
		Edge.Create(physicalWorld, 0, 0.01f, width, 0.01f);
		Edge.Create(physicalWorld, 0, height, width, 0.01f);
		Edge.Create(physicalWorld, 0, height, 0.01f, height);
		Edge.Create(physicalWorld, width - 0.01f, height, 0.01f, height);
	}

	public void AddIcon(Icon icon) {

		// Calculation
		float x = icon.getX();
		float y = icon.getY();
		float width = icon.getWidth();
		float height = icon.getHeight();
		float rotation = icon.getRotation();

		// Create in physicalWorld
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		icon.body = physicalWorld.createBody(bodyDef);
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(width / 2, height / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygon;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 1.0f;
		fixtureDef.restitution = 0.0f;
		icon.body.setTransform(new Vector2(x, y), rotation
				* MathUtils.degreesToRadians);
		icon.fixture = icon.body.createFixture(fixtureDef);
		polygon.dispose();

		// Add to stage
		stage.addActor(icon);
	}

	public void AddBackground(Texture texture) {
		Background background = new Background();
		background.texture = Assets.background;
		background.setBounds(0, 0, camera.viewportWidth
				- Constants.static_area_width, camera.viewportHeight);
		// Add to stage
		stage.addActor(background);
	}

	public void AddGameIcon(Texture texture) {
		Icon icon = new Icon();
		icon.setBounds(5f, 5f, 1.8f, 1.8f);
		icon.texture = texture;
		// Add to stage
		AddIcon(icon);
	}
}
