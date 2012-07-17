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
	
	// for convenience, dependent on the above constants
	int TOP = (EDITOR_RESOLUTION_Y - GAME_RESOLUTION_Y)/2 - 1;
	int BOTTOM = (EDITOR_RESOLUTION_Y + GAME_RESOLUTION_Y)/2 + 1;
	int LEFT = (EDITOR_RESOLUTION_X - GAME_RESOLUTION_X)/2 - 1;
	int RIGHT = (EDITOR_RESOLUTION_X + GAME_RESOLUTION_X)/2 - 1;

	// not final, so you can change within the editor
	int FONT_SIZE = 24;		
	int GRID_SIZE = 50;
	int CAMERA_SCROLL_SPEED = 5;

	
	int transX, transY, mouseX, mouseY, width = 100, height = 100;
	float startX, startY;
	int [] Keys = new int[20];		// make an array to hold the keys for controls
	
	boolean drawGrid = true;
	
	private String currShape = "Box", inputValue;
	private Shape selected;
	
	private List<Shape> shapes = new ArrayList<Shape>(20);
	
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
		left = loadTexture("bag");
		
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
		return selected;
	}
	
	private void render()
	{	 			
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
		
		// end level

		drawBoundary();
		glPopMatrix();

		drawButtons();
		
		if (drawGrid)
			drawGrid();
		
	}
	
	private void assignPic(Shape temp)
	{
		if (temp.name.equals("Box"))
			temp.setPic(left);
		if (temp.name.equals("Arrow"))
			temp.setPic(a1);
	}
	
	private void input()
	{
		if (Mouse.isButtonDown(0))
		{
			Shape temp = new Box(mouseX, mouseY, width, height);	// just start it as a box, then change it
			if (currShape == "Box")
				temp = new Box(mouseX, mouseY, width, height);
			if (currShape == "Arrow")
				temp = new Arrow(mouseX, mouseY, width, height);
			
			
			assignPic(temp);
			shapes.add(temp);
			
			fixMouse();
			
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
			if (Keyboard.isKeyDown(Keyboard.KEY_X))
				currShape = "Arrow";
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
		int buttonLeft = 0;
		int buttonBOTTOM = EDITOR_RESOLUTION_Y;
		int buttonWidth = 200;
		int buttonHeight = 50;
		
		glColor4f(1f, 1f, 1f, 1f);
	
		glBegin(GL_LINE_LOOP);
			glVertex2f(buttonLeft, buttonBOTTOM - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBOTTOM - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBOTTOM);
			glVertex2f(buttonLeft, buttonBOTTOM);
		glEnd();
		
		buttonLeft += buttonWidth;
		
		glBegin(GL_LINE_LOOP);
			glVertex2f(buttonLeft, buttonBOTTOM - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBOTTOM - buttonHeight);
			glVertex2f(buttonLeft + buttonWidth, buttonBOTTOM);
			glVertex2f(buttonLeft, buttonBOTTOM);
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
		
		
		uniFont.drawString(5, 5, "Mouse: " + (mouseX + transX) + "," + (mouseY + transY));
		uniFont.drawString(5, 5 + FONT_SIZE, "Game x,y: " + (mouseX - LEFT - 1) + "," + (mouseY - TOP - 2));
		uniFont.drawString(0, EDITOR_RESOLUTION_Y - 25, "Button!");
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
					shape.save(OS);
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
