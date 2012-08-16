package entities;

import game.GameShell;

public class Cactus extends Shape
{

	public Cactus(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 24;
		name = "Cactus";
		defaultWidth = 44;
		defaultHeight = 86;
	}

	@Override
	public void interact(Box player)
	{
		// do nothing
	}

	@Override
	public void draw()
	{
		textureStart();

		GameShell.cactus.bind();

		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
