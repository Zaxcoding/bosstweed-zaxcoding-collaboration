package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Bat extends Shape
{
	public boolean on = false, up = false, vert = false;
	
	public Bat(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 3;
		name = "Bat";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if (this.vert)
			Game.cliffv.bind();
		else
			Game.cliffi.bind();

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
