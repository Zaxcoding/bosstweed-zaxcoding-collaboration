package entities;

import game.GameShell;

public class Brick extends Shape
{
	public Brick(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 5;
		name = "Brick";
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.vert)
		{
			GameShell.brickv.bind();
		} else
		{
			GameShell.brick.bind();
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
