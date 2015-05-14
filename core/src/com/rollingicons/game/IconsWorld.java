package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class IconsWorld {
	public List<Icon> icons = new ArrayList<Icon>();

	public Edge ledge = new Edge();
	public Edge redge = new Edge();
	public Edge tedge = new Edge();
	public Edge bedge = new Edge();

	public World physicalWorld = new World(new Vector2(0, 0), true);

	public void CreateIcon() {
		Icon icon = new Icon();
		icon.Create(physicalWorld);
		icon.body.applyForceToCenter(-400f, 400f, true);
		icons.add(icon);
	}

	void CreateEdge(OrthographicCamera camera) {
		bedge.Create(physicalWorld, 0, 0.01f, camera.viewportWidth,
				0.01f);
		tedge.Create(physicalWorld, 0, camera.viewportHeight,
				camera.viewportWidth, 0.01f);
		ledge.Create(physicalWorld, 0, camera.viewportHeight, 0.01f,
				camera.viewportHeight);
		redge.Create(physicalWorld, camera.viewportWidth - 0.01f,
				camera.viewportHeight, 0.01f, camera.viewportHeight);
	}
}
