package entities;

import static org.lwjgl.opengl.GL11.glColor3d;

public class Sky extends Shape
{
	public Sky(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 19;
		name = "Sky";
	}

	@Override
	public void draw()
	{
		glColor3d(.6,.9,.9);

		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
