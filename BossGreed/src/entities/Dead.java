package entities;

import game.GameOn;

public class Dead extends Shape
{	
	public Dead(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 8;
		name = "Dead";
		defaultWidth = 80;
		defaultHeight = 18;
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.vert){
			if (this.i<=10){
				GameOn.deadv.bind();
			}
			else if(this.i<=20){
				GameOn.deadv1.bind();
			}
			else if(this.i<=30){
				GameOn.deadv2.bind();
			}
			else if(this.i<=40){
				GameOn.deadv3.bind();
			}
			else if(this.i<=50){
				GameOn.deadv4.bind();
				if(this.i==50)
					this.i=0;
			}
			else{
				this.i = 0;
			}
			this.i++;
		}
		else{
			if(this.i<=10){
				GameOn.deadi.bind();
			}
			else if(this.i<=20){
				GameOn.deadi1.bind();
			}
			else if(this.i<=30){
				GameOn.deadi2.bind();
			}
			else if(this.i<=40){
				GameOn.deadi3.bind();
			}
			else if(this.i<=50){
				GameOn.deadi4.bind();
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
	
	@Override
	public void interact(Box player) 
	{
		player.alive = false;
	}

}
