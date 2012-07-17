package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Brick extends Shape
{
	public boolean up,on;

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
	public void save(ObjectOutputStream OS)
	{
	}

	@Override
	public Shape load(ObjectInputStream IS)
	{
		return null;
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}