package entities;

import game.GameShell;

public class ArrowKey extends Shape
{
	public ArrowKey(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 2;
		name = "ArrowKey";
		defaultWidth = 64;
		defaultHeight = 64;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.type == 0)
		{
			GameShell.a3.bind();
		} else if (this.type == 1)
		{
			GameShell.a4.bind();
		} else if (this.type == 2)
		{
			GameShell.a5.bind();
		} else if (this.type == 3)
		{
			GameShell.esc.bind();
		} else if (this.type == 4)
		{
			GameShell.space.bind();
		}

		textureVertices();
	}

	@Override
	public void interact(Box player)
	{
		// nothing
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
