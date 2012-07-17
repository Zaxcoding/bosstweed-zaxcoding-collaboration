package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Coin extends Shape
{	
	public Coin(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.coini.bind();
		
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
