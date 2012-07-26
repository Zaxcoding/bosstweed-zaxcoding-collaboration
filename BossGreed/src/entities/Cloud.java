package entities;

import static org.lwjgl.opengl.GL11.glColor4d;
import game.GameOn;

public class Cloud extends Shape
{
	public int startx;
	
	public Cloud(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		this.startx = (int) x;
		code = 6;
		name = "Cloud";
		defaultWidth = 128;
		defaultHeight = 128;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.dx ==7){
			glColor4d(1.0,1,1,.7);
		}
		if(this.type==1){
			GameOn.cloud1.bind();
		}
		else if(this.type==2){
			GameOn.cloud2.bind();
		}
		else if(this.type==3){
			GameOn.cloud3.bind();
		}
		else if(this.type==4){
			GameOn.cloud4.bind();
		}
		else if(this.type==5){
			GameOn.cloud5.bind();
		}
		else if(this.type==6){
			GameOn.cloud6.bind();
		}
		else if(this.type==7){
			GameOn.cloud7.bind();
		}
		else if(this.type==8){
			GameOn.cloud8.bind();
		}
		else if(this.type==9){
			GameOn.cloud9.bind();
		}
		else if(this.type==10){
			GameOn.cloud10.bind();
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
