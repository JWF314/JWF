package com.joel.cpc.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public static final int SIZE = 256;
	public static int[] pixels;
	
	public SpriteSheet(String path) {
		loadImageFromFile(path);
	}
	
	public void loadImageFromFile(String path) {
		try {
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			pixels = new int[w * h];
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
