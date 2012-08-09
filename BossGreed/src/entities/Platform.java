package entities;

import game.GameOn;

public class Platform extends Shape
{
	int disappearCounter;

	public Platform(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 25;
		name = "Platform";
		defaultWidth = 256;
		defaultHeight = 64;
		solid = true;
	}

	@Override
	public void interact(Box player)
	{
		//disappearCounter++;

	}

	@Override
	public void draw()
	{
		textureStart();

		GameOn.desertplatform.bind();

		textureVertices();

	}

	@Override
	public boolean intersects(Shape other)
	{

		return false;
	}

}
