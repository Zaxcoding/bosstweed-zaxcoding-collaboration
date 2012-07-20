package entities;

import game.Game;

public class Doorjam extends Shape
{		
	Ledge ledge;
	
	public Doorjam(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 9;
		name = "Doorjam";
		defaultWidth = 32;
		defaultHeight = 128;
	}

	public void setLedge(Ledge ledge)
	{
		this.ledge = ledge;
	}
	
	@Override
	public void draw()
	{
		textureStart();
		
		if(!this.vert){
			Game.doorjam.bind();
		}
		else{
			Game.doorjamv.bind();
		}
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
