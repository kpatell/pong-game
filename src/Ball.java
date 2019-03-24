// Name: Krishan Patel
// Date: 01/28/2019
// Unit 2 - Graphics
// Ball.java

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private double x;			// x-coordinate of the center of the ball
	private double y;			// y-coordinate of the center of the ball
	private double diameter;	// diameter of the ball
	private double radius;		// radius of the ball = diameter/2
	private Color color;		// color of the ball
	private double xSpeed;		// x-speed = change in x-position
	private double ySpeed;		// y-speed = change in y-position

	/**
	 * Creates a red ball with a center at (0,0) 
	 * and a diameter of 25. The default speed is 0.
	 */
	public Ball() {
		this.x = 0;
		this.y = 0;
		this.diameter = 25;
		this.radius = 25 / 2.0;
		this.color = Color.RED;
		this.xSpeed = 0;
		this.ySpeed = 0;
	}

	/**
	 * Creates a ball based on user-inputted coordinates, diameter, and Color.
	 * Sets the x- and y-speed to 0.
	 * @param x the x-coordinate of center of the ball
	 * @param y the y-coordinate of center of the ball
	 * @param diameter the diameter of the ball
	 * @param color the color of the ball
	 */
	public Ball(double x, double y, double diameter, Color color) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.radius = diameter / 2.0;
		this.color = color;
		this.xSpeed = 0;
		this.ySpeed = 0;
	}

	/**
	 * Returns the x-coordinate of the center of the ball
	 * @return the x-coordinate of the center of the ball
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the y-coordinate of the center of the ball
	 * @return the y-coordinate of the center of the ball
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the diameter of the ball
	 * @return the diameter of the ball
	 */
	public double getDiameter() {
		return diameter;
	}
	
	/**
	 * Returns the radius of the ball
	 * @return the radius of the ball
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Returns the color of the ball
	 * @return the color of the ball
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Returns the x-speed of the ball
	 * @return the x-speed of the ball
	 */
	public double getXSpeed() {
		return xSpeed;
	}
	
	/**
	 * Returns the y-speed of the ball
	 * @return the y-speed of the ball
	 */
	public double getYSpeed() {
		return ySpeed;
	}
	
	/**
	 * Sets center of the ball to a specified x-coordinate
	 * @param x the x-coordinate of the center of the ball
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets center of the ball to a specified y-coordinate
	 * @param y the y-coordinate of the center of the ball
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Sets ball to specified diameter. Based on diameter value,
	 * sets the radius to the correct value.
	 * @param diameter the diameter of the ball
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
		radius = diameter / 2.0;
	}
	/**
	 * Sets ball to specified radius. Based on radius value,
	 * sets the diameter to the correct value.
	 * @param radius the radius of the ball
	 */
	public void setRadius(double radius) {
		this.radius = radius;
		diameter = radius * 2.0;
	}
	
	/**
	 * Sets the ball to a new color
	 * @param color the color of the ball
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Sets the ball to specified x-speed
	 * @param xSpeed the x-speed of the ball (change in x)
	 */
	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	/**
	 * Sets the ball to specified y-speed
	 * @param ySpeed the y-speed of the ball (change in y)
	 */
	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * Draws the ball
	 * @param g the Graphics object
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)diameter, (int)diameter);
	}
	
	/**
	 * Sets the center location of the ball
	 * @param x the x-coordinate of the center of the ball
	 * @param y the y-coordinate of the center of the ball
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Generates a speed between -<code>maxSpeed<code>
	 * and <code>maxSpeed<code>
	 * @param maxSpeed the max speed user wants ball to go
	 */
	public void setRandomSpeed(double maxSpeed) {
		maxSpeed = (Math.random() * (maxSpeed * 2 + 1)) - maxSpeed;
		xSpeed = maxSpeed;
		
		maxSpeed = (Math.random() * (maxSpeed * 2 + 1)) - maxSpeed;
		ySpeed = maxSpeed;
	}

	/**
	 * Makes the ball move around the screen and bounce off the edges.
	 * @param rightEdge the right edge of the window (= width of window)
	 * @param bottomEdge the bottom edge of the window (= height of window)
	 */
	public void move(int rightEdge, int bottomEdge) {
		// bounce off top edge
		if (y - radius >= 0) {
			setY(getY() + ySpeed);
		} else if (y - radius <= 0) {
			setYSpeed(-1 * ySpeed);
			setY(getY() + ySpeed);
		}
		
		// bounce off of bottom edge
		if (y + radius <= bottomEdge) {
			setY(getY() + ySpeed);
		} else if (y + radius > bottomEdge) {
			setYSpeed(-1 * ySpeed);
			setY(getY() + ySpeed);
		}
		
		// bounce off of left edge
		if (x - radius >= 0) {
			setX(getX() + xSpeed);
		} else if (x - radius < 0) {
			setXSpeed(-1 * xSpeed);
			setX(getX() + xSpeed);
		}
		
		// bounce off of right edge
		if (x + radius <= rightEdge) {
			setX(getX() + xSpeed);
		} else if (x + radius > rightEdge) {
			setXSpeed(-1 * xSpeed);
			setX(getX() + xSpeed);
		}
		
//		// check to see if ball is within bounds at all times
//		if (x - radius < -5) {
//			setX(radius + 1);
//		}
//		
//		if (x + radius > rightEdge + 5) {
//			setX(radius - (rightEdge - 1));
//		}
//		
//		if (y - radius < -5) {
//			setY(radius + 1);
//		}
//		
//		if (y + radius > bottomEdge + 5) {
//			setY(radius - (bottomEdge - 1));
//		}
	}
	
	/**
	 * Makes the ball move around the screen for pong game.
	 */
	public void movePong() {
		setY(getY() + ySpeed);
		setX(getX() + xSpeed);
	}
}