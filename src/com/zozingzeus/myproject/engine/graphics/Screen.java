package com.zozingzeus.myproject.engine.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.LinkedList;
import java.util.List;

import com.zozingzeus.myproject.engine.graphics.Color;
import com.zozingzeus.myproject.engine.interfaces.RenderBuffer;
import com.zozingzeus.myproject.engine.interfaces.Renderable;

public class Screen {

	private int width, height;
	private double scale = 1.0;
	private int[] pixels, imagePixels;

	private BufferedImage image;
	private List<RenderBuffer> buffer = new LinkedList<RenderBuffer>();
	private Font font = new Font("Helvetica", 0, 20);

	public Screen(int width, int height, double scale) {
		this.width = (int) (width / scale);
		this.height = (int) (height / scale);
		this.scale = scale;
		pixels = new int[this.width * this.height];
		image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void clear(Color color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color.getColor();
		}
	}

	public void clear() {
		clear(Color.BLACK);
	}

	public int getWidth() {
		return width;
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void copy() {
		for (int i = 0; i < width * height; i++) {
			imagePixels[i] = pixels[i];
		}
	}

	public void fillRect(int x, int y, int width, int height, Color color) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				if (xx < 0 || xx >= this.width || yy < 0 || yy >= this.height) continue;
				pixels[xx + yy * this.width] = color.getColor();
			}
		}
	}

	public void drawString(final String text, final int x, final int y, final Color color) {
		drawString(text, x, y, font, color);
	}

	public void drawString(final String text, final int x, final int y, final Font font, final Color color) {
		buffer.add(new RenderBuffer() {
			public void render(Graphics graphics) {
				int[] rgb = color.getRGB();
				graphics.setFont(font);
				graphics.setColor(new java.awt.Color(rgb[0], rgb[1], rgb[2]));
				graphics.drawString(text, x, y);
			}
		});
	}

	public void renderTexture(Texture texture, int x, int y) {
		if (texture == null) {
			System.err.println("Texture is null!");
			return;
		}
		for (int yy = 0; yy < texture.getHeight(); yy++) {
			int yp = yy + y;
			for (int xx = 0; xx < texture.getWidth(); xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= this.width || yp < 0 || yp >= this.height) continue;
				pixels[xp + yp * this.width] = texture.getPixels(Texture.FORMAT_RGB)[xx + yy * texture.getWidth()];
			}
		}
	}

	public String toString() {
		return "Screen class";
	}

	public void render(int x, int y, Renderable renderable) {
		if (renderable == null) {
			System.err.println("Renderable is null.");
			return;
		}
		for (int yy = 0; yy < renderable.getHeight(); yy++) {
			int yp = yy + y;
			for (int xx = 0; xx < renderable.getWidth(); xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width || yp < 0 || yp >= height) continue;
				pixels[xp + yp * width] = renderable.getPixels()[xx + yy * renderable.getWidth()];
			}
		}
	}

	public List<RenderBuffer> getBufferedObjects() {
		return buffer;
	}

	public double getScale() {
		return scale;
	}

}