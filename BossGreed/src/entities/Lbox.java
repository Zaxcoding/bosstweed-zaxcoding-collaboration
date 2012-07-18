package entities;

import game.Game;

public class Lbox extends Shape
{
	public Lbox(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 14;
		name = "Lbox";
	}

	@Override
	public void draw()
	{
		textureStart();
		Game.lboxi.bind();
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
