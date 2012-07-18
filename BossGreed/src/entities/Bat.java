package entities;

import game.Game;

public class Bat extends Shape
{	
	public Bat(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 3;
		name = "Bat";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert)
			Game.cliffv.bind();
		else
			Game.cliffi.bind();

		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
