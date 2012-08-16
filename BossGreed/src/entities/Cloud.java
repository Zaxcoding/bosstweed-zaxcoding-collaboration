package entities;

import static org.lwjgl.opengl.GL11.glColor4d;
import game.GameShell;

public class Cloud extends Shape
{
	public int startx;

	public Cloud(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		this.startx = (int) x;
		code = 6;
		name = "Cloud";
		defaultWidth = 128;
		defaultHeight = 128;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.dx == 7)
		{
			glColor4d(1.0, 1, 1, .7);
		}
		if (this.type == 1)
		{
			GameShell.cloud1.bind();
		} else if (this.type == 2)
		{
			GameShell.cloud2.bind();
		} else if (this.type == 3)
		{
			GameShell.cloud3.bind();
		} else if (this.type == 4)
		{
			GameShell.cloud4.bind();
		} else if (this.type == 5)
		{
			GameShell.cloud5.bind();
		} else if (this.type == 6)
		{
			GameShell.cloud6.bind();
		} else if (this.type == 7)
		{
			GameShell.cloud7.bind();
		} else if (this.type == 8)
		{
			GameShell.cloud8.bind();
		} else if (this.type == 9)
		{
			GameShell.cloud9.bind();
		} else if (this.type == 10)
		{
			GameShell.cloud10.bind();
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
