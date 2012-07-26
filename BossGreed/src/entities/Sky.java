package entities;

import static org.lwjgl.opengl.GL11.*;

public class Sky extends Shape
{
	double red, green, blue;
	
	public Sky(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 19;
		name = "Sky";
		red = .6;
		green = .9;
		blue = .9;
	}

	public void setRGB(double r, double g, double b)
	{
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public void draw()
	{		
		textureVertices();
		glColor4d(red,green,blue,1);
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
