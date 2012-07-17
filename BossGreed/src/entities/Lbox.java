package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Lbox extends Shape
{
	public Lbox(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public void draw()
	{
		textureStart();
		Game.lboxi.bind();
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
