package entities;

import game.Game;

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
	public boolean intersects(Shape other)
	{
		return false;
	}

}
