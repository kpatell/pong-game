// Name: Krishan Patel
// LongPowerup.java

import java.awt.Color;

public class LongPowerup extends JumpingBall {	
	public LongPowerup(double x, double y, double diameter, Color color) {
		super(x, y, diameter, color);
	}
	
	/**
	 * Powerup spawns anywhere within the screen, given constraints
	 * @param leftEdge left edge of the movement area
	 * @param rightEdge right edge of the movement area
	 * @param topEdge top edge of the movement area
	 * @param bottomEdge bottom edge of the movement area
	 */
	public void move(int leftEdge, int rightEdge, int topEdge, int bottomEdge) {
		setX((Math.random() * (rightEdge - getDiameter()) + getRadius() + leftEdge));
		setY((Math.random() * (bottomEdge - getDiameter()) + getRadius() + topEdge)); 
	}
 }