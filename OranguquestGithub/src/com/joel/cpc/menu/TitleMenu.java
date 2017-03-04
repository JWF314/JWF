package com.joel.cpc.menu;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;

public class TitleMenu extends Menu {

	public TitleMenu(KeyInput input) {
		super(input);
	}
	
	int timer = 18;
	String[] options = { "Play", "Help", "About", "Quit" };
	int selected = 0;
	
	public void tick() {
		if (timer > 0) timer--;
		if (input.down && selected < options.length && timer == 0) {
			selected++;
			timer = 10;
		}
		if (input.up && selected > 0 && timer == 0) {
			selected--;
			timer = 10;
		}
		
		if (selected < 0) selected = 0;
		if (selected > 3) selected = 3;
		
		if (selected == 0) {
			options[selected] = "> " + "New Game" + " <";
		} else {
			options[0] = "New Game";
		}
		if (selected == 1) {
			options[selected] = "> " + "How To Play" + " <";
		} else {
			options[1] = "How To Play";
		}
		if (selected == 2) {
			options[selected] = "> " + "About Oranguquest" + " <";
		} else {
			options[2] = "About Oranguquest";
		}
		if (selected == 3) {
			options[selected] = "> " + "Quit" + " <";
			if (input.use) System.exit(0);
		} else {
			options[3] = "Quit";
		}
		
		if (selected == 0 && input.use && timer == 0) {
			input.releaseAll();
			Game.menu = null;
			PlayMenu.biome = 0;
		}
	}
	
	public void render(Render render) {
		render.drawText("Oranguquest", 15 + 4, 140 + 4, 120, 1, 0);
		render.drawText("Oranguquest", 15, 140, 120, 1, 0xffffff);
		render.drawText("A Java Game by Joel and Chesapeake", 140 + 2, 200 + 2, 30, 1, 0);
		render.drawText("A Java Game by Joel and Chesapeake", 140, 200, 30, 1, 0xffffff);
		render.drawText("(With Help From TheChernoProject)", 150 + 2, 230 + 2, 30, 1, 0);
		render.drawText("(With Help From TheChernoProject)", 150, 230, 30, 1, 0xffffff);
		for (int i = 0; i < options.length; i++) {
			render.drawText(options[i], 15 + 3, 300 + i * 60 + 3, 50, 1, 0);
			render.drawText(options[i], 15, 300 + i * 60, 50, 1, 0xffffff);
		}
	}

}
