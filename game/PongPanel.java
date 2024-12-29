package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class PongPanel extends JPanel implements Runnable
{

	
	private int width = 1000, height = (int)(1000 * (5.0/9.0));
	private Dimension size = new Dimension(width,height);
	static final int BALLDIAMETER = 20;
	static final int PWIDTH = 15;
	static final int PHEIGHT = 100;
	
	Thread gameThread; //path followed when executed
	Image image;
	Graphics graphics; //graphics class that paints screen
	Random rand;
	PongPaddles player1;
	PongPaddles player2;
	PongBall ball;
	PongScore score;
	
	
	public PongPanel() 
	{
		this.setPreferredSize(size);
		setFocusable(true);
		newPaddles();
		newBall();
		score = new PongScore(width,height);
		addKeyListener(new AL());
		gameThread = new Thread(this); //instantiating thread
		gameThread.start(); //starting thread
	}
	private void newBall() 
	{
		ball = new PongBall((width/2)-(BALLDIAMETER/2), 
				(height/2) - (BALLDIAMETER/2),
				BALLDIAMETER, BALLDIAMETER); //instantiation 
	}
	
	private void newPaddles() 
	{
		player1 = new PongPaddles(3,(HEIGHT/2)-PHEIGHT/2, PWIDTH, PHEIGHT, 1);
		player2 = new PongPaddles(width - PWIDTH - 3, (HEIGHT/2)-PHEIGHT/2, PWIDTH, PHEIGHT, 2);
	}
	
	public void paint(Graphics g)
	{
		image = createImage(getWidth(), getHeight()); //create new image
		graphics = image.getGraphics(); //set graphics to be image
		draw(graphics); //draw graphics
		g.drawImage(image, 0, 0, this); //draw that image on the screen
	}
	
	public void draw(Graphics g)
	{
		player1.draw(g);
		player2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move()
	{
		player1.move();
		player2.move();
		ball.move();
		
	}
	
	public void checkCollision()
	{
		if(player1.y <= 0)
        {
            player1.y = 0;
        }
        if(player1.y >= height-PHEIGHT)
        {
            player1.y = height-PHEIGHT;
        }
        if(player2.y <= 0)
        {
            player2.y = 0;
        }
        if(player2.y >= height-PHEIGHT)
        {
            player2.y = height-PHEIGHT;
        }
        
        if (ball.y <= 0 || ball.y >= height-BALLDIAMETER)
        {
        	ball.setyDirection(-ball.yVelocity);
        }
        
        if (ball.intersects(player1) || ball.intersects(player2))
        {
        	ball.setxDirection(-ball.xVelocity);
        }
        
        if (ball.x <= -BALLDIAMETER) //right side winner
        {
        	score.score2++;
        	newBall();
        }
        if (ball.x >= width) //left side winner
        {
        	score.score1++;
        	newBall();
        }
        
	}
			
	
	@Override
	public void run() 
	{
		//game loop
		long lastTime = System.nanoTime();
		int fps = 60;
		double ns = 1000000000/fps;
		double delta = 0;
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				move();
				checkCollision();
				repaint();
				delta--;
				
			}
			
		}
		
	}

	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			player1.keyPressed(e);
			player2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e)
		{
			player1.keyReleased(e);
			player2.keyReleased(e);
		}
	}
}


