package entities;

import game.GameShell;

public class Arrow extends Shape
{
	public Arrow(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 1;
		name = "Arrow";
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.i < 10)
		{
<<<<<<< HEAD
			GameShell.al1.bind();
		} else if (this.i >= 10 && this.i <= 20)
		{
			GameShell.al2.bind();
=======
			GameShell.a1.bind();
		} else if (this.i >= 10 && this.i <= 20)
		{
			GameShell.a2.bind();
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2
			if (this.i == 20)
			{
				this.i = 0;
			}
		}
		this.i++;

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
