package entities;

import game.Game;

public class Rope extends Shape
{	
	public Rope(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 18;
		name = "Rope";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.ropei.bind();
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
