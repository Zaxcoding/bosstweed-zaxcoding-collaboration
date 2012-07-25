package entities;

import game.GameOn;

public class Bat extends Shape
{	
	public Bat(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 3;
		name = "Bat";
		defaultWidth = 80;
		defaultHeight = 12;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert)
			GameOn.cliffv.bind();
		else
			GameOn.cliffi.bind();

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
