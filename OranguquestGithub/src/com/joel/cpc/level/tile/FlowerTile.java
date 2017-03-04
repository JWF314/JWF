package com.joel.cpc.level.tile;

import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.Sprite;

public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Render render) {
		render.drawTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}

}
