package com.zozingzeus.myproject;

import java.awt.event.KeyEvent;

import com.zozingzeus.myproject.engine.BlueBrickTile;
import com.zozingzeus.myproject.engine.Engine;
import com.zozingzeus.myproject.engine.GrassTile;
import com.zozingzeus.myproject.engine.RockTile;
import com.zozingzeus.myproject.engine.State;
import com.zozingzeus.myproject.engine.graphics.Color;
import com.zozingzeus.myproject.engine.graphics.Sprite;
import com.zozingzeus.myproject.engine.graphics.Texture;
import com.zozingzeus.myproject.engine.input.Keyboard;
import com.zozingzeus.myproject.engine.input.Mouse;
import com.zozingzeus.myproject.engine.interfaces.Action;
import com.zozingzeus.myproject.engine.level.TiledLevel;
import com.zozingzeus.myproject.engine.menu.Menu;
import com.zozingzeus.myproject.engine.menu.MenuOption;

public class Main extends Engine {

private int x = 50, y = 50;
private int mx, my;

private TiledLevel test;
private Player player;

private boolean audio = true;

private Sprite grass, rock, bluebrick;
private Menu menu = new Menu(new MenuOption[] { new MenuOption("Play", new Action() {
	public void action() {
		State.setState(State.GAME);
	}
}), new MenuOption("Options", new Action() {
	public void action() {
		State.setState(State.OPTIONS);
	}
}), new MenuOption("About", null), new MenuOption("Quit", new Action() {
	public void action() {
		System.exit(0);
	}
}) });
private Menu options = new Menu(new MenuOption[] { new MenuOption("Audio: " + (audio ? "ON" : "OFF"), new Action() {
	public void action() {
		audio = !audio;
	}
}) });

private void levels() {
	test = new TiledLevel("res/levels/level.png");
	test.addTileCode(0x00ff00, new GrassTile(grass.getWidth(), grass.getHeight(), grass));
	test.setTileSize(32);
	test.addTileCode(0xf0f0f0, new RockTile(rock));
	test.addTileCode(0x0000ff, new BlueBrickTile(bluebrick));
	test.add(player);
}

protected void init() {
	grass = new Sprite(Texture.load("res/grass.png"));
	rock = new Sprite(Texture.load("res/rock.png"));
	bluebrick = new Sprite(Texture.load("res/bluebrick.png"));
	player = new Player();
	levels();
	createDisplay("Unnamed Game Alpha", 960, 540, 1.7);
	setInput(KEYBOARD | MOUSE);
	start();
	State.setState(State.MENU);
}

protected void update() {
	mx = Mouse.getX();
	my = Mouse.getY();
	if (Keyboard.keyPressed(KeyEvent.VK_UP)) y--;
	if (Keyboard.keyPressed(KeyEvent.VK_DOWN)) y++;
	if (Keyboard.keyPressed(KeyEvent.VK_LEFT)) x--;
	if (Keyboard.keyPressed(KeyEvent.VK_RIGHT)) x++;
	if (State.getState() == State.GAME) {
		if (Keyboard.keyTyped(KeyEvent.VK_ESCAPE)) State.setState(State.MENU);
	}
	menu.update();
	test.setOffset(player.getX() - 960 / 8, player.getY() - 540 / 8);
	test.update();
}

protected void render() {
	clear(Color.BLACK);
	if (State.getState() == State.GAME) {
		render(0, 0, test);
	} else if (State.getState() == State.MENU) {
		fillRect(0, 0, 960, 540, new Color(0xff00ff));
		screen.render(mx, my, grass);
		menu.render(50, 50, screen);
	} else if (State.getState() == State.OPTIONS) {
		fillRect(0, 0, 960, 540, new Color(0x0000ff));
		screen.render(mx, my, grass);
		options.render(50, 50, screen);
	}
	show();
}

public static void main(String[] args) {
	Main main = new Main();
	main.init();
}

}