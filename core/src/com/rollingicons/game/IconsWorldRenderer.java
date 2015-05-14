package com.rollingicons.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class IconsWorldRenderer {

	public IconsWorld iconsWorld;
	public OrthographicCamera camera;

	SpriteBatch batch = new SpriteBatch();
	
	//DEBUG
	ShapeRenderer shapeRender = new ShapeRenderer();
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	
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
		//debugRenderer.render(iconsWorld.physicalWorld, camera.combined);
	}

	private void renderIcons() {
	    shapeRender.begin(ShapeType.Filled);
	    
		for (Icon icon : iconsWorld.icons) {
			
			Vector3 pos = new Vector3();
			pos.x = icon.body.getPosition().x;
			pos.y = icon.body.getPosition().y;
			pos.z = 0;
			
			camera.project(pos);
			shapeRender.setColor(Color.BLUE);
			shapeRender.identity();
			shapeRender.translate(pos.x, pos.y, 0);
			shapeRender.rotate(0, 0, 1, icon.body.getAngle() * MathUtils.radDeg);
			shapeRender.rect(-120 / 2, -120 / 2, 120, 120);		
		}
		
		shapeRender.end();
	}
	
	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.background, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
	}
}
