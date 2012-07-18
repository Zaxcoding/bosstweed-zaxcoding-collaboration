package entities;

import game.Game;

public class Ledge extends Shape
{	
	public Ledge(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 15;
		name = "Ledge";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.ledgei.bind();
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
