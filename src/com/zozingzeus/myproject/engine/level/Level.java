package com.zozingzeus.myproject.engine.level;

import java.util.ArrayList;
import java.util.List;

import com.zozingzeus.myproject.engine.entity.Entity;
import com.zozingzeus.myproject.engine.graphics.Screen;
import com.zozingzeus.myproject.engine.interfaces.Renderable;
import com.zozingzeus.myproject.engine.interfaces.Updatable;


public abstract class Level implements Renderable, Updatable {

	protected int width, height;
	protected int xOffset, yOffset;

	protected List<Entity> entities = new ArrayList<Entity>();

	protected Level(int width, int height) {
		this.width = width;
		this.height = height;
	}

	protected Level(String fileName) {
		load(fileName);
	}

	public abstract void load(String fileName);

	public abstract void generate();

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void add(Entity e) {
		e.init(this);
		entities.add(e);
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void render(Screen screen) {
		renderEntities(screen);
	}

	public void update() {
		updateEntities();
	}

	private void updateEntities() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}

	private void renderEntities(Screen screen) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(0, 0, screen);
		}
	}
}