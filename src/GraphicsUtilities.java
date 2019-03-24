// Name: Krishan Patel
// Unit 2 - Graphics
// GraphicsUtilities.java

import java.awt.Color;
import java.awt.Graphics;

public class GraphicsUtilities {

	/**
	 * Draws a rectangle of the given color from (0, 0) with the given width and height
	 * @param g the Graphics object
	 * @param color the Color of the background
	 * @param width the width of the rectangle
	 * @param height the height of the background
	 */
	public static void drawBackground(Graphics g, Color color, int width, int height) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}

	/**
	 * Creates a grid of vertical and horizontal lines starting at (0,0) to the given 
	 * width and height at the given interval.
	 * @param g the Graphics object
	 * @param color the color of the lines
	 * @param width the width of the grid
	 * @param height the height of the grid
	 * @param increment the distance between each line
	 */
	public static void drawGrid(Graphics g, Color color, int width, int height, int increment) {
		g.setColor(color); 
		for (int i = 0; i < width; i += increment) {
			g.drawLine(i, 0, i, height);
		}
		for (int i = 0; i < height; i += increment) {
			g.drawLine(0, i, width, i);
		}
	}

	/**
	 * Draws a heart at a specific x- and y- coordinate
	 * @param g Graphics object
	 * @param x x-coordinate of the center point in between two humps
	 * @param y y-coordinate of the center point in between two humps
	 * @param scale scale in which the heart's size is increased or decreased
	 */
	public static void drawHeart(Graphics g, int x, int y, double scale) {
		g.setColor(Color.RED);

		/**
		 * From: http://mathworld.wolfram.com/HeartCurve.html
		 * x = 16 sin^3(t) 
		 * y = 13 cos(t) - 5 cos(2t) - 2 cos(3t) - cos(4t)
		 */

		int xpoints[] = new int[100];
		int ypoints[] = new int[xpoints.length];

		for (int i = 0; i < xpoints.length; i++) {
			// angle in radians
			double t = i * 2 * Math.PI / xpoints.length;

			xpoints[i] = x + (int) (scale * (16 * Math.pow(Math.sin(t), 3)));
			ypoints[i] = y - (int) (scale * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
		}

		g.fillPolygon(xpoints, ypoints, xpoints.length);
	}
	
	/**
	 * Returns a random color
	 * @return random color
	 */
	public static Color randomColor() {
		int red = (int)(Math.random() * 256); 
		int green = (int)(Math.random() * 256);
		int blue = (int)(Math.random() * 256); 

		Color randomColor = new Color(red, green, blue);
		
		return randomColor;
	}
}