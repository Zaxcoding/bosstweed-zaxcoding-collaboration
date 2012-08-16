package entities;

import game.GameShell;

public class Skyline extends Shape
{
	public Skyline(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 20;
		name = "Skyline";
		defaultWidth = 26;
		defaultHeight = 26;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.type == 0)
		{
			GameShell.sky1.bind();
		} else if (this.type == 1)
		{
			GameShell.sky2.bind();
		} else if (this.type == 2)
		{
			GameShell.sky3.bind();
		} else if (this.type == 3)
		{
			GameShell.sky4.bind();
		} else if (this.type == 4)
		{
			GameShell.sky5.bind();
		} else if (this.type == 5)
		{
			GameShell.sky6.bind();
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
