package entities;

import game.GameShell;

public class Grav extends Shape
{
	int init;

	public Grav(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 11;
		name = "Grav";
		init = 5;
		defaultWidth = 80;
		defaultHeight = 12;
		solid = true;
	}

	@Override
	public void draw()
	{
		textureStart();

		if (this.i <= 2 * init)
		{
			GameShell.gravflip.bind();
		} else if (this.i > 2 * init && this.i <= 4 * init)
		{
			GameShell.gravflip2.bind();
		} else if (this.i > 4 * init && this.i <= 6 * init)
		{
			GameShell.gravflip3.bind();
		} else if (this.i > 6 * init && this.i <= 8 * init)
		{
			GameShell.gravflip4.bind();
		} else if (this.i > 8 * init && this.i <= 10 * init)
		{
			GameShell.gravflip5.bind();
		} else if (this.i > 10 * init && this.i <= 12 * init)
		{
			GameShell.gravflip6.bind();
		} else if (this.i > 12 * init && this.i <= 14 * init)
		{
			GameShell.gravflip7.bind();
		} else if (this.i > 14 * init && this.i <= 16 * init)
		{
			GameShell.gravflip8.bind();
		} else if (this.i > 16 * init && this.i <= 18 * init)
		{
			GameShell.gravflip9.bind();
		} else if (this.i > 18 * init && this.i <= 20 * init)
		{
			GameShell.gravflip10.bind();
		} else if (this.i > 20 * init && this.i <= 22 * init)
		{
			GameShell.gravflip11.bind();
		} else if (this.i > 22 * init && this.i <= 24 * init)
		{
			GameShell.gravflip12.bind();
		} else if (this.i > 24 * init && this.i <= 26 * init)
		{
			GameShell.gravflip13.bind();
		} else if (this.i > 26 * init && this.i <= 28 * init)
		{
			GameShell.gravflip14.bind();
		} else if (this.i > 28 * init && this.i <= 30 * init)
		{
			GameShell.gravflip15.bind();
		} else if (this.i > 30 * init && this.i <= 32 * init)
		{
			GameShell.gravflip16.bind();
		} else if (this.i > 32 * init && this.i <= 34 * init)
		{
			GameShell.gravflip17.bind();
		} else if (this.i > 34 * init && this.i <= 36 * init)
		{
			GameShell.gravflip18.bind();
			if (this.i == 36 * init)
			{
				this.i = 0;
			}
		}
		this.i++;

		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		if ((this.getX() + this.getWidth()) >= other.getX()
				&& (this.getX() + this.getWidth()) <= (other.getX() + other
						.getWidth())
				&& (this.getY() + this.getHeight()) >= other.getY()
				&& (this.getY() + this.getHeight()) <= (other.getY() + other
						.getHeight()))
		{
			intersect = true;

		} else if ((this.getX() + this.getWidth()) >= other.getX()
				&& (this.getX() + this.getWidth()) <= (other.getX() + other
						.getWidth()) && this.getY() >= other.getY()
				&& this.getY() <= (other.getY() + other.getHeight()))
		{
			intersect = true;

		} else if (this.getX() >= other.getX()
				&& this.getX() <= (other.getX() + other.getWidth())
				&& (this.getY() + this.getHeight()) >= other.getY()
				&& (this.getY() + this.getHeight()) <= (other.getY() + other
						.getHeight()))
		{
			intersect = true;

		} else if (this.getX() >= other.getX()
				&& this.getX() <= other.getX() + other.getWidth()
				&& this.getY() >= other.getY()
				&& this.getY() <= (other.getY() + other.getHeight()))
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
