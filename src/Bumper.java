// Name: Krishan Patel
// Date: 03/12/2019
// Unit 2 - Graphics
// Bumper.java

import java.awt.Color;
import java.awt.Graphics;

public class Bumper {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	/**
	 * Sets the bumper at (100, 100), width at 60, height at 100, and the color as blue
	 */
	public Bumper() {
		this.x = 100;
		this.y = 100;
		this.width = 60;
		this.height = 100;
		this.color = Color.BLUE;
	}
	
	/**
	 * Sets bumper at specified (x, y), width, height, and color
	 * @param x x-coordinate of the center of the bumper
	 * @param y y-coordinate of the center of the bumper
	 * @param width width of the bumper
	 * @param height height of the bumper
	 * @param color color of the bumper
	 */
	public Bumper(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	/**
	 * returns the x-coordinate of the center of the bumper
	 * @return the x-coordinate of the center of the bumper
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x-coordinate of the center of the bumper
	 * @param x x-coordinate the center of the bumper will be set to
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * returns the y-coordinate of the center of the bumper
	 * @return the y-coordinate of the center of the bumper
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the y-coordinate of the center of the bumper
	 * @param y y-coordinate the center of the bumper will be set to
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * returns the width of the bumper
	 * @return the width of the bumper
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the width of the bumper
	 * @param width width the bumper will be set to
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * returns the height of the bumper
	 * @return the height of the bumper
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height of the bumper
	 * @param height height the bumper will be set to
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * returns the color of the bumper
	 * @return the color of the bumper
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * sets the color of the bumper
	 * @param color color the bumper will be set to
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Sets the center location of the bumper
	 * @param x the x-coordinate of the center of the bumper
	 * @param y the y-coordinate of the center of the bumper
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws the bumper
	 * @param g the Graphics object
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRoundRect(x - (int)(width / 2), y - (int)(height / 2), width, height, 10, 10);
	}

	/**
	 * Returns true if any part of the Ball is inside the bumper
	 * @param ball the Ball
	 * @return true if any part of the Ball is inside the bumper, false otherwise
	 */
	public boolean inBumper(Ball ball) {
		for (int x = getX() - getWidth()/2; x <= getX() + getWidth()/2; x++) {
			for (int y = getY() - getHeight()/2; y <= getY() + getHeight()/2; y++) {
				if (getDistance(x, y, ball.getX(), ball.getY()) <= ball.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Calculates the distance between (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x-coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 * @return the distance between (x1, y1) and (x2, y2)
	 */
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}