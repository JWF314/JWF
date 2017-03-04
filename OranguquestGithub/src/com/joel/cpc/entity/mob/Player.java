package com.joel.cpc.entity.mob;

import com.joel.cpc.Game;
import com.joel.cpc.entity.Entity;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.Sprite;
import com.joel.cpc.input.KeyInput;
import com.joel.cpc.menu.GameOverMenu;
import com.joel.cpc.menu.TitleMenu;

public class Player extends Mob {
	
	private KeyInput input;
	
	public Player(KeyInput input) {
		this.input = input;
		sprite = Sprite.player_back;
		x = 255;
		y = 230;
	}
	
	public Player(int x, int y, KeyInput input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void tick() {
		int xa = 0;
		int ya = 0;
		
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		if (input.back) {
			level.killAll();
			Game.setMenuLevel();
			Game.menu = new TitleMenu(input);
		}
		
		for (int i = 0; i < level.entities.size(); i++) {
			Entity e = level.entities.get(i);
			if (e instanceof Orangutan) {
				if ((x >> 4) == (e.x >> 4) && (y >> 4) == (e.y >> 4)) {
					level.remove(((Orangutan) e));
					level.remove(this);
					Game.menu = new GameOverMenu(input);	
				}
			}
		}
	}
	
	public void render(Render render) {
		if (dir == 0) sprite = Sprite.player_forward;
		if (dir == 1) sprite = Sprite.player_right;
		if (dir == 2) sprite = Sprite.player_back;
		if (dir == 3) sprite = Sprite.player_left;
		
		render.drawPlayer(x - 16, y - 16, this);
	}

}
