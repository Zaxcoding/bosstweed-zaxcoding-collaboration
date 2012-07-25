package entities;

import game.GameOn;

public class Grav extends Shape
{
	public Grav(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 11;
		name = "Grav";
		init = 5;
		defaultWidth = 80;
		defaultHeight = 12;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.i<=2*init){
			GameOn.gravflip.bind();
		}
		else if(this.i>2*init&&this.i<=4*init)
		{
			GameOn.gravflip2.bind();
		}
		else if(this.i>4*init&&this.i<=6*init)
		{
			GameOn.gravflip3.bind();
		}
		else if(this.i>6*init&&this.i<=8*init)
		{
			GameOn.gravflip4.bind();
		}
		else if(this.i>8*init&&this.i<=10*init)
		{
			GameOn.gravflip5.bind();
		}
		else if(this.i>10*init&&this.i<=12*init)
		{
			GameOn.gravflip6.bind();
		}
		else if(this.i>12*init&&this.i<=14*init)
		{
			GameOn.gravflip7.bind();
		}
		else if(this.i>14*init&&this.i<=16*init)
		{
			GameOn.gravflip8.bind();
		}
		else if(this.i>16*init&&this.i<=18*init)
		{
			GameOn.gravflip9.bind();
		}
		else if(this.i>18*init&&this.i<=20*init)
		{
			GameOn.gravflip10.bind();
		}
		else if(this.i>20*init&&this.i<=22*init)
		{
			GameOn.gravflip11.bind();
		}
		else if(this.i>22*init&&this.i<=24*init)
		{
			GameOn.gravflip12.bind();
		}
		else if(this.i>24*init&&this.i<=26*init)
		{
			GameOn.gravflip13.bind();
		}
		else if(this.i>26*init&&this.i<=28*init)
		{
			GameOn.gravflip14.bind();
		}
		else if(this.i>28*init&&this.i<=30*init)
		{
			GameOn.gravflip15.bind();
		}
		else if(this.i>30*init&&this.i<=32*init)
		{
			GameOn.gravflip16.bind();
		}
		else if(this.i>32*init&&this.i<=34*init)
		{
			GameOn.gravflip17.bind();
		}
		else if(this.i>34*init&&this.i<=36*init)
		{
			GameOn.gravflip18.bind();
			if(this.i==36*init){
				this.i=0;
			}
		}
		this.i++;
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		if((this.getX()+this.getWidth())>= other.getX()&&(this.getX()+this.getWidth())<=(other.getX()+other.getWidth())&&(this.getY()+this.getHeight())>=other.getY()&&(this.getY()+this.getHeight())<=(other.getY()+other.getHeight()))
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
		return intersect;
	}
	
	@Override
	public void interact(Box player) 
	{
		if (player.groundPiece != this)
		{
			player.gravityMod = -player.gravityMod;
			player.grounded = false;
			player.bounce = true;
		}
	}

}
