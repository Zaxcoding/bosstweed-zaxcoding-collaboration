package entities;

import game.GameOn;

public class Doorjam extends Shape
{		
	public Doorjam(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 9;
		name = "Doorjam";
		defaultWidth = 32;
		defaultHeight = 128;
		solid = true;
	}
	
	@Override
	public void draw()
	{
		textureStart();
		
		if (!this.vert){
			GameOn.doorjam.bind();
		}
		else{
			GameOn.doorjamv.bind();
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
		player.alive = false;
	}

}
