package com.zozingzeus.myproject;

import java.awt.event.KeyEvent;

import com.zozingzeus.myproject.engine.entity.mob.Mob;
import com.zozingzeus.myproject.engine.graphics.Screen;
import com.zozingzeus.myproject.engine.graphics.Sprite;
import com.zozingzeus.myproject.engine.graphics.Texture;
import com.zozingzeus.myproject.engine.input.Keyboard;

public class Player extends Mob {

	private Texture player_front = Texture.spritesheet(0, 0, "res/player.png");
	private Texture player_back = Texture.spritesheet(1, 0, "res/player.png");
	private Texture player_right = Texture.spritesheet(2, 0,"res/player.png");
	private Texture player_left = Texture.spritesheet(3, 0, "res/player.png");
	
	private Sprite sprite;
	private Sprite sprite_front = new Sprite(player_front);
	private Sprite sprite_back = new Sprite(player_back);
	private Sprite sprite_right = new Sprite(player_right);
	private Sprite sprite_left = new Sprite(player_left);

	public Player() {
		sprite = new Sprite(player_front);
	}

	public int getWidth() {
		return 32;
	}

	public int getHeight() {
		return 32;
	}

	public int[] getPixels() {
		return sprite.getPixels();
	}

	public void update() {
		sprite = sprite_front;
		int xa = 0, ya = 0;
		if (Keyboard.keyPressed(KeyEvent.VK_UP)) {
			ya--;
		}
		if (Keyboard.keyPressed(KeyEvent.VK_DOWN)) {
			ya++;
		}
		if (Keyboard.keyPressed(KeyEvent.VK_LEFT)) {
			xa--;
		}
		if (Keyboard.keyPressed(KeyEvent.VK_RIGHT)) {
			xa++;
		}
		if (xa != 0 || ya != 0) move(xa, ya);
	}

	public void render(int x, int y, Screen screen) {	
		screen.render(960 / 8 - 16, 540 / 8 - 16, this);
	}
}
