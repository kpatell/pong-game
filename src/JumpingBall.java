// Name: Krishan Patel
// JumpingBall.java

import java.awt.Color;

public class JumpingBall extends Ball {
	public JumpingBall (double x, double y, double diameter, Color color) {
		super (x, y, diameter, color);
	}
	
	/**
	 * Moves to a random location within the boundaries of the rightEdge
	 * and bottomEdge
	 * @param rightEdge the ridgtEdge of the movement area
	 * @param bottomEdge the bottomEdge of the movement area
	 */
	public void move (int rightEdge, int bottomEdge) {
		setX((Math.random() * (rightEdge - getDiameter()) + getRadius()));
		setY((Math.random() * (bottomEdge - getDiameter()) + getRadius())); 
	}
		
	/**
	 * Determines if the JumpingBall intersects, or collides with, another Ball object
	 * @param otherBall a Ball object
	 * @return true if JumpingBall intersects with the Ball, false otherwise
	 */
	public boolean intersectsWith (Ball otherBall) {
		if (findDistanceFrom(otherBall) <= (getRadius() + otherBall.getRadius())) {
			return true;
		} 
		
		return false;
	}
		
	/**
	 * Calculates and returns the distance between the center of the JumpingBall and 
	 * a specific (x, y) location.
	 * @param otherBall a Ball object
	 * @return the distance between the center of the JumpingBall and (x, y) coordinate
	 */
	public double findDistanceFrom (Ball otherBall) {
		// distance formula
		return Math.sqrt(Math.pow(getX() - otherBall.getX(), 2) + Math.pow(getY() - otherBall.getY(), 2));
	}
}