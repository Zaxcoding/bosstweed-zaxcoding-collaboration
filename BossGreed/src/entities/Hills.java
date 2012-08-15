package entities;

import game.GameOn;

<<<<<<< HEAD
public class Hills extends Shape {

	public Hills(double x, double y, double width, double height) {
		super(x, y, width, height);
		code =26;
		name ="Hills";
		defaultWidth=1024;
		defaultHeight=512;
	}

	@Override
	public void interact(Box player) {
=======
public class Hills extends Shape
{

	public Hills(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 26;
		name = "Hills";
		defaultWidth = 1024;
		defaultHeight = 512;
	}

	@Override
	public void interact(Box player)
	{
>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		//do nothing

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
		GameOn.desertback.bind();

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
