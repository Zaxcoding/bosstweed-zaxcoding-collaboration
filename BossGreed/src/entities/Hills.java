package entities;

import game.GameShell;

public class Hills extends Shape
{

	public Hills(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 26;
		name = "Hills";
		defaultWidth = 1024;
		defaultHeight = 512;
	}

	@Override
	public void interact(Box player)
	{
		//do nothing
	}

	@Override
	public void draw()
	{
		textureStart();

		GameShell.desertback.bind();

		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
