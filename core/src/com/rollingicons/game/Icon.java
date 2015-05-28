package com.rollingicons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Icon extends Actor {
	// Box2D
	public Body body;
	public Fixture fixture;

	// Drawing
	public Texture texture;

	@Override
	public void draw(Batch batch, float parentAlpha) {

		if (texture != null) {
			TextureRegion region = new TextureRegion(texture, 0, 0,
					texture.getWidth(), texture.getHeight());

			batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		}
	}

	@Override
	public void act(float delta) {

		Vector2 position = body.getPosition();
		// Center body is center sprite here
		float hw = getWidth() / 2.0f;
		float hh = getHeight() / 2.0f;
		float a = body.getAngle() * MathUtils.radiansToDegrees;
		float x = position.x - hw;
		float y = position.y - hh;

		setPosition(x, y);
		setOrigin(hw, hh);
		setRotation(a);

		super.act(delta);
	}
}
