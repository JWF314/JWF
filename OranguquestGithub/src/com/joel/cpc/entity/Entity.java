package com.joel.cpc.entity;

import java.util.Random;

import com.joel.cpc.gfx.Render;
import com.joel.cpc.level.Level;

public class Entity {
	
	public int x, y;
	public boolean removed = false;
	protected Level level;
	protected Random rand = new Random();
	
	public void tick() {
		
	}
	
	public void render(Render render) {
		
	}
	
	public void remove() {
		level.remove(this);
		removed = false;
	}
	
	public final void init(Level level) {
		this.level = level;
	}

}
