package entities;

import game.GameShell;

public class Rope extends Shape
{
	public Rope(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 18;
		name = "Rope";
		defaultWidth = 2;
		defaultHeight = 75;
	}

	@Override
	public void draw()
	{
		textureStart();

		GameShell.ropei.bind();

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
