package com.zozingzeus.myproject.engine.entity;

import com.zozingzeus.myproject.engine.interfaces.Renderable;
import com.zozingzeus.myproject.engine.interfaces.Updatable;
import com.zozingzeus.myproject.engine.level.Level;

public abstract class Entity implements Renderable, Updatable {

	protected int x, y;
	private boolean removed = false;
	protected Level level;

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		removed = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void init(Level level) {
		this.level = level;
	}

}