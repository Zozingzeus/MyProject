package com.zozingzeus.myproject;

import java.awt.event.KeyEvent;

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

private Sprite grass, rock;
private Menu menu = new Menu(new MenuOption[] { new MenuOption("Play", new Action() {
	public void action() {
		State.setState(State.GAME);
	}
}), new MenuOption("Options", null), new MenuOption("About", null), new MenuOption("Quit", new Action() {
	public void action() {
		System.exit(0);
	}
}) });

private void levels() {
	test = new TiledLevel("res/levels/level.png");
	test.addTileCode(0xffffff, new GrassTile(grass.getWidth(), grass.getHeight(), grass));
	test.setTileSize(32);
	test.addTileCode(0xff00ff, new RockTile(rock));
	test.add(player);
}

protected void init() {
	grass = new Sprite(Texture.load("res/grass.png"));
	rock = new Sprite(Texture.load("res/rock.png"));
	player = new Player();
	levels();
	createDisplay("Cherno 0.1a", 960, 540, 2.0);
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
	clear(Color.WHITE);
	if (State.getState() == State.GAME) {
		render(0, 0, test);
	} else if (State.getState() == State.MENU) {
		fillRect(0, 0, 960, 540, new Color(0xff00ff));
		screen.render(mx, my, grass);
		menu.render(50, 50, screen);
	}
	show();
}

public static void main(String[] args) {
	Main main = new Main();
	main.init();
}

}