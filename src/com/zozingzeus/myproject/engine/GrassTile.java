package com.zozingzeus.myproject.engine;

import com.zozingzeus.myproject.engine.graphics.Screen;
import com.zozingzeus.myproject.engine.graphics.Sprite;
import com.zozingzeus.myproject.engine.level.tile.Tile;

public class GrassTile extends Tile {

	public GrassTile(int width, int height, Sprite sprite) {
		super(sprite);
		this.width = width;
		this.height = height;
		
	}

	public void render(int x, int y, Screen screen) {
		screen.render(x, y, this);
	}

}
