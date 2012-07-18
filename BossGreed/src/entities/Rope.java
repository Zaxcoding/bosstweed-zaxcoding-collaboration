package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Rope extends Shape
{	
	public Rope(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 18;
		name = "Rope";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.ropei.bind();
		
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