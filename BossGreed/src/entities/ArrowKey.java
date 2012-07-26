package entities;

import game.GameOn;

public class ArrowKey extends Shape
{		
	public ArrowKey(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 2;
		name = "ArrowKey";
		defaultWidth = 64;
		defaultHeight = 64;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			GameOn.a3.bind();
		}
		else if(this.type==1){
			GameOn.a4.bind();
		}
		else if(this.type==2){
			GameOn.a5.bind();
		}
		else if(this.type==3){
			GameOn.esc.bind();
		}
		else if(this.type==4){
			GameOn.space.bind();
		}
		
		textureVertices();
	}
	
	@Override
	public void interact(Box player) 
	{
		// nothing
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
