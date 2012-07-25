package entities;

import game.Game;

public class Skyline extends Shape
{	
	public Skyline(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 20;
		name = "Skyline";
		defaultWidth = 26;
		defaultHeight = 26;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.that ==0){
			Game.sky1.bind();
		}
		else if(this.that ==1){
			Game.sky2.bind();
		}
		else if(this.that ==2){
			Game.sky3.bind();
		}
		else if(this.that ==3){
			Game.sky4.bind();
		}
		else if(this.that ==4){
			Game.sky5.bind();
		}
		else if(this.that ==5){
			Game.sky6.bind();
		}
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}
	
	@Override
	public void interact(Box player) 
	{
		// nothing
	}

}
