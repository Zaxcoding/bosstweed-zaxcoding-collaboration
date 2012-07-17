package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Gem extends Shape
{
	public boolean vert;
	
	public Gem(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 10;
		name = "Gem";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (!this.vert)
			Game.door.bind();
		else
			Game.doorv.bind();
		
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
