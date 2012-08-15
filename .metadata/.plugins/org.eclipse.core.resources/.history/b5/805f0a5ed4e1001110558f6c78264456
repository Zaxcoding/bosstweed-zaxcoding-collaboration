package entities;

import game.GameOn;

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
		// do nothing

	}

	@Override
	public void draw() {
		textureStart();
		
		GameOn.desertbush.bind();

		textureVertices();

	}

	@Override
	public boolean intersects(Shape other) {
		// TODO Auto-generated method stub
		return false;
	}

}
