package com.rollingicons.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.rollingicons.game.Icon.Status;

public class IconsWorldRenderer {

	public IconsWorld iconsWorld;
	public OrthographicCamera camera;

	private SpriteBatch batch = new SpriteBatch();

	// DEBUG
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

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

		renderBackground();
		renderIcons();

		// DEBUG
		// debugRenderer.render(iconsWorld.physicalWorld, camera.combined);
	}

	private void renderIcons() {

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.identity();
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.setProjectionMatrix(camera.combined);

		for (Icon icon : iconsWorld.icons) {

			if (icon.status != Status.FINISHED) {
				Vector3 pos = new Vector3();
				pos.x = icon.body.getPosition().x;
				pos.y = icon.body.getPosition().y;
				pos.z = 0;
				if (icon.status == Status.HIT) {
					shapeRenderer.circle(pos.x, pos.y, 1f, 20);
				}
			}
		}

		shapeRenderer.end();

		batch.enableBlending();
		batch.begin();

		for (Icon icon : iconsWorld.icons) {

			if (icon.status != Status.FINISHED) {
				Vector3 pos = new Vector3();
				pos.x = icon.body.getPosition().x;
				pos.y = icon.body.getPosition().y;
				pos.z = 0;

				Texture texture = icon.texture;
				TextureRegion region = new TextureRegion(texture, 0, 0,
						texture.getWidth(), texture.getHeight());
				batch.draw(region, pos.x - 0.8f, pos.y - 0.8f, 0.8f, 0.8f,
						1.6f, 1.6f, 1, 1, icon.body.getAngle()
								* MathUtils.radDeg);
			}

		}
		batch.end();
	}

	public void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.background, 0, 0, camera.viewportWidth,
				camera.viewportHeight);
		batch.end();
	}
}
