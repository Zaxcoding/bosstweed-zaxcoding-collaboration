package entities;

import static org.lwjgl.opengl.GL11.glColor4d;
import game.Game;

public class Cloud extends Shape
{
	
	public int startx;
	
	public Cloud(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		this.startx = (int) x;
		code = 6;
		name = "Cloud";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.dx ==7){
			glColor4d(1.0,1,1,.7);
		}
		if(this.type==1){
			Game.cloud1.bind();
		}
		else if(this.type==2){
			Game.cloud2.bind();
		}
		else if(this.type==3){
			Game.cloud3.bind();
		}
		else if(this.type==4){
			Game.cloud4.bind();
		}
		else if(this.type==5){
			Game.cloud5.bind();
		}
		else if(this.type==6){
			Game.cloud6.bind();
		}
		else if(this.type==7){
			Game.cloud7.bind();
		}
		else if(this.type==8){
			Game.cloud8.bind();
		}
		else if(this.type==9){
			Game.cloud9.bind();
		}
		else if(this.type==10){
			Game.cloud10.bind();
		}
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
