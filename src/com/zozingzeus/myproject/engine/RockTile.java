package com.zozingzeus.myproject.engine;

import com.zozingzeus.myproject.engine.graphics.Screen;
import com.zozingzeus.myproject.engine.graphics.Sprite;
import com.zozingzeus.myproject.engine.level.tile.Tile;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
		width = 32;
		height = 32;
	}

	public boolean solid() {
		return true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.render(x, y, this);
	}

}
