package entities;

import game.GameOn;

public class Hang extends Shape
{	
	public Hang(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 12;
		name = "Hang";
		defaultWidth = 170;
		defaultHeight = 85;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (!this.vert)
		{
			GameOn.hangi.bind();
		}
		else{
			GameOn.hangv.bind();
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
