package entities;

import game.Game;

public class Dead extends Shape
{	
	public Dead(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 8;
		name = "Dead";
		up = true;
		right = true;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.vert){
			if(this.j<=10){
				Game.deadv.bind();
			}
			else if(this.j<=20){
				Game.deadv1.bind();
			}
			else if(this.j<=30){
				Game.deadv2.bind();
			}
			else if(this.j<=40){
				Game.deadv3.bind();
			}
			else if(this.j<=50){
				Game.deadv4.bind();
				if(this.j==50)
					this.j=0;
			}
			else{
				this.j = 0;
			}
			this.j++;
		}
		else{
			if(this.i<=10){
				Game.deadi.bind();
			}
			else if(this.i<=20){
				Game.deadi1.bind();
			}
			else if(this.i<=30){
				Game.deadi2.bind();
			}
			else if(this.i<=40){
				Game.deadi3.bind();
			}
			else if(this.i<=50){
				Game.deadi4.bind();
				if(i==50)
					this.i=0;
			}
			
			this.i++;
		}
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
