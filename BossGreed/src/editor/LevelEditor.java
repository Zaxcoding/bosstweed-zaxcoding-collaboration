package editor;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import entities.*;

public class LevelEditor
{
	protected static final int EDITOR_RESOLUTION_X = 1280;			// width of the level editor screen
	protected static final int EDITOR_RESOLUTION_Y = 800;			// height of the level editor screen
	protected final static int GAME_RESOLUTION_X = 640;		// game dimensions
	protected final static int GAME_RESOLUTION_Y = 480;		// of BossGreed
	public static final int THICKNESS = 1;					// the thickness you adjust by
	public static final int MAX_GRID_SIZE = 200;			// maximum size for the grid
	public static final int MIN_GRID_SIZE = 10;				// minimum size for the grid
	
	
	// for convenience, dependent on the above constants
	int TOP = (EDITOR_RESOLUTION_Y - GAME_RESOLUTION_Y)/2 - 1 - 70;
	int BOTTOM = (EDITOR_RESOLUTION_Y + GAME_RESOLUTION_Y)/2 + 1 - 70;
	int LEFT = (EDITOR_RESOLUTION_X - GAME_RESOLUTION_X)/2 - 1;
	int RIGHT = (EDITOR_RESOLUTION_X + GAME_RESOLUTION_X)/2 - 1;

	// not final, so you can change within the editor
	int FONT_SIZE = 24;		
	int GRID_SIZE = 50;
	int CAMERA_SCROLL_SPEED = 5;

	
	int transX, transY, mouseX, mouseY, width = 26, height = 26;
	float startX, startY;
	int [] Keys = new int[20];		// make an array to hold the keys for controls
	
	int picsX = 50, picsY = BOTTOM + 50, picsW = 50, picsH = 50;		// for the bottom pics grid
	int buttonCode = 1;				// used to choose which instance int to change
	int pointerX, pointerY;			// for the ^ used to show the current piece
	
	
	boolean drawGrid = true;
	
	private String currShape = "Box", inputValue;
	private Shape selected, current = new Box(0,0,0,0);
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private List<Shape> bottomShapes = new ArrayList<Shape>(20);
	
	
	UnicodeFont uniFont;

	public static Texture left,right,gright,gleft,icel,cliffi,cliffv,icev,deadi,deadi1,deadi2,deadi3,deadi4,deadv,deadv1,deadv2,deadv3,deadv4,coini,door,doorv,gravflip,gravflip2,gravflip3,gravflip4,gravflip5,gravflip6,gravflip7,gravflip8,gravflip9,gravflip10,gravflip11,gravflip12,gravflip13,gravflip14,gravflip15,gravflip16,gravflip17,gravflip18,Lon,Loff,brick,brickv,wallpaper;
	public static Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10,lboxi,doorjam,doorjamv,woodi,ledgei,ropei,hangi,hangv,wheeli,wheeli2,sky1,sky2,sky3,sky4,sky5,sky6,a1,a2,a3,a4,a5,esc,space,words,words2,words3,words4,words5,words6,words7,words8,words9,words10,words11,words12,words13,words14,words15,words16,words17,words18,words19,words20,words21,words22;
	public static Texture p,pr,pre,pres,press,news;
	
	public LevelEditor() 
	{
		initGL();
		initFonts();
		initKeys();
		initTextures();
		
		assignPic(current);	
		drawShapes();
		
		while (!Display.isCloseRequested()) 
		{			
			glClear(GL_COLOR_BUFFER_BIT);	// wipe the screen
			
			mouse();

			input();
			render();
			drawText();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}
	
	public void initTextures()
	{
		right = loadTexture("bagi1");
		
		sky1 = loadTexture("skyline1");
		sky2 = loadTexture("skyline2");
		sky3 = loadTexture("skyline3");
		sky4 = loadTexture("skyline4");
		sky5 = loadTexture("skyline5");
		sky6 = loadTexture("skyline6");
		icel = loadTexture("air");
		cliffi = loadTexture("cliff");
		cliffv = loadTexture("cliff2");
		icev = loadTexture("air1");
		deadi = loadTexture("dead");
		coini = loadTexture("coin2");
		gravflip = loadTexture("gravflip1");
		gravflip2 = loadTexture("gravflip2");
		gravflip3 = loadTexture("gravflip3");
		gravflip4 = loadTexture("gravflip4");
		gravflip5 = loadTexture("gravflip5");
		gravflip6 = loadTexture("gravflip6");
		gravflip7 = loadTexture("gravflip7");
		gravflip8 = loadTexture("gravflip8");
		gravflip9 = loadTexture("gravflip9");
		gravflip10 = loadTexture("gravflip10");
		gravflip11 = loadTexture("gravflip11");
		gravflip12 = loadTexture("gravflip12");
		gravflip13 = loadTexture("gravflip13");
		gravflip14 = loadTexture("gravflip14");
		gravflip15 = loadTexture("gravflip15");
		gravflip16 = loadTexture("gravflip16");
		gravflip17 = loadTexture("gravflip17");
		gravflip18 = loadTexture("gravflip18");
		Lon = loadTexture("lighton");
		Loff = loadTexture("lightoff");
		//ski = loadTexture("sky");
		cloud1 =loadTexture("cloud1");
		cloud2 =loadTexture("cloud2");
		cloud3 =loadTexture("cloud3");
		cloud4 =loadTexture("cloud4");
		cloud5 =loadTexture("cloud5");
		cloud6 =loadTexture("cloud6");
		cloud7 =loadTexture("cloud7");
		cloud8 =loadTexture("cloud8");
		cloud9 =loadTexture("cloud9");
		cloud10 =loadTexture("cloud10");
		lboxi = loadTexture("lbox");
		doorjam = loadTexture("doorjam");
		woodi = loadTexture("wood");
		brick = loadTexture("brick");
		brickv = loadTexture("brickv");
		a1 = loadTexture("arrow");
		a2 = loadTexture("arrow1");
		words = loadTexture("words");
		words2 = loadTexture("wordsLevel");
		words3 = loadTexture("wordsAbout");
		words4 = loadTexture("wordsExit");
		words5 = loadTexture("wordsControls");
		words6 = loadTexture("wordsMove");
		words7 = loadTexture("wordsJump");
		words8 = loadTexture("wordsMainMenu");
		words9 = loadTexture("level1");
		words10 = loadTexture("level2");
		words11 = loadTexture("wordsGameOver");
		words12 = loadTexture("wordsRestart");
		words13 = loadTexture("wordsYouWon");
		words14 = loadTexture("wordsWelcome");
		words15 = loadTexture("wordsIntroLevel");
		words16 = loadTexture("wordsto");
		words17 = loadTexture("wordsAvoidFire");
		words18 = loadTexture("wordsBossGreed");
		words19 = loadTexture("wordsSlidesonIce");
		words20 = loadTexture("wordsGravityFlipper");
		words21 = loadTexture("wordsCoinsAddWeightto");
		words22 = loadTexture("wordsAvoidspike");
		wallpaper = loadTexture("bgwallpaper3");
		p = loadTexture("wordsP");
		pr = loadTexture("wordsPr");
		pre = loadTexture("wordsPre");
		pres = loadTexture("wordsPres");
		press = loadTexture("wordsPress");
		news = loadTexture("news");
		
		
		a3 = loadTexture("arrowup");
		a4 = loadTexture("arrowleft");
		a5 = loadTexture("arrowright");
		esc = loadTexture("esc");
		space = loadTexture("spacebar");
		
		deadi1 = loadTexture("deadi1");
		deadi2 = loadTexture("deadi2");
		deadi3 = loadTexture("deadi3");
		deadi4 = loadTexture("deadi4");
		deadv = loadTexture("deadv");
		deadv1 = loadTexture("deadv1");
		deadv2 = loadTexture("deadv2");
		deadv3 = loadTexture("deadv3");
		deadv4 = loadTexture("deadv4");
		door = loadTexture("door");
		doorv = loadTexture("doorv");
		ropei = loadTexture("rope");
		hangi = loadTexture("hang");
		doorjamv = loadTexture("doorjamv");
		hangv = loadTexture("hangv");
		ledgei = loadTexture("ledge");
		wheeli = loadTexture("wheel");
		wheeli2 = loadTexture("wheel1");
		
	}
	
	// for selecting a shape
	public Shape getShape()
	{
		if (mouseY + transY >= TOP && mouseY + transY <= BOTTOM)
		{
			for (Shape shape: shapes)
			{
				if (mouseX >= shape.getX() && (mouseX <= shape.getX() + shape.getWidth())
						&& mouseY >= shape.getY() && (mouseY <= shape.getY() + shape.getHeight()))
				{
					selected = shape;
					shape.selected = true;
				}
				else
				{
					shape.selected = false;
				}
			}
		}	
		if (mouseY + transY >= BOTTOM)
		{
			for (Shape shape: bottomShapes)
			{
				if (mouseX + transX >= shape.getX() && (mouseX + transX <= shape.getX() + shape.getWidth())
						&& mouseY + transY >= shape.getY() && (mouseY + transY <= shape.getY() + shape.getHeight()))
				{
					currShape = shape.name;
					current = getCurrShape();
					if (current.name.equals("Cloud"))
						current.type = 1;
					assignPic(current);
					selected = current;
					pointerX = (int) (shape.getX() + (shape.getWidth() - FONT_SIZE)/2) + 5;
					pointerY = (int) (shape.getY() + (shape.getHeight() + FONT_SIZE)/2) + 5;
					
				}
			}
		}
		return selected;
	}
	
	
	private void render()
	{	 		
		current.setPosition(mouseX, mouseY);
		current.setWidth(width);
		current.setHeight(height);
		assignPic(current);
		
		// draw the game box
		glBegin(GL_LINE_LOOP);
			glVertex2f(LEFT, TOP);
			glVertex2f(RIGHT, TOP);
			glVertex2f(RIGHT, BOTTOM);
			glVertex2f(LEFT, BOTTOM);
		glEnd();
		
		translate();			
		
		// this is where the level itself goes
		
		for (Shape shape : shapes)
			shape.editorDraw();
		
		current.editorDraw();
		
		// end level

		drawBoundary();
		glPopMatrix();

		for (Shape shape : bottomShapes)
			shape.editorDraw();
		
		drawButtons(); 
		
		if (drawGrid)
			drawGrid();
		
	}
	
	private void assignPic(Shape temp)
	{
		// this is where the heavy lifting is done to
		// determine which picture to show
		
		if (temp.name.equals("Arrow"))
		{
			if (temp.i < 10)
				temp.setPic(a1);
			else if (temp.i >= 10 && temp.i < 20)
				temp.setPic(a2);
			else
				temp.i = 0;
		}
		
		if (temp.name.equals("ArrowKey"))
		{
			if(temp.type == 0)
				temp.setPic(a3);
			else if(temp.type == 1)
				temp.setPic(a4);
			else if(temp.type == 2)
				temp.setPic(a5);
			else if(temp.type == 3)
				temp.setPic(esc);
			else if(temp.type == 4)
				temp.setPic(space);
		}
		
		if (temp.name.equals("Bat"))
		{
			if (temp.vert)
				temp.setPic(cliffv);
			else
				temp.setPic(cliffi);
		}
		
		if (temp.name.equals("Box"))
			temp.setPic(right);
		
		if (temp.name.equals("Brick"))
		{
			if (temp.up)
				temp.setPic(brickv);
			else
				temp.setPic(brick);
		}
		
		if (temp.name.equals("Cloud"))
		{
			if (temp.type == 1)
				temp.setPic(cloud1);
			else if(temp.type == 2)
				temp.setPic(cloud2);
			else if(temp.type == 3)
				temp.setPic(cloud3);
			else if(temp.type == 4)
				temp.setPic(cloud4);
			else if(temp.type == 5)
				temp.setPic(cloud5);
			else if(temp.type == 6)
				temp.setPic(cloud6);
			else if(temp.type == 7)
				temp.setPic(cloud7);
			else if(temp.type == 8)
				temp.setPic(cloud8);
			else if(temp.type == 9)
				temp.setPic(cloud9);
			else if(temp.type == 10)
				temp.setPic(cloud10);
		}
			
		if (temp.name.equals("Coin"))
			temp.setPic(coini);
		
		if (temp.name.equals("Dead"))
		{
			if (temp.vert)
			{
				if (temp.j <= 10)
					temp.setPic(deadv);
				else if (temp.j <= 20)
					temp.setPic(deadv1);
				else if (temp.j <= 30)
					temp.setPic(deadv2);
				else if (temp.j <= 40)
					temp.setPic(deadv3);
				else if (temp.j <= 50)
					temp.setPic(deadv4);
				if(temp.j == 50)
						temp.j = 0;
			}
			else
			{
				if (temp.j <= 10)
					temp.setPic(deadi);
				else if (temp.j <= 20)
					temp.setPic(deadi1);
				else if (temp.j <= 30)
					temp.setPic(deadi2);
				else if (temp.j <= 40)
					temp.setPic(deadi3);
				else if (temp.j <= 50)
					temp.setPic(deadi4);
				if(temp.j == 50)
						temp.j = 0;
			}
		}
		
		if (temp.name.equals("Doorjam"))
		{
			if (!temp.vert)
				temp.setPic(doorjam);
			else
				temp.setPic(doorjamv);
		}
		
		if (temp.name.equals("Gem"))
		{
			if (!temp.vert)
				temp.setPic(door);
			else
				temp.setPic(doorv);
		}
		
		if (temp.name.equals("Grav"))
		{
			temp.setPic(gravflip);
		}
		
		if (temp.name.equals("Hang"))
		{
			if (!temp.upp)
				temp.setPic(hangi);
			else
				temp.setPic(hangv);
		}
		
		if (temp.name.equals("Ice"))
		{
			if (temp.up)
				temp.setPic(icev);
			else
				temp.setPic(icel);
		}
		
		if (temp.name.equals("Lbox"))
			temp.setPic(lboxi);
		
		if (temp.name.equals("Ledge"))
			temp.setPic(ledgei);
		
		if (temp.name.equals("Loff"))
		{
			if (!temp.on)
				temp.setPic(Loff);
			else
				temp.setPic(Lon);
		}
		
		if (temp.name.equals("News"))
			temp.setPic(news);
		
		if (temp.name.equals("Rope"))
			temp.setPic(ropei);
		
		if (temp.name.equals("Skyline"))
		{
			if (temp.that == 0)
				temp.setPic(sky1);
			else if (temp.that == 1)
				temp.setPic(sky2);
			else if (temp.that == 2)
				temp.setPic(sky3);
			else if (temp.that == 3)
				temp.setPic(sky4);
			else if (temp.that == 4)
				temp.setPic(sky5);
			else if (temp.that == 5)
				temp.setPic(sky6);
		}
	
		if (temp.name.equals("Text"))
		{
			if (temp.type == 0)
				temp.setPic(words);
			else if (temp.type == 1)
				temp.setPic(words2);
			else if (temp.type == 2)
				temp.setPic(words3);
			else if (temp.type == 3)
				temp.setPic(words4);
			else if (temp.type == 4)
				temp.setPic(words5);
			else if (temp.type == 5)
				temp.setPic(words6);
			else if (temp.type == 6)
				temp.setPic(words7);
			else if (temp.type == 7)
				temp.setPic(words8);
			else if (temp.type == 8)
				temp.setPic(words9);
			else if (temp.type == 9)
				temp.setPic(words10);
			else if (temp.type == 10)
				temp.setPic(words11);
			else if (temp.type == 11)
				temp.setPic(words12);
			else if (temp.type == 12)
				temp.setPic(words13);
			else if (temp.type == 13)
				temp.setPic(words14);
			else if (temp.type == 14)
				temp.setPic(words15);
			else if (temp.type == 15)
				temp.setPic(words16);
			else if (temp.type == 16)
				temp.setPic(words17);
			else if (temp.type == 17)
				temp.setPic(words18);
			else if (temp.type == 18)
				temp.setPic(words19);
			else if (temp.type == 19)
				temp.setPic(words20);
			else if (temp.type == 20)
				temp.setPic(words21);
			else if (temp.type == 21)
				temp.setPic(words22);
		}
		
		if (temp.name.equals("Wall"))
			temp.setPic(wallpaper);
		
		if (temp.name.equals("Wheel"))
		{
			if (temp.switch1)
				temp.setPic(wheeli);
			else
				temp.setPic(wheeli2);
		}
	}
	
	public Shape getCurrShape()
	{
		Shape temp = new Box(0,0,0,0);
		
		if (currShape == "Arrow")
			temp = new Arrow(mouseX, mouseY, width, height);
		if (currShape == "ArrowKey")
			temp = new ArrowKey(mouseX, mouseY, width, height);
		if (currShape == "Bat")
			temp = new Bat(mouseX, mouseY, width, height);
		if (currShape == "Box")
			temp = new Box(mouseX, mouseY, width, height);
		if (currShape == "Brick")
			temp = new Brick(mouseX, mouseY, width, height);
		if (currShape == "Cloud")
			temp = new Cloud(mouseX, mouseY, width, height);
		if (currShape == "Coin")
			temp = new Coin(mouseX, mouseY, width, height);
		if (currShape == "Dead")
			temp = new Dead(mouseX, mouseY, width, height);
		if (currShape == "Doorjam")
			temp = new Doorjam(mouseX, mouseY, width, height);
		if (currShape == "Gem")
			temp = new Gem(mouseX, mouseY, width, height);
		if (currShape == "Grav")
			temp = new Grav(mouseX, mouseY, width, height);
		if (currShape == "Hang")
			temp = new Hang(mouseX, mouseY, width, height);
		if (currShape == "Ice")
			temp = new Ice(mouseX, mouseY, width, height);
		if (currShape == "Lbox")
			temp = new Lbox(mouseX, mouseY, width, height);
		if (currShape == "Ledge")
			temp = new Ledge(mouseX, mouseY, width, height);
		if (currShape == "Loff")
			temp = new Loff(mouseX, mouseY, width, height);
		if (currShape == "News")
			temp = new News(mouseX, mouseY, width, height);
		if (currShape == "Rope")
			temp = new Rope(mouseX, mouseY, width, height);
		if (currShape == "Sky")
			temp = new Sky(mouseX, mouseY, width, height);
		if (currShape == "Skyline")
			temp = new Skyline(mouseX, mouseY, width, height);
		if (currShape == "Text")
			temp = new Text(mouseX, mouseY, width, height);
		if (currShape == "Wall")
			temp = new Wall(mouseX, mouseY, width, height);
		if (currShape == "Wheel")
			temp = new Wheel(mouseX, mouseY, width, height);
		
		return temp;
	}
	
	public void drawShapes()
	{
		Shape temp = new Arrow(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new ArrowKey(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Bat(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Box(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Brick(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Cloud(picsX, picsY, picsW, picsH);
		temp.type = 1;
		drawAndMove(temp);
		temp = new Coin(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Dead(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Doorjam(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Gem(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Grav(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Hang(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Ice(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Lbox(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Ledge(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Loff(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new News(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Rope(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Skyline(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Text(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Wall(picsX, picsY, picsW, picsH);
		drawAndMove(temp);
		temp = new Wheel(picsX, picsY, picsW, picsH);
		drawAndMove(temp);	
	}
	
	public void drawAndMove(Shape temp)
	{
		assignPic(temp);
		bottomShapes.add(temp);
		
		picsX += picsW + 50;
		if (picsX >= EDITOR_RESOLUTION_X - 50)
		{
			picsX = 50;
			picsY += picsH + 50;
		}
	}
	
	private boolean mouseIn(int left, int right, int top, int bottom)
	{
		return mouseX + transX >= left && mouseX + transX <= right && mouseY + transY > top && mouseY + transY < bottom;
	}
	
	private void input()
	{
		if (Mouse.isButtonDown(0) && mouseIn(LEFT, RIGHT, TOP, BOTTOM))
		{	
			shapes.add(current);
			
			current = getCurrShape();
			if (current.name.equals("Cloud"))
				current.type = 1;
			assignPic(current);
			
			fixMouse();
		}
		
		// clicking the buttons. this is ugly because it can easily be screwed
		// up if the interface shifts around, but at least it's very easy to change
		// ----Start left side buttons
		
		// --- Ints
		if (Mouse.isButtonDown(0) && mouseIn(0, 50, 290, 310))
			buttonCode = 1;			// i
		if (Mouse.isButtonDown(0) && mouseIn(75, 125, 290, 310))
			buttonCode = 2;			// j
		if (Mouse.isButtonDown(0) && mouseIn(145, 230, 290, 310))
			buttonCode = 3;			// that
		if (Mouse.isButtonDown(0) && mouseIn(0, 95, 315, 335))
			buttonCode = 4;			// type
		if (Mouse.isButtonDown(0) && mouseIn(130, 205, 315, 335))
			buttonCode = 5;			// init
		
		// --- Booleans
		if (Mouse.isButtonDown(0) && mouseIn(0, 105, 355, 380))
		{
			current.up = !current.up;
			fixMouse();
		}
		if (Mouse.isButtonDown(0) && mouseIn(125, 245, 355, 380))
		{
			current.upp = !current.upp;
			fixMouse();
		}
		if (Mouse.isButtonDown(0) && mouseIn(0, 145, 385, 405))
		{
			current.pause = !current.pause;
			fixMouse();
		}	
		if (Mouse.isButtonDown(0) && mouseIn(160, 265, 385, 405))
		{
			current.on = !current.on;
			fixMouse();
		}
		if (Mouse.isButtonDown(0) && mouseIn(0, 120, 410, 430))
		{
			current.vert = !current.vert;
			fixMouse();
		}	
		if (Mouse.isButtonDown(0) && mouseIn(140, 265, 410, 430))
		{
			current.right = !current.right;
			fixMouse();
		}
		if (Mouse.isButtonDown(0) && mouseIn(0, 130, 435, 455))
		{
			current.alive = !current.alive;
			fixMouse();
		}	
		if (Mouse.isButtonDown(0) && mouseIn(145, 310, 435, 455))
		{
			current.switch1 = !current.switch1;
			fixMouse();
		}
		
		//-----End left side buttons
			
		if (Mouse.isButtonDown(0) && mouseY + transY >= BOTTOM + 40)
		{
			selected = getShape();
		}
		
		
		if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)
				|| Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA))
				&& Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			fixKeyboard();
			save(shapes);
		}
		else if ((Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)
				|| Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA))
				&& Keyboard.isKeyDown(Keyboard.KEY_O))
		{
			fixKeyboard();
			load(shapes);
			
			// reset the camera
			transX= 0;
			transY= 0;
		}
		else
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
				transY += CAMERA_SCROLL_SPEED;
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
				transY -= CAMERA_SCROLL_SPEED;
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
				transX += CAMERA_SCROLL_SPEED;
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) 
				transX -= CAMERA_SCROLL_SPEED;
			
			if (Keyboard.isKeyDown(Keyboard.KEY_I) && (height - THICKNESS) >= 1) 
				height -= THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_K)) 
				height += THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_L)) 
				width += THICKNESS;
			if (Keyboard.isKeyDown(Keyboard.KEY_J) && (width - THICKNESS) >= 3) 
				width -= THICKNESS;
			
			if (Keyboard.isKeyDown(Keyboard.KEY_COMMA) && GRID_SIZE > MIN_GRID_SIZE)
				GRID_SIZE--;
			if (Keyboard.isKeyDown(Keyboard.KEY_PERIOD) && GRID_SIZE < MAX_GRID_SIZE)
				GRID_SIZE++;
			
			
			if (Keyboard.isKeyDown(Keyboard.KEY_T))
			{
				drawGrid = !drawGrid;
				fixKeyboard();
			}
			
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RBRACKET))
			{
				if (buttonCode == 1)
					current.i++;
				if (buttonCode == 2)
					current.j++;
				if (buttonCode == 3)
					current.that++;
				if (buttonCode == 4)
					current.type++;
				if (buttonCode == 5)
					current.init++;
				
				fixKeyboard();
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LBRACKET))
			{
				if (buttonCode == 1)
					current.i--;
				if (buttonCode == 2)
					current.j--;
				if (buttonCode == 3)
					current.that--;
				if (buttonCode == 4)
					current.type--;
				if (buttonCode == 5)
					current.init--;
				
				fixKeyboard();
			}
		}
	}
	
	public void fixMouse()
	{
		Mouse.destroy();
		try
		{
			Mouse.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void fixKeyboard()
	{
		Keyboard.destroy();
		try
		{
			Keyboard.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void mouse()
	{
		// Retrieve the "true" coordinates of the mouse.
		mouseX = Mouse.getX() - transX;
		mouseY = EDITOR_RESOLUTION_Y - Mouse.getY() - 1 - transY;
	}

	private void drawButtons()
	{
		int buttonLeft = 40;
		int buttonWidth = 200;
		int buttonHeight = 50;
		
		glColor4f(1f, 1f, 1f, 1f);
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);
		
		buttonLeft += buttonWidth;
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);
		
		buttonLeft += buttonWidth;
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);
		
		buttonLeft += buttonWidth;
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);
		
		buttonLeft += buttonWidth;
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);
		
		buttonLeft += buttonWidth;
		
		makeButton(buttonLeft, buttonWidth, buttonHeight);

	}
	
	public void makeButton(int buttonLeft, int buttonWidth, int buttonHeight)
	{
		glBegin(GL_LINE_LOOP);
			glVertex2f(buttonLeft, 0);
			glVertex2f(buttonLeft + buttonWidth, 0);
			glVertex2f(buttonLeft + buttonWidth, buttonHeight);
			glVertex2f(buttonLeft, buttonHeight);
		glEnd();
	}
	
	private void drawBoundary()
	{
		// this draws a black frame around the screen size to create the 'window-in-window' illusion
		glColor4f(0f, 0f, 0f, 1f);
		glRectd(-transX, -transY - CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_X - transX, TOP - 1 - transY);	// TOP
		glRectd(-transX - CAMERA_SCROLL_SPEED, -transY, LEFT - 1 - transX, EDITOR_RESOLUTION_Y - transY);	// left
		glRectd(-transX, BOTTOM + 1 - transY, EDITOR_RESOLUTION_X - transX, EDITOR_RESOLUTION_Y - transY + CAMERA_SCROLL_SPEED);	// BOTTOM
		glRectd(RIGHT + 1 - transX, -transY, EDITOR_RESOLUTION_X - transX + CAMERA_SCROLL_SPEED, EDITOR_RESOLUTION_Y - transY);	// RIGHT
	}
	
	private void drawGrid()
	{
		// draw the grid
		glBegin(GL_LINES);
	
			glColor4f(1.0f, 1.0f, 1.0f, .75f);
			for (int i = 0; TOP + i <  BOTTOM; i += GRID_SIZE)
			{
				glVertex2f(LEFT, TOP + i);
				glVertex2f(RIGHT, TOP + i);
			}
			for (int i = 0; LEFT + i <  RIGHT; i += GRID_SIZE)
			{
				glVertex2f(LEFT + i, TOP);
				glVertex2f(LEFT + i, BOTTOM);
			}
			glDisable(GL_BLEND); 
		glEnd();
	}
	
	private void drawText()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
		
		
		uniFont.drawString(5, TOP, "Mouse: " + (mouseX + transX) + "," + (mouseY + transY));
		uniFont.drawString(5, TOP + FONT_SIZE, "Game x,y: " + (mouseX - LEFT - 1) + "," + (mouseY - TOP - 2));
		uniFont.drawString(5, TOP + 2*FONT_SIZE, "CurrShape: " + currShape);
		uniFont.drawString(5, TOP + 3*FONT_SIZE, "Width: " + width);
		uniFont.drawString(5, TOP + 4*FONT_SIZE, "Height: " + height);
		uniFont.drawString(5, TOP + 5*FONT_SIZE, "Grid size: " + GRID_SIZE);
		
		uniFont.drawString(5, TOP + 7*FONT_SIZE, "---Instance variables---");
		
		String iString = "i: ", jString = "j: ", thatString = "that: ", typeString = "type: ", initString = "init: ";
		
		if (buttonCode == 1)
			iString = "I= ";
		if (buttonCode == 2)
			jString = "J= ";
		if (buttonCode == 3)
			thatString = "THAT= ";
		if (buttonCode == 4)
			typeString = "TYPE= ";
		if (buttonCode == 5)
			initString = "INIT= ";
		
		uniFont.drawString(5, TOP + 8*FONT_SIZE, iString + current.i + "     " + jString + current.j + "    " + thatString + current.that);
		uniFont.drawString(5, TOP + 9*FONT_SIZE, typeString + current.type + "      " + initString + current.init);
		
		uniFont.drawString(5, TOP + 11*FONT_SIZE, "up: " + current.up + "   upp: " + current.upp);
		uniFont.drawString(5, TOP + 12*FONT_SIZE, "pause: " + current.pause + "   on: " + current.on);
		uniFont.drawString(5, TOP + 13*FONT_SIZE, "vert: " + current.vert + "   right: " + current.right);
		uniFont.drawString(5, TOP + 14*FONT_SIZE, "alive: " + current.alive + "   switch1: " + current.switch1);
		 
		if (pointerX > 0)
			uniFont.drawString(pointerX, pointerY, "^");
		
		uniFont.drawString(55, 10, "Button!");
		// more text here
		
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);        
		glDisable(GL_BLEND); 
	}
	
	public void load(List<Shape> shapes)
	{
		inputValue = JOptionPane.showInputDialog("Enter the filename to load please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
			try
			{
				shapes.clear();
				ObjectInputStream IS = new ObjectInputStream(new FileInputStream(inputValue));
				int size = IS.readInt();
				for (int i = 0; i < size; i++)
				{
					int code = IS.readInt();
					Shape temp = Shape.load(IS, code);
					assignPic(temp);
					shapes.add(temp);
				}
				startX = IS.readFloat();
				startY = IS.readFloat();
				transX = 0;
				transY = 0;
				
				System.out.println("Loaded!");
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void save(List<Shape> shapes)
	{
		inputValue = JOptionPane.showInputDialog("Enter the desired filename please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
			try
			{
				ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(inputValue));
				OS.writeInt(shapes.size());
				for (Shape shape : shapes)
				{
					Shape.save(OS, shape);
				}
				OS.writeFloat(startX);
				OS.writeFloat(startY);
				
				OS.close();
				System.out.println("Saved!");
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void translate()
	{		
		glPushMatrix();
		glTranslatef(transX, 0, 0);
		glTranslatef(0, transY, 0);
	}
	
	private void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(EDITOR_RESOLUTION_X, EDITOR_RESOLUTION_Y));
			Display.setTitle("Level Editor");
			Display.create();
		} catch (LWJGLException e) 
		{
			e.printStackTrace();	
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, EDITOR_RESOLUTION_X, EDITOR_RESOLUTION_Y, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
	}
	
	@SuppressWarnings("unchecked")
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN,55);
       
        uniFont = new UnicodeFont(awtFont, FONT_SIZE, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};
    }
	
	private void initKeys()
	{
		// set the default control scheme
	}
	
	public static Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("png",new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		new LevelEditor();
	}
	
}
