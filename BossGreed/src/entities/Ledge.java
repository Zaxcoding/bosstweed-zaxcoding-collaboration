package entities;

import game.GameOn;

public class Ledge extends Shape
{	
	public Ledge(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 15;
		name = "Ledge";
		defaultWidth = 48;
		defaultHeight = 12;
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		GameOn.ledgei.bind();
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}
	
	@Override
	public void interact(Box player) 
	{
		// nothing
	}

}
