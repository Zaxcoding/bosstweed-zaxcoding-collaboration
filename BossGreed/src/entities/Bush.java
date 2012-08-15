package entities;

import game.GameOn;

<<<<<<< HEAD
public class Bush extends Shape {

	public Bush(double x, double y, double width, double height) {
		super(x, y, width, height);
		code=27;
		name = "Bush";
		defaultWidth=32;
		defaultHeight=32;
	}

	@Override
	public void interact(Box player) {
=======
public class Bush extends Shape
{

	public Bush(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 27;
		name = "Bush";
		defaultWidth = 32;
		defaultHeight = 32;
	}

	@Override
	public void interact(Box player)
	{
>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		// do nothing

	}

	@Override
<<<<<<< HEAD
	public void draw() {
		textureStart();
		
=======
	public void draw()
	{
		textureStart();

>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		GameOn.desertbush.bind();

		textureVertices();

	}

	@Override
<<<<<<< HEAD
	public boolean intersects(Shape other) {
=======
	public boolean intersects(Shape other)
	{
>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		// TODO Auto-generated method stub
		return false;
	}

}
