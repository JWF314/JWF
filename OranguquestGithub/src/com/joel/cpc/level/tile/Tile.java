package com.joel.cpc.level.tile;

import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Render render) {
		
	}
	
	public boolean solid() {
		return false;
	}

}
