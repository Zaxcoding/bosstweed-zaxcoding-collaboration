package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Hang extends Shape
{	
	public Hang(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 12;
		name = "Hang";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(!this.upp){
			Game.hangi.bind();
		}
		else{
			Game.hangv.bind();
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