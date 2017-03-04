package com.joel.cpc.entity.mob;

import com.joel.cpc.entity.Entity;
import com.joel.cpc.gfx.Sprite;

public class Mob extends Entity {
	
	public Sprite sprite;
	
	protected int dir = 0;
	protected boolean walking = false;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;	
		}
	}
	
	public void tick() {
		
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int i = 0; i < 4; i++) {
			int xc = ((x + xa) + (i % 2 * 2) * 5) >> 4;
			int yc = ((y + ya) + (i / 2 * 2 - 4) * 2) >> 4;
			if (level.getTile(xc, yc).solid()) solid = true;
		}
		return solid;
	}

}
