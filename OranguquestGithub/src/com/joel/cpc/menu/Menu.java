package com.joel.cpc.menu;

import java.util.Random;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;

public class Menu {
	
	protected KeyInput input;
	protected Game game;
	protected Random rand = new Random();
	
	public Menu(KeyInput input) {
		this.input = input;
	}
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void tick() {
		
	}
	
	public void render(Render render) {
		
	}

}
