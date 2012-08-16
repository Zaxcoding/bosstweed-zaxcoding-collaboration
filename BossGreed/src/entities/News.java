package entities;

import game.GameShell;

public class News extends Shape
{
	public News(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 17;
		name = "News";
		alive = true;
		defaultWidth = 40;
		defaultHeight = 40;
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();

		GameShell.news.bind();

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
		this.alive = false;
	}

}
