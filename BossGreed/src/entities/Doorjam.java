package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Doorjam extends Shape
{		
	public Doorjam(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 9;
		name = "Doorjam";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!this.vert){
			Game.doorjam.bind();
		}
		else{
			Game.doorjamv.bind();
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
