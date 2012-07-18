package entities;

import game.Game;

public class Wheel extends Shape
{	
	public Wheel(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 23;
		name = "Wheel";
		switch1 = true;
	}

	@Override
	public void draw()
	{
		textureStart();
	/*
		if(ledge.getY()<265){
			switch1= true;
		}
		else if(ledge.getY()>265 &&ledge.getY()<275){
			switch1 = false;
		}
		else if(ledge.getY()>275 &&ledge.getY()<285){
			switch1 = true;
		}
		else if(ledge.getY()>285 &&ledge.getY()<295){
			switch1 = false;
		}
		else if(ledge.getY()>295 &&ledge.getY()<305){
			switch1 = true;
		}
		else if(ledge.getY()>305 &&ledge.getY()<315){
			switch1 = false;
		}
		else if(ledge.getY()>315 &&ledge.getY()<325){
			switch1 = true;
		}
		else if(ledge.getY()>325 &&ledge.getY()<335){
			switch1 = false;
		}
		else if(ledge.getY()>335 &&ledge.getY()<345){
			switch1 = true;
		}
		else if(ledge.getY()>345 &&ledge.getY()<355){
			switch1 = false;
		}
		else if(ledge.getY()>355 &&ledge.getY()<365){
			switch1 = true;
		}
		else if(ledge.getY()>365 &&ledge.getY()<375){
			switch1 = false;
		}
	*/
		if(switch1){
			Game.wheeli.bind();
		}
		else{
			Game.wheeli2.bind();
		}
		
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
