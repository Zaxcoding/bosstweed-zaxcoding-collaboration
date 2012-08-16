package entities;

import game.GameShell;

public class Map extends Shape {

	public Map(double x, double y, double width, double height) {
		super(x, y, width, height);
		code = 27;
		name = "Map";
		
	}

	@Override
	public void interact(Box player) {
		//do nothing

	}

	@Override
	public void draw() {
		textureStart();

		GameShell.map.bind();

		textureVertices();

	}

	@Override
	public boolean intersects(Shape other) {
		
		return false;
	}

}
