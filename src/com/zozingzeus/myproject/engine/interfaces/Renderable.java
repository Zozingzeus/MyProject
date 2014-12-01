package com.zozingzeus.myproject.engine.interfaces;

import com.zozingzeus.myproject.engine.graphics.Screen;

public interface Renderable {

	public int getWidth();

	public int getHeight();

	public int[] getPixels();

	public void render(int x, int y, Screen screen);

}
