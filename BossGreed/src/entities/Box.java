package entities;

import game.GameOn;
import static org.lwjgl.opengl.GL11.*;

public class Box extends Shape
{	
	public int goldCount = 0;
	public boolean jumping, onIce = false, grounded = false, bounce = false;
	public double startX, startY;
	public int gravityMod = 1;						// gravityMod = 1 for normal, -1 for reverse
	public int lastDIR = 1;
	
	public Shape groundPiece;
	
	public Box(double x, double y , double width, double height)
	{
		super(x,y,width,height);
		code = 4;
		name = "Box";
		defaultWidth = 26;
		defaultHeight = 26;
		alive = true;
		groundPiece = null;
	}
	
	@Override
	public void draw()
	{
		textureStart();
		
		/*
		if(goldCount==0){
			if(gravity){
				if(lastDIR==1){
					left5.bind();
				}
				else if(lastDIR==2){
					right5.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft5.bind();
				}
				else if(lastDIR==2){
					gright5.bind();
				}
			}
		}
		else if(goldCount ==1){
			if(gravity){
				if(lastDIR==1){
					left4.bind();
				}
				else if(lastDIR==2){
					right4.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft4.bind();
				}
				else if(lastDIR==2){
					gright4.bind();
				}
			}
		}
		else if(goldCount ==2){
			if(gravity){
				if(lastDIR==1){
					left3.bind();
				}
				else if(lastDIR==2){
					right3.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft3.bind();
				}
				else if(lastDIR==2){
					gright3.bind();
				}
			}
		}
		else if(goldCount ==3){
			if(gravity){
				if(lastDIR==1){
					left2.bind();
				}
				else if(lastDIR==2){
					right2.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft2.bind();
				}
				else if(lastDIR==2){
					gright2.bind();
				}
			}
		}
		else if(goldCount ==4){
			if(gravity){
				if(lastDIR==1){
					left1.bind();
				}
				else if(lastDIR==2){
					right1.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft1.bind();
				}
				else if(lastDIR==2){
					gright1.bind();
				}
			}
		}
		else if(goldCount ==5){
			if(gravity){
				if(lastDIR==1){
					left.bind();
				}
				else if(lastDIR==2){
					right.bind();
				}
			}
			else{
				if(lastDIR==1){
					gleft.bind();
				}
				else if(lastDIR==2){
					gright.bind();
				}
			}
		}
		
		i++;
		if(i>100&&i<200){
			goldCount =1;
		}
		else if(i>200&&i<300){
			goldCount=2;
		}
		else if(i>300&&i<400){
			goldCount=3;
		}
		else if(i>400&&i<500){
			goldCount=4;
		}
		else if(i>500&&i<600){
			goldCount=5;
		}
		else if(i>600){
			goldCount=0;
			i=0;
		}
		*/
		
		if (gravityMod == 1)
		{
			if (lastDIR == -1)
				GameOn.left.bind();
			else if (lastDIR == 1)
				GameOn.right.bind();
		}
		else
		{
			if (lastDIR == -1)
				GameOn.gleft.bind();
			else if (lastDIR == 1)
				GameOn.gright.bind();
		}	
		textureVertices();
	}
	
	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		double p1y = this.getY() + 4;
		double p1x = this.getX() + 4;
		double p2x = this.getX() + this.getWidth() - 4;
		double p2y = this.getY()+this.getHeight() - 4;
		double p3y = other.getY();
		double p3x = other.getX();
		double p4x = other.getX()+other.getWidth();
		double p4y = other.getY()+other.getHeight();
		
		if (p2y<p3y || p1y>p4y || p2x<p3x ||p1x>p4x){
			
		}
		else{
			intersect = true;
		}
		
		
		/*if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}
		else if(this.getX()>= other.getX()&&this.getX()<=other.getX()+other.getWidth()&&this.getY()>=other.getY()&&this.getY()<=(other.getY()+other.getHeight()))
		{
			intersect = true;
			
		}	
		*/
		if (intersect && other.visible)
		{
			if (other.partner != null)
				if (other.name.equals("Grav") && other.width < 15 & other.height < 15)
				{
					other.partner.action();		// this is for blips
					other.visible = false;		// make them disappear on touch
				}
			if (other.name.equals("Grav") && other.width > 15)
				other.interact(this);
			if (other.name.equals("Coin"))
				other.interact(this);
			if (other.name.equals("Dead") || other.name.equals("Doorjam"))
				this.alive = false;
			if (other.name.equals("Sky"))
				intersect = false;
			if (onIce && other.name.equals("Ice") && other.vert)
				lastDIR *= -1;				// bouncing off of the side of ice
				
		}
		
		
		return intersect;
	}

	public boolean on(Shape other)
	{
		return(((this.getX() <= (other.getX() + other.getWidth()) || (this.getX() + this.getWidth())<=(other.getX() + other.getWidth())))&& ((this.getX() >=other.getX())||((this.getX()+this.getWidth()) >=other.getX()) ) && ((this.getY()+this.getHeight()) >=other.getY()) && ((this.getY()+this.getHeight()) <= (other.getY() + other.getHeight())));
	}
	
	public boolean leftOf(Shape other)
	{
		return((this.getX()+this.getWidth()+10)>=other.getX() &&this.getX()<=other.getX()&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	public boolean rightOf(Shape other)
	{
		return(this.getX()>=other.getX() &&this.getX()<=(other.getX()+other.getWidth()+10)&& this.getY()>other.getY() && this.getY()<( other.getY()+other.getHeight()));
	}
	
	public boolean under(Shape other)
	{
		return((this.getX()+this.getWidth()) >= other.getX() && this.getX() <= (other.getX() + other.getWidth()) && (this.getY()-this.getHeight()) <= other.getY()-other.getHeight() && (this.getY()) >= other.getY());
	}
	
	@Override
	public void interact(Box player) 
	{
		// nothing
	}
	
}