package entities;

import game.Game;

public class Wall extends Shape
{
	public Wall(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 22;
		name = "Wall";
	}

	@Override
	public void draw()
	{
		textureStart();
		Game.wallpaper.bind();
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
