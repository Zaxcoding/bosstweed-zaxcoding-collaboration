package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Ice extends Shape
{	
	public Ice(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 13;
		name = "Ice";
	}		
	
	@Override
	public void draw()
	{
		textureStart();
		
		if(this.up)
			Game.icev.bind();
		else
			Game.icel.bind();
		
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