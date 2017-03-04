package com.joel.cpc;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.joel.cpc.entity.mob.Orangutan;
import com.joel.cpc.entity.mob.Player;
import com.joel.cpc.entity.mob.Visiter;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.SpriteSheet;
import com.joel.cpc.input.KeyInput;
import com.joel.cpc.level.Level;
import com.joel.cpc.level.TestLevel;
import com.joel.cpc.menu.Menu;
import com.joel.cpc.menu.PlayMenu;
import com.joel.cpc.menu.TitleMenu;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 10;
	private static final int SCALE = 3;
	
	private static final String TITLE = "Oranguquest";
	private static String version  = "Testing Version 0.1";
	
	private Thread thread;
	private JFrame frame;
	private int time = 0;
	private boolean running = false;
	private BufferedImage img;
	private int[] pixels;
	private Render render;
	public static Menu menu;
	public static Level level;
	private static Player player;
	private static KeyInput key;
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		
		frame = new JFrame();
		render = new Render(WIDTH, HEIGHT);
		new SpriteSheet("/sheet/spritesheet.png");
		key = new KeyInput();
		setMenuLevel();
		menu = new TitleMenu(key);
		
		addKeyListener(key);
	}
	
	public static void setMenuLevel() {
		level = new Level("/level/testlevel.png");
		player = new Player(key);
		for (int i = 0; i < 10; i++) {
			level.add(new Visiter());
		}
		for (int i = 0; i < 2; i++) {
			level.add(new Orangutan());
		}
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "MainThread");
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {	
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			while (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(frames + " FPS, " + updates + " UPS");
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void tick() {
		time++;
		if (time > 65536) time = 0;
		key.tick();
		if (menu != null) menu.tick();
		level.tick();
		
		if (PlayMenu.biome == 0) {
			level = new TestLevel();
			Visiter v = new Visiter();
			Orangutan o = new Orangutan();
			level.add(player, level);			
			level.add(v, level);
			level.add(o, level);
			player.x = 170;
			player.y = 130;
			v.x = 150;
			v.y = 110;
			o.x = 150;
			o.y = 110;
			PlayMenu.biome = -1;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		render.graphics(g);
		render.clear();
		
		int xScroll = player.x - render.width / 2;
		int yScroll = player.y - render.height / 2;
		
		level.render(xScroll, yScroll, render);
		
		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = render.pixels[i];
		}
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		if (menu != null) menu.render(render);
		render.drawText(version, 15 + 2, 35 + 2, 40, 0, 0);
		render.drawText(version, 15, 35, 40, 0, 0xffffff);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}
