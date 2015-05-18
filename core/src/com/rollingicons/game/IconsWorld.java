package com.rollingicons.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.rollingicons.game.Icon.Status;

public class IconsWorld implements Disposable {

	private int level = 4;

	public List<Icon> icons = new ArrayList<Icon>();

	public Edge ledge = new Edge();
	public Edge redge = new Edge();
	public Edge tedge = new Edge();
	public Edge bedge = new Edge();

	// Control the two areas of the game screen
	public float status_area_width = 0;
	public float status_area_height = 0;
	public float game_area_width = 0;
	public float game_area_height = 0;

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

	public void CreateGameArea(float width, float height) {
		game_area_width = width;
		game_area_height = height;
		bedge.Create(physicalWorld, 0, 0.01f, width, 0.01f);
		tedge.Create(physicalWorld, 0, height, width, 0.01f);
		ledge.Create(physicalWorld, 0, height, 0.01f, height);
		redge.Create(physicalWorld, width - 0.01f, height, 0.01f, height);
	}
	
	public void CreateStatusArea(float width, float height) {
		status_area_width = width;
		status_area_height = height;
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

	@Override
	public void dispose() {
		physicalWorld.dispose();
	}
}
