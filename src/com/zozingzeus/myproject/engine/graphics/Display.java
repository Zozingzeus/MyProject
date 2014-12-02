package com.zozingzeus.myproject.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import com.zozingzeus.myproject.engine.input.Keyboard;
import com.zozingzeus.myproject.engine.input.Mouse;
import com.zozingzeus.myproject.engine.interfaces.RenderBuffer;


public class Display {

	private double scale = 1.0;

	private Window window;
	private Graphics graphics;
	private BufferStrategy bs;

	public Display(Window window) {
		this.window = window;
		createBufferStrategy();
		bs = window.getBufferStrategy();
		graphics = bs.getDrawGraphics();
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
	protected void createBufferStrategy() {
		window.createBufferStrategy(3);
	}

	public void drawImage(BufferedImage image) {
		graphics = bs.getDrawGraphics();
		graphics.drawImage(image, 0, 0, (int) (window.getWidth() * scale), (int) (window.getHeight() * scale), null);
	}

	public void show() {
		graphics.dispose();
		bs.show();
	}

	public Graphics getGraphics() {
		if (graphics == null) {
			System.err.println("Graphics is null.");
		}
		return graphics;
	}

	public void enable(byte device) {
		if ((device & 0x1) == 1) {
			window.addKeyListener(new Keyboard());
		}
		if ((device >> 1 & 0x1) == 1) {
			window.addMouseListener(new Mouse(scale));
			window.addMouseMotionListener(new Mouse(scale));
		}
	}

	public void drawBufferedObjects(List<RenderBuffer> bufferedObjects) {
		for (int i = 0; i < bufferedObjects.size(); i++) {
			bufferedObjects.get(i).render(graphics);
		}
		bufferedObjects.clear();
	}
}
