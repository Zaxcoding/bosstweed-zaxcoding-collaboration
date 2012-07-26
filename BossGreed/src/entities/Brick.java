package entities;

import game.GameOn;

public class Brick extends Shape
{
	public Brick(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 5;
		name = "Brick";
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert){
			GameOn.brickv.bind();
		}
		else{
			GameOn.brick.bind();
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