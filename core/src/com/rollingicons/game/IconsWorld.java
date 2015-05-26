package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class IconsWorld implements Disposable {

	private int level = 4;

	private List<Icon> icons = new ArrayList<Icon>();
	private Edge ledge = new Edge();
	private Edge redge = new Edge();
	private Edge tedge = new Edge();
	private Edge bedge = new Edge();
	
	private Stage stage;
	public OrthographicCamera camera;
	private ScreenViewport viewport;
	
	public World physicalWorld;
	
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
		CreateGameArea(camera.viewportWidth, camera.viewportHeight);
	}

	public void CreateGameIcon(int image_id, Texture texture) {
		Icon icon = new Icon();
		icon.setBounds(5f, 5f, 1.8f, 1.8f);
		icon.image_id = image_id;
		icon.texture = texture;
		AddIcon(icon);
	}

	@Override
	public void dispose() {
		physicalWorld.dispose();
		stage.dispose();
	}
	
	public void Start() {
		icons.clear();
		for (int i = 0; i < level; ++i) {
			CreateGameIcon(i, Assets.icons.get(i));
			CreateGameIcon(i, Assets.icons.get(i));
		}
	}

	public void render(float delta) {
		// Do physical actions and update 
		physicalWorld.step(1 / 60f, 6, 2);
		UpdateIcons();
		
		// Draw
		stage.act(delta);
		stage.draw();
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
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.8f;
		icon.body.setTransform(new Vector2(x, y), rotation
				* MathUtils.degreesToRadians);
		icon.fixture = icon.body.createFixture(fixtureDef);
		polygon.dispose();

		// Add to stage
		stage.addActor(icon);

		// Add to my own structure
		icons.add(icon);
	}

	private void UpdateIcons() {
		for (Icon icon : icons) {
			Vector2 position = icon.body.getPosition();
			// Center body is center sprite here
			float hw = icon.getWidth() / 2.0f;
			float hh = icon.getHeight() / 2.0f;
			float a = icon.body.getAngle() * MathUtils.radiansToDegrees;
			float x = position.x - hw;
			float y = position.y - hh;

			icon.setPosition(x, y);
			icon.setOrigin(hw, hh);
			icon.setRotation(a);
		}
	}
	
	private void CreateGameArea(float width, float height) {
		bedge.Create(physicalWorld, 0, 0.01f, width, 0.01f);
		tedge.Create(physicalWorld, 0, height, width, 0.01f);
		ledge.Create(physicalWorld, 0, height, 0.01f, height);
		redge.Create(physicalWorld, width - 0.01f, height, 0.01f, height);
	}
}
