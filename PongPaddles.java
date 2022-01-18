package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class PongPaddles extends Rectangle
{
	int id; //track which player it is
	int yVelocity;
	int speed = 10;
	
	PongPaddles(int x, int y, int width, int height, int id)
	{
		super(x, y, width, height); //creates the rectangle
		this.id = id; //uses the one common with this class
		
	}
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W && id == 1)
		{
			setYDirection(-speed); // goes up
		}
		if (e.getKeyCode() == KeyEvent.VK_S && id == 1)
		{
			setYDirection(speed); // goes down
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && id == 2)
		{
			setYDirection(-speed); // goes up
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && id == 2)
		{
			setYDirection(speed); // goes down
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			setYDirection(0); // stops moving
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			setYDirection(0); // stops moving
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			setYDirection(0); // stops moving
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			setYDirection(0); // stops moving
		}
	}
	
	private void setYDirection(int i) 
	{
		yVelocity = i;
	}
	public void move()
	{
		y += yVelocity;
	}
	public void draw(Graphics g)
	{
		if (id == 1)
		{
			g.setColor(Color.BLUE);
		}
		else
		{
			g.setColor(Color.RED);
		}
		g.fillRect(x, y, width, height);
	}
}
