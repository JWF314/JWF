package com.joel.cpc.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.joel.cpc.entity.Entity;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.level.tile.Tile;

public class Level {
	
	public int width, height;
	public int[] tiles;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
	}
	
	public Level(String path) {
		loadLevelFromFile(path);
	}
	
	private void loadLevelFromFile(String path) {
		try {
			BufferedImage img = ImageIO.read(Level.class.getResource(path));
			int w = this.width = img.getWidth();
			int h = this.height = img.getHeight();
			tiles = new int[w * h];
			img.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	public void render(int xScroll, int yScroll, Render render) {
		render.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + render.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + render.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				Tile tile = getTile(x, y);
				tile.render(x, y, render);
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(render);
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
		e.init(this);
	}
	
	public void add(Entity e, Level level) {
		entities.add(e);
		e.init(level);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xff408000) return Tile.grass;
		if (tiles[x + y * width] == 0xffff00ff) return Tile.flower;
		return Tile.voidTile;
	}
	
	public void killAll() {
		entities.clear();
	}

}
