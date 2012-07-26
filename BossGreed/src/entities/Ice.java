package entities;

import game.GameOn;

public class Ice extends Shape
{	
	public Ice(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 13;
		name = "Ice";
		defaultWidth = 80;
		defaultHeight = 12;
		solid = true;
	}		
	
	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert)
			GameOn.icev.bind();
		else
			GameOn.icel.bind();
		
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
		player.onIce = true;
	}

}