package com.joel.cpc.menu;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;

public class GameOverMenu extends Menu {

	public GameOverMenu(KeyInput input) {
		super(input);
	}
	
	int timer = 18;
	String[] options = { "Play Again", "Title Menu" };
	int selected = 0;
	
	public void tick() {
		if (timer > 0) timer--;
		if (input.down && selected < options.length && timer == 0) {
			selected++;
			timer = 10;
		}
		if (input.up && selected < options.length && timer == 0) {
			selected--;
			timer = 10;
		}
		
		if (selected < 0) selected = 0;
		if (selected > 1) selected = 1;
		
		if (selected == 0) {
			options[selected] = "> " + "Play Again?" + " <";
			if (input.use) {
				Game.level.killAll();
				input.releaseAll();
				Game.menu = null;
				PlayMenu.biome = 0; //change to random when there are multiple levels
			}
		} else {
			options[0] = "Play Again?";
		}
		if (selected == 1) {
			options[selected] = "> Title Menu" + " <";
			if (input.use) {
				Game.menu = new TitleMenu(input);
				Game.level.killAll();
				Game.setMenuLevel();
			}
		} else {
			options[1] = "Title Menu";
		}
	}
	
	public void render(Render render) {
		render.drawText("You found", 250 + 4, 140 + 4, 70, 1, 0);
		render.drawText("You found", 250, 140, 70, 1, 0xffffff);
		render.drawText("the Orangutan!", 170 + 4, 200 + 4, 70, 1, 0);
		render.drawText("the Orangutan!", 170, 200, 70, 1, 0xffffff);
		for (int i = 0; i < options.length; i++) {
			render.drawText(options[i], 15 + 3, 350 + i * 60 + 3, 50, 1, 0);
			render.drawText(options[i], 15, 350 + i * 60, 50, 1, 0xffffff);
		}
	}
}
