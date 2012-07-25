package entities;

import game.Game;

public class Coin extends Shape
{	
	public Coin(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 7;
		name = "Coin";
		defaultWidth = 16;
		defaultHeight = 32;
		partner = this;
		action = 2;	// disappear
	}

	@Override
	public void draw()
	{
		textureStart();
		
		Game.coini.bind();
		
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
		if (!this.removeMe)
		{
			player.goldCount++;
			this.removeMe = true;
		}
	}
}
