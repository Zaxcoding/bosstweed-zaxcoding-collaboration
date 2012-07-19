package entities;

import game.Game;

public class Gem extends Shape
{	
	public Gem(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 10;
		name = "Gem";
		defaultWidth = 64;
		defaultHeight = 64;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (!this.vert)
			Game.door.bind();
		else
			Game.doorv.bind();
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
