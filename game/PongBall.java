package game;

import java.util.*;
import java.awt.*;

public class PongBall extends Rectangle
{
	Random rand = new Random();
	int xVelocity;
	int yVelocity;
	int speed = 2;
	
	
	PongBall(int x, int y, int width, int height)
	{
		super(x, y, width, height); //creates a rectangle
		int x1 = rand.nextInt(2);
		if (x1 == 0) //left
		{
			setxDirection(-speed);
		}
		else //right
		{
			setxDirection(speed);
		}
		
		int y1 = rand.nextInt(2);
		if (y1 == 0) 
		{
			setyDirection(-speed); //up
		}
		else 
		{
			setyDirection(speed); //down
		}
	}


	public void setyDirection(int i)
	{
		yVelocity = i;
	}


	public void setxDirection(int i) 
	{
		xVelocity = i;
	}
	public void move()
	{
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) //rectangle object
	{
		g.setColor(Color.GRAY);
		g.fillOval(x, y, width, height);
	}
}


