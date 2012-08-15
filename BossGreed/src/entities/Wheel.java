package entities;

import game.GameShell;

public class Wheel extends Shape
{
	public Wheel(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 23;
		name = "Wheel";
		on = true;
		defaultWidth = 32;
		defaultHeight = 32;
	}

	@Override
	public void draw()
	{
		textureStart();
		if (on)
		{
			GameShell.wheeli.bind();
		} else
		{
			GameShell.wheeli2.bind();
		}

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
