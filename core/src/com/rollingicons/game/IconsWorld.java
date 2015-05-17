package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rollingicons.game.Icon.Status;

public class IconsWorld {

	private int level = 1;

	public List<Icon> icons = new ArrayList<Icon>();

	public Edge ledge = new Edge();
	public Edge redge = new Edge();
	public Edge tedge = new Edge();
	public Edge bedge = new Edge();

	public World physicalWorld = new World(new Vector2(0, 0), true);

	public void CreateIcon(int image_id, Texture texture) {
		Icon icon = new Icon();
		icon.image_id = image_id;
		icon.texture = texture;
		icon.Create(physicalWorld);
		icon.body.applyForceToCenter(-400f, 400f, true);
		icons.add(icon);
	}

	public void Start() {
		icons.clear();
		for (int i = 0; i < level; ++i) {
			CreateIcon(i, Assets.icons.get(i));
			CreateIcon(i, Assets.icons.get(i));
		}
	}

	public void CreateEdge(OrthographicCamera camera) {
		bedge.Create(physicalWorld, 0, 0.01f, camera.viewportWidth, 0.01f);
		tedge.Create(physicalWorld, 0, camera.viewportHeight,
				camera.viewportWidth, 0.01f);
		ledge.Create(physicalWorld, 0, camera.viewportHeight, 0.01f,
				camera.viewportHeight);
		redge.Create(physicalWorld, camera.viewportWidth - 0.01f,
				camera.viewportHeight, 0.01f, camera.viewportHeight);
	}

	public void CheckAndLevelUp() {
		boolean goLevelUp = true;
		for (Icon one_icon : icons) {
			if (one_icon.status != Status.FINISHED) {
				goLevelUp = false;
			}
		}
		if (goLevelUp) {
			level++;
			Start();
		}
	}

	public void UpdateStatus(Icon icon) {
		boolean all_hit = true;
		for (Icon one_icon : icons) {
			// check if hit wrong icon
			if (one_icon.status == Status.HIT
					&& one_icon.image_id != icon.image_id) {
				UpdateAllStatusRunning();
				return;
			}
			// check if all icons with same image hit
			if (one_icon.image_id == icon.image_id
					&& one_icon.status != Status.HIT) {
				all_hit = false;
			}
		}

		if (all_hit) {
			FinishIconsWithSameImage(icon);
		}
	}

	public void UpdateAllStatusRunning() {
		for (Icon icon : icons) {
			if (icon.status == Status.HIT) {
				icon.status = Status.RUNNING;
			}
		}
	}

	public void FinishIconsWithSameImage(Icon icon) {
		for (Icon one_icon : icons) {
			if (one_icon.image_id == icon.image_id) {
				one_icon.status = Status.FINISHED;
				physicalWorld.destroyBody(one_icon.body);
			}
		}
	}
}
