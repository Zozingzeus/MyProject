package com.zozingzeus.myproject.engine;


import com.zozingzeus.myproject.engine.graphics.Color;
import com.zozingzeus.myproject.engine.graphics.Display;
import com.zozingzeus.myproject.engine.graphics.Screen;
import com.zozingzeus.myproject.engine.graphics.Texture;
import com.zozingzeus.myproject.engine.graphics.Window;
import com.zozingzeus.myproject.engine.input.Keyboard;
import com.zozingzeus.myproject.engine.input.Mouse;
import com.zozingzeus.myproject.engine.interfaces.Renderable;

public abstract class Engine implements Runnable {
	
	public Screen screen;
	private Display display;
	private Thread thread;
	private long startTimer = 0L;
	private boolean running;
	
	protected final byte KEYBOARD = Keyboard.CODE;
	protected final byte MOUSE = Mouse.CODE;
	
	
	public void start() {
		running = true;
		thread = new Thread(this, "Main");
		thread.start();
	}
	
	protected final void createDisplay(String name, int width, int height) {
		startTimer = System.currentTimeMillis();
		display = new Display(new Window(name, width, height));
		screen = new Screen(width, height, 1.0);
	}
	
	protected final void createDisplay(String name, int width, int height, double scale) {
		startTimer = System.currentTimeMillis();
		display = new Display(new Window(name, width, height));
		display.setScale(scale);
		screen = new Screen(width, height, scale);
	}
	
	protected final void setInput(int device) {
		display.enable((byte) device);
	}
	
	public final void run() {
		long lastTime = System.nanoTime();
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		System.out.println("Took " + ((System.currentTimeMillis() - startTimer) / 1000.0) + " seconds to load!");
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	protected final int[] getPixels() {
		return screen.getPixels();
	}

	protected final void show() {
		screen.copy();
		display.drawImage(screen.getImage());
		display.drawBufferedObjects(screen.getBufferedObjects());
		display.show();
	}

	public boolean isRunnning() {
		return running;
	}
	
	protected Screen getScreen() {
		return screen;
	}

	protected final void clear() {
		screen.clear();
	}

	protected final void clear(Color col) {
		screen.clear(col);
	}

	protected final void render(int x, int y, Renderable renderable) {
		renderable.render(x, y, screen);
	}

	protected final void renderTexture(Texture texture, int x, int y) {
		screen.renderTexture(texture, x, y);
	}

	protected final void fillRect(int x, int y, int width, int height, Color color) {
		screen.fillRect(x, y, width, height, color);
	}

	protected abstract void init();

	protected abstract void update();

	protected abstract void render();

}
