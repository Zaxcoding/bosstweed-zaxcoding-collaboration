package entities;

import game.GameOn;

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
		
		if(this.type ==0){
			GameOn.sky1.bind();
		}
		else if(this.type==1){
			GameOn.sky2.bind();
		}
		else if(this.type ==2){
			GameOn.sky3.bind();
		}
		else if(this.type ==3){
			GameOn.sky4.bind();
		}
		else if(this.type ==4){
			GameOn.sky5.bind();
		}
		else if(this.type ==5){
			GameOn.sky6.bind();
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
