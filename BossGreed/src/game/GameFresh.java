package game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import entities.*;
import editor.Level;

public class GameFresh
{	
	public static final int WIDTH = 640;			// width of the level editor screen
	public static final int HEIGHT = 480;			// height of the level editor screen
	public static final int MOVEMENT_AMOUNT = 5;	// for moving left, right, and translating
	public static final int COLLISION_HELP_2 = 15;	// for help landing from great heights
	
	private float translateX = 50, translateY = 0;
	private float startX, startY;
	private int gravityMod = 1;	// gravityMod = 1 for normal, -1 for reverse
	private long lastFrame;	
		
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	
	private long currTime;
	
	private Box box;
	
	boolean grounded;
	
	public GameFresh()
	{		
		loadLevel();
	//	initFonts();
		initGL();
		
		currTime = getTime();
	
		while (!Display.isCloseRequested())
		{			
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();
					
			glTranslatef(translateX, 0, 0);
			glTranslatef(0, translateY, 0);

			
			input();
			grounded = onGround();
						
			update();
			render();
						
			glPopMatrix();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}	
	
	public void input()
	{
		// W or up arrow to jump
		if ((Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) && grounded)
		{
//			box.jump();
			currTime = getTime();
		}
		// move the box and the camera left
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			moveLeft(box);
		// move the box and the camera right
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			moveRight(box);
		// reset the box's position, the camera, and the gravity
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			restart();
	}
	
	public boolean onGround()
	{
		// if we're already on the ground, then just check that we're still on top of the groundPiece
		// note the extra 3/4 width cushion for the box (to make it more fun)
		if (box.groundPiece != null)
		{
			if ((box.groundPiece.x - box.width < box.x && 
					(box.groundPiece.x + box.groundPiece.width > box.x)))
			{
	//			box.groundPiece.interact(box);
				return true;
			}
		}
		
		// if we're not on the ground, time to see if we're on the ground somewhere else
		for (Shape shape : shapes)
		{				
			if ((shape.x < box.x + box.width*1/4  && 
				(shape.x + shape.width > box.x + box.width*3/4)) &&	
					(shape.y + shape.height + COLLISION_HELP_2 > box.y + box.height) &&
						 (box.y + box.height > shape.y - 1) &&
						 	!shape.user && !box.jumping)
			{	
					box.y = shape.y - box.height;
	//				shape.interact(box);
					box.groundPiece = shape;
					return true;
			}
		}
		return false;
	}
	
	public void restart()
	{
		box.x = startX;
		box.y = startY + box.height;
		box.dy = 0;
		box.jumping = false;
		translateX = 0;
		translateY = 0;
	}
	
	public void moveLeft(Box box)
	{
	//	if (!box.onIce)
			box.x -= MOVEMENT_AMOUNT;
	}
	
	public void moveRight(Box box)
	{
	//	if (!box.onIce)
			box.x += MOVEMENT_AMOUNT;
	}
	
	public void render()
	{			
		// draw the placed boxes
		for (Shape shape : shapes)
			if (shape.isVisible())
				shape.draw();
	}
	
	public void update()
	{
		// remove the Shapes that say removeMe
		Shape temp = new Box(0,0,0,0);
		for (Shape shape : shapes)
		{
			if (shape.removeMe)
				temp = shape;
		}
		if (temp.removeMe)
			shapes.remove(temp);
		if (box.y > 5000)
		{
			box.alive = false;
			currTime = getTime();
		}		
	}
		
	private long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private int getDelta()
	{
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Game!");
			Display.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (WIDTH, HEIGHT) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void loadLevel()
	{
		String filename = JOptionPane.showInputDialog("Which level would you like to play?");

		System.out.println("Loading...");
		
		load(shapes, filename);	
		shapes.add(box);
	}
	
	public void load(List<Shape> shapes, String filename)
	{
		if (filename == null)
			System.exit(0);
		try
		{
			// clear the array
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(filename));
			int size = IS.readInt();
			
			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();
				Shape temp = Shape.load(IS, code);
				shapes.add(temp);
			}
			// the last two floats in the file are the start position
			startX = IS.readFloat();
			startY = IS.readFloat();
			
			box = new Box(startX, startY, 26, 26);
			
			IS.close();
			System.out.println("Loaded!");
		} catch (FileNotFoundException e)
		{
			System.out.println("Didn't find that level.");
			loadLevel();
		} catch (IOException e)
		{
			System.out.println(e);
		}
	}
	
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN,55);
        
        uniFont = new UnicodeFont(awtFont, 45, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};
    }
	
	public static void main(String [] args)
	{
		new GameFresh();
	}
	
}
