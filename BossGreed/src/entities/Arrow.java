package entities;

import game.Game;

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
			Game.a1.bind();
		}
		else if (this.i >= 10 && this.i <= 20)
		{
			Game.a2.bind();
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

}
