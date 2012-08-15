package entities;

import game.GameShell;

public class Bat extends Shape
{
	public Bat(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 3;
		name = "Bat";
		defaultWidth = 80;
		defaultHeight = 12;
		solid = true;
		visible = true;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.vert)
			GameShell.cliffv.bind();
		else if (this.type == 1)
			GameShell.cliffdesert.bind();
		else if (this.type == 2)
			GameShell.cliffdesert2.bind();
		else
			GameShell.cliffi.bind();

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
