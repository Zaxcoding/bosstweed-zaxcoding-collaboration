package editor;

import java.util.ArrayList;
import java.util.List;

import entities.*;

public class Level 
{
	public String name;
	public int id;
	
	public int totalCoins;
	public Sky background;
	
	public List<Shape> shapes = new ArrayList<Shape>(20);
	
	public Level(String name, List<Shape> shapes)
	{
		this.shapes = shapes;
		this.name = name;
	}
	
}
