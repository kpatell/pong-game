// Name: Krishan Patel
// Date: 03/18/2019
// Unit 2 - Graphics
// Pong.java

/**************** HOW TO PLAY ****************
 * LEFT PLAYER:
 * W: up
 * S: down
 * 
 * RIGHT PLAYER:
 * UP ARROW: up
 * DOWN ARROW: down
 * 
 * OTHER CONTROLS:
 * P: pause
 * SPACE: start game/round
 * 		  during game can randomize velocity of ball
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong extends JPanel {

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1250;
	private static final int MOVE_PIXEL = 14;
	private static final int PADDING_SIZE = 30;
	private static final int POWERUP_PADDING = 50;
	private static final int BUMPER_WIDTH = 60;
	private static final int BUMPER_HEIGHT = 175;
	private static final int TOP_BOTTOM_BLOCK_HEIGHT = HEIGHT / 10;
	private static final int DASH_HEIGHT = 50;
	private static final int DASH_WIDTH = 10;
	private static final int DASH_GAP = 30;
	private static final int RANDOM_SPEED_LOW = 15;
	private static final Color BUMPER_COLOR = Color.WHITE;
	private static final Color TOP_BOTTOM_BLOCK_COLOR = new Color(255, 193, 94);
	private static final Color DASH_COLOR = new Color(57, 60, 61);

	private static int leftScore = 0;
	private static int rightScore = 0;
	private static boolean pause, startGame, wPressed, sPressed, upPressed, downPressed = false;
	private static Color backgroundColor = new Color(87, 132, 204);
	
	private BufferedImage image;
	private Graphics g;
	private Timer timer;

	private Ball ball;
	private LongPowerup longPowerup;
	private Bumper leftBumper;
	private Bumper rightBumper;
	private Bumper topConstraint;
	private Bumper bottomConstraint;

	public Pong() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		GraphicsUtilities.drawBackground(g, backgroundColor, WIDTH, HEIGHT);
		
		// set ball to center of screen
		double randomAngle = Math.random() * (Math.PI * 2);
		double randomSpeed = Math.random() * 5 + RANDOM_SPEED_LOW;
		
		ball = new Ball(WIDTH / 2, HEIGHT / 2, 100, Color.WHITE);
		ball.setXSpeed(randomSpeed * Math.cos(randomAngle));
		ball.setYSpeed(randomSpeed * Math.sin(randomAngle));
		
		// put bumpers to sides of the screen
		leftBumper = new Bumper();
		leftBumper.setLocation(leftBumper.getWidth(), HEIGHT / 2);
		leftBumper.setColor(BUMPER_COLOR);
		leftBumper.setWidth(BUMPER_WIDTH);
		leftBumper.setHeight(BUMPER_HEIGHT);

		rightBumper = new Bumper();
		rightBumper.setLocation(WIDTH - rightBumper.getWidth(), HEIGHT / 2);
		rightBumper.setColor(BUMPER_COLOR);
		rightBumper.setWidth(BUMPER_WIDTH);
		rightBumper.setHeight(BUMPER_HEIGHT);
		
		topConstraint = new Bumper();
		topConstraint.setLocation(WIDTH / 2, TOP_BOTTOM_BLOCK_HEIGHT / 2);
		topConstraint.setColor(TOP_BOTTOM_BLOCK_COLOR);
		topConstraint.setWidth(WIDTH);
		topConstraint.setHeight(TOP_BOTTOM_BLOCK_HEIGHT);
		
		bottomConstraint = new Bumper();
		bottomConstraint.setLocation(WIDTH / 2, HEIGHT - TOP_BOTTOM_BLOCK_HEIGHT / 2);
		bottomConstraint.setColor(TOP_BOTTOM_BLOCK_COLOR);
		bottomConstraint.setWidth(WIDTH);
		bottomConstraint.setHeight(TOP_BOTTOM_BLOCK_HEIGHT);
		
		longPowerup = new LongPowerup((int)(Math.random() * (WIDTH - rightBumper.getWidth() - (PADDING_SIZE * 4)) + (leftBumper.getWidth() + (PADDING_SIZE * 4))), 
				 (int)(Math.random() * (HEIGHT - bottomConstraint.getHeight() - (PADDING_SIZE * 4)) + (topConstraint.getHeight() + (PADDING_SIZE * 4))), 
				 50, 
				 Color.RED);
		
		timer = new Timer(5, new TimerListener());
		timer.start();

		addKeyListener(new Keyboard());
		setFocusable(true);
	}

	private class Keyboard implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// move left bumper up
			if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			}

			// move left bumper down
			if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			}
			
			// move right bumper up
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}

			// move right bumper down
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			
			// press 'P' to pause the game
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (pause) {
					timer.start();
				} else {
					timer.stop();
				}
				
				// switch pause to the one it isn't on
				if (pause == false) {
					pause = true;
				} else {
					pause = false;
				}
			}
			
			// Press SPACEBAR to play next round
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				startGame = true;
				
				double randomAngle = Math.random() * (Math.PI * 2);
				double randomSpeed = Math.random() * 5 + RANDOM_SPEED_LOW;

				ball.setXSpeed(randomSpeed * Math.cos(randomAngle));
				ball.setYSpeed(randomSpeed * Math.sin(randomAngle));
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = false;
			}
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	/**
	 * Moves left bumper up and down using W and S keys and 
	 * moves right bumper up and down using UP and DOWN arrow keys
	 * @param leftB left bumper of pong game
	 * @param rightB right bumper of pong game
	 */
	private void moveBumper(Bumper leftB, Bumper rightB) {
		if (wPressed) {
			if (leftB.getY() - (leftB.getHeight() / 2) - PADDING_SIZE >= TOP_BOTTOM_BLOCK_HEIGHT) {
				leftB.setY(leftB.getY() - MOVE_PIXEL);
			}
		}
		
		if (sPressed) {
			if (leftB.getY() + (leftB.getHeight() / 2) + PADDING_SIZE <= HEIGHT - TOP_BOTTOM_BLOCK_HEIGHT) {
				leftB.setY(leftB.getY() + MOVE_PIXEL);
			}
		}
		
		if (upPressed) {
			if (rightB.getY() - (rightB.getHeight() / 2) - PADDING_SIZE >= TOP_BOTTOM_BLOCK_HEIGHT) {
				rightB.setY(rightB.getY() - MOVE_PIXEL);
			}
		}
		
		if (downPressed) {
			if (rightB.getY() + (rightB.getHeight() / 2) + PADDING_SIZE <= HEIGHT - TOP_BOTTOM_BLOCK_HEIGHT) {
				rightB.setY(rightB.getY() + MOVE_PIXEL);
			}
		}
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicsUtilities.drawBackground(g, backgroundColor, WIDTH, HEIGHT);
			
			// move ball only when space is pressed
			if (startGame) {
				ball.movePong();
			}
			
			// add function to the long powerup
			if (ball.getXSpeed() > 0 && longPowerup.intersectsWith(ball)) {
				leftBumper.setHeight(rightBumper.getHeight() + 20);
				longPowerup.move(leftBumper.getWidth() + POWERUP_PADDING + PADDING_SIZE, 
						rightBumper.getWidth() - POWERUP_PADDING - PADDING_SIZE, 
						topConstraint.getHeight() + POWERUP_PADDING + PADDING_SIZE, 
						bottomConstraint.getHeight() - POWERUP_PADDING - PADDING_SIZE);
			} else if (ball.getXSpeed() < 0 && longPowerup.intersectsWith(ball)) {
				rightBumper.setHeight(rightBumper.getHeight() + 20);
				longPowerup.move(leftBumper.getWidth() + POWERUP_PADDING + PADDING_SIZE, 
						rightBumper.getWidth() - POWERUP_PADDING - PADDING_SIZE, 
						topConstraint.getHeight() + POWERUP_PADDING + PADDING_SIZE, 
						bottomConstraint.getHeight() - POWERUP_PADDING - PADDING_SIZE);
			}
			
			
			BumperCollision.collide(leftBumper, ball);
			BumperCollision.collide(rightBumper, ball);
			BumperCollision.collide(topConstraint, ball);
			BumperCollision.collide(bottomConstraint, ball);
			
			topConstraint.draw(g);
			bottomConstraint.draw(g);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier New", Font.BOLD, 60));
			g.drawString(" " + leftScore, 5, 50);
			g.drawString(" " + rightScore, WIDTH - 125, 50);
			
			// add one to the score and reset ball back to center
			if (ball.getX() - ball.getRadius() <= 0) {
				rightScore++;
				ball.setLocation(WIDTH / 2, HEIGHT / 2);
				startGame = false;
			}
			
			if (ball.getX() + ball.getRadius() >= WIDTH) {
				leftScore++;
				ball.setLocation(WIDTH / 2, HEIGHT / 2);
				startGame = false;
			}
			
			// draw the dashed line in the center
			for (int i = TOP_BOTTOM_BLOCK_HEIGHT; i < HEIGHT - TOP_BOTTOM_BLOCK_HEIGHT; i += (DASH_HEIGHT + DASH_GAP)) {
				g.setColor(DASH_COLOR);
				g.fillRoundRect((WIDTH / 2) - (DASH_WIDTH / 2), i, DASH_WIDTH, DASH_HEIGHT, 10, 10);
			}
			
			// draw stuff
			ball.draw(g);
			leftBumper.draw(g);
			rightBumper.draw(g);
			longPowerup.draw(g);
			
			if (rightScore >= 10 || leftScore >= 10) {
				GraphicsUtilities.drawBackground(g, GraphicsUtilities.randomColor(), WIDTH, HEIGHT);
				
				if (rightScore >= 10) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("Courier New", Font.BOLD, 100));
					g.drawString("RIGHT PLAYER WINS!", 300, HEIGHT / 2);
				} else if (leftScore >= 10) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("Courier New", Font.BOLD, 100));
					g.drawString("LEFT PLAYER WINS!", 300, HEIGHT / 2);
				}
			}
			
			moveBumper(leftBumper, rightBumper);
			
			repaint();
		}
	}


	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong");
		frame.setSize(1200, 900);
		frame.setLocation(200, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Pong());
		frame.setVisible(true);
	}
}