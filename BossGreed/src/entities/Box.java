package entities;

import game.GameShell;

public class Box extends Shape
{
	public int goldCount = 0;
	public boolean jumping, onIce = false, grounded = false, bounce = false;
	public double startX, startY;
	public int gravityMod = 1;						// gravityMod = 1 for normal, -1 for reverse
	public int lastDIR = 1;

	public Shape groundPiece;

	public Box(double x, double y, double width, double height)
	{
		super(x, y, width, height);
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

		if (gravityMod == 1)
		{
			if (lastDIR == -1)
				GameShell.left.bind();
			else if (lastDIR == 1)
				GameShell.right.bind();
		} else
		{
			if (lastDIR == -1)
				GameShell.gleft.bind();
			else if (lastDIR == 1)
				GameShell.gright.bind();
		}
		textureVertices();
	}

	public boolean intersects(Shape other)
	{
		boolean intersect = false;
		double p1y = this.getY() + 4;
		double p1x = this.getX() + 4;
		double p2x = this.getX() + this.getWidth() - 4;
		double p2y = this.getY() + this.getHeight() - 4;
		double p3y = other.getY();
		double p3x = other.getX();
		double p4x = other.getX() + other.getWidth();
		double p4y = other.getY() + other.getHeight();

		if (p2y < p3y || p1y > p4y || p2x < p3x || p1x > p4x)
		{

		} else
		{
			intersect = true;
		}

		/*
		 * if((this.getX()+this.getWidth())>=
		 * other.getX()&&(this.getX()+this.getWidth
		 * ())<=(other.getX()+other.getWidth
		 * ())&&(this.getY()+this.getHeight())>=
		 * other.getY()&&(this.getY()+this.getHeight
		 * ())<=(other.getY()+other.getHeight())) { intersect = true;
		 * 
		 * } else if((this.getX()+this.getWidth())>=
		 * other.getX()&&(this.getX()+this
		 * .getWidth())<=(other.getX()+other.getWidth
		 * ())&&this.getY()>=other.getY
		 * ()&&this.getY()<=(other.getY()+other.getHeight())) { intersect =
		 * true;
		 * 
		 * } else if(this.getX()>=
		 * other.getX()&&this.getX()<=(other.getX()+other
		 * .getWidth())&&(this.getY
		 * ()+this.getHeight())>=other.getY()&&(this.getY
		 * ()+this.getHeight())<=(other.getY()+other.getHeight())) { intersect =
		 * true;
		 * 
		 * } else if(this.getX()>=
		 * other.getX()&&this.getX()<=other.getX()+other.
		 * getWidth()&&this.getY()>=
		 * other.getY()&&this.getY()<=(other.getY()+other.getHeight())) {
		 * intersect = true;
		 * 
		 * }
		 */
		if (intersect && other.visible)
		{
			if (other.partner != null)
				if (other.name.equals("Grav") && other.width < 15
						& other.height < 15)
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
			if (other.name.equals("Bat"))
				if (this.x > other.x
						&& this.x + this.width < other.x + other.width)
					intersect = false;
		}

		return intersect;
	}

	@Override
	public void interact(Box player)
	{
		// nothing
	}

}
