package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class ArrowKey extends Shape
{
	public int type = 0;
		
	public ArrowKey(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			Game.a3.bind();
		}
		else if(this.type==1){
			Game.a4.bind();
		}
		else if(this.type==2){
			Game.a5.bind();
		}
		else if(this.type==3){
			Game.esc.bind();
		}
		else if(this.type==4){
			Game.space.bind();
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
