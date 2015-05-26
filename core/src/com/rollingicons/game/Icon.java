package com.rollingicons.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Icon extends Actor {
	// Box2D
	public Body body;
	public Fixture fixture;

	// Drawing
	public Texture texture;

	public int image_id = 0;

	public enum Status {
		RUNNING, HIT, FINISHED
	}

	public Status status = Status.RUNNING;

	public boolean HitTest(float x, float y) {
		return fixture.testPoint(x, y);
	}

	public void Hit() {
		if (status == Status.RUNNING) {
			status = Status.HIT;
		}
	}
	
    @Override
    public void draw (Batch batch, float parentAlpha) {

		TextureRegion region = new TextureRegion(texture, 0, 0,
				texture.getWidth(), texture.getHeight());
		
		 batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
		            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
