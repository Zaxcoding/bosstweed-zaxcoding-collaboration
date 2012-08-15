package entities;

import game.GameShell;

public class Bush extends Shape
{

	public Bush(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 27;
		name = "Bush";
		defaultWidth = 32;
		defaultHeight = 32;
	}

	@Override
	public void interact(Box player)
	{
		// do nothing
	}

	public void draw()
	{
		textureStart();

		GameShell.desertbush.bind();

		textureVertices();

	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
