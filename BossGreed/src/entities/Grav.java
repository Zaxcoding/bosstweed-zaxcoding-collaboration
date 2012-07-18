package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import game.Game;

public class Grav extends Shape
{
	public Grav(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 11;
		name = "Grav";
		init = 5;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.i<=2*init){
			Game.gravflip.bind();
		}
		else if(this.i>2*init&&this.i<=4*init)
		{
			Game.gravflip2.bind();
		}
		else if(this.i>4*init&&this.i<=6*init)
		{
			Game.gravflip3.bind();
		}
		else if(this.i>6*init&&this.i<=8*init)
		{
			Game.gravflip4.bind();
		}
		else if(this.i>8*init&&this.i<=10*init)
		{
			Game.gravflip5.bind();
		}
		else if(this.i>10*init&&this.i<=12*init)
		{
			Game.gravflip6.bind();
		}
		else if(this.i>12*init&&this.i<=14*init)
		{
			Game.gravflip7.bind();
		}
		else if(this.i>14*init&&this.i<=16*init)
		{
			Game.gravflip8.bind();
		}
		else if(this.i>16*init&&this.i<=18*init)
		{
			Game.gravflip9.bind();
		}
		else if(this.i>18*init&&this.i<=20*init)
		{
			Game.gravflip10.bind();
		}
		else if(this.i>20*init&&this.i<=22*init)
		{
			Game.gravflip11.bind();
		}
		else if(this.i>22*init&&this.i<=24*init)
		{
			Game.gravflip12.bind();
		}
		else if(this.i>24*init&&this.i<=26*init)
		{
			Game.gravflip13.bind();
		}
		else if(this.i>26*init&&this.i<=28*init)
		{
			Game.gravflip14.bind();
		}
		else if(this.i>28*init&&this.i<=30*init)
		{
			Game.gravflip15.bind();
		}
		else if(this.i>30*init&&this.i<=32*init)
		{
			Game.gravflip16.bind();
		}
		else if(this.i>32*init&&this.i<=34*init)
		{
			Game.gravflip17.bind();
		}
		else if(this.i>34*init&&this.i<=36*init)
		{
			Game.gravflip18.bind();
			if(this.i==36*init){
				this.i=0;
			}
		}
		this.i++;
		
		textureVertices();
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
	}

	@Override
	public Shape load(ObjectInputStream IS)
	{
		return null;
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

}
