package entities;

import game.Game;

public class Brick extends Shape
{
	public Brick(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 5;
		name = "Brick";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.up){
			Game.brickv.bind();
		}
		else{
			Game.brick.bind();
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