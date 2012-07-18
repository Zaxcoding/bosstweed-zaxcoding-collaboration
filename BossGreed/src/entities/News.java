package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class News extends Shape
{		
	public News(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 17;
		name = "News";
		alive = true;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.news.bind();
		
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
