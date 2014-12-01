package com.zozingzeus.myproject.engine.graphics;

import com.zozingzeus.myproject.engine.interfaces.Renderable;

public class Sprite implements Renderable {

	private int width, height;
	private int[] pixels;

	public Sprite(Texture texture) {
		create(texture);
	}

	public Sprite(String fileName) {
		create(Texture.load(fileName));
	}

	private void create(Texture texture) {
		width = texture.getWidth();
		height = texture.getHeight();
		pixels = texture.getPixels(Texture.FORMAT_RGB);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void render(int x, int y, Screen screen) {
		screen.render(x, y, this);
	}

}