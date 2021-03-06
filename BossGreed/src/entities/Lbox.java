package entities;

import game.GameShell;

public class Lbox extends Shape
{
	public Lbox(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 14;
		name = "Lbox";
	}

	@Override
	public void draw()
	{
		textureStart();
		GameShell.lboxi.bind();
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
