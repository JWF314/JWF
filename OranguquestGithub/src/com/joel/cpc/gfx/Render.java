package com.joel.cpc.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.joel.cpc.entity.mob.Mob;
import com.joel.cpc.level.tile.Tile;

public class Render {
	
	public int width, height;
	public int[] pixels;
	private int xOffs, yOffs;
	private Graphics g;
	
	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void graphics(Graphics g) {
		this.g = g;
	}
	
	public void drawTile(int xp, int yp, Tile tile) {
		xp -= xOffs;
		yp -= yOffs;
		for (int y = 0; y < tile.sprite.size; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.size; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.size || xa >= width || ya < -tile.sprite.size || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.size];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void drawMob(int xp, int yp, Mob mob) {
		xp -= xOffs;
		yp -= yOffs;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -32 || xa >= width || ya < -32 || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = mob.sprite.pixels[x + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void drawPlayer(int xp, int yp, Mob mob) {
		xp -= xOffs;
		yp -= yOffs;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -32 || xa >= width || ya < -32 || ya >= height) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = mob.sprite.pixels[x + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void drawText(String text, int x, int y, int size, int style, int color) {
		int r = (color & 0xff0000) >> 16;
		int g = (color & 0xff00) >> 8;
		int b = (color & 0xff);
		Color c = new Color(r, g, b);
		Font f = new Font("Verdana", style, size);
		this.g.setColor(c);
		this.g.setFont(f);
		this.g.drawString(text, x, y);
	}
	
	public void setOffset(int xOffs, int yOffs) {
		this.xOffs = xOffs;
		this.yOffs = yOffs;
	}

}
