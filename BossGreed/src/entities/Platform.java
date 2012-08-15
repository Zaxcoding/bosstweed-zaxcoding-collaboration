package entities;

import game.GameOn;

<<<<<<< HEAD
public class Platform extends Shape {
	int disappearCounter;
	public Platform(double x, double y, double width, double height) {
		super(x, y, width, height);
		code =25;
=======
public class Platform extends Shape
{
	int disappearCounter;

	public Platform(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 25;
>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		name = "Platform";
		defaultWidth = 256;
		defaultHeight = 64;
		solid = true;
	}

	@Override
<<<<<<< HEAD
	public void interact(Box player) {
=======
	public void interact(Box player)
	{
>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		//disappearCounter++;

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
		GameOn.desertplatform.bind();

		textureVertices();

	}

	@Override
<<<<<<< HEAD
	public boolean intersects(Shape other) {
		
=======
	public boolean intersects(Shape other)
	{

>>>>>>> d11c89e14c50b3b44bef1eb885706da4a4e17caa
		return false;
	}

}
