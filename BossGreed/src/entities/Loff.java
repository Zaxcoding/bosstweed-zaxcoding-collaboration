package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Loff extends Shape
{
	public boolean on = false;
		
	public Loff(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 16;
		name = "Loff";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!on)
			Game.Loff.bind();
		else
			Game.Lon.bind();
		
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
