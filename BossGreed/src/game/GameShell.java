package game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
<<<<<<< HEAD
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glPopMatrix;
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import entities.Arrow;
import entities.ArrowKey;
<<<<<<< HEAD
import entities.Box;
import entities.Map;
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2
import entities.Text;
import entities.Wall;

public class GameShell
{

	static final int WIDTH = 640; //width of the game window
	static final int HEIGHT = 480; //height of the game window
	static final int INTRO_TIME = 2000; //time for the intro animation
	private long startTime, endTime;
	public static UnicodeFont uniFont;
	private final int FONT_SIZE = 18;

	public static Texture left, right, gright, gleft, icel, cliffi, cliffv,
			icev, deadi, deadi1, deadi2, deadi3, deadi4, deadv, deadv1, deadv2,
			deadv3, deadv4, coini, door, doorv, gravflip, gravflip2, gravflip3,
			gravflip4, gravflip5, gravflip6, gravflip7, gravflip8, gravflip9,
			gravflip10, gravflip11, gravflip12, gravflip13, gravflip14,
			gravflip15, gravflip16, gravflip17, gravflip18, Lon, Loff, brick,
			brickv, wallpaper;
	public static Texture cloud1, cloud2, cloud3, cloud4, cloud5, cloud6,
			cloud7, cloud8, cloud9, cloud10, lboxi, doorjam, doorjamv, woodi,
			ledgei, ropei, hangi, hangv, wheeli, wheeli2, sky1, sky2, sky3,
			sky4, sky5, sky6, a1, a2, a3, a4, a5, esc, space, words, words2,
			words3, words4, words5, words6, words7, words8, words9, words10,
			words11, words12, words13, words14, words15, words16, words17,
			words18, words19, words20, words21, words22, words23, al1, al2;
	public static Texture p, pr, pre, pres, press, news;
	public static Texture cliffdesert, cliffdesert2, desertbush, cactus,
			desertplatform, desertplatform1, desertplatform2, desertplatform3,
			desertplatform4, desertplatform5, desertplatform6, desertplatform7,
			desertplatform8, desertplatform9, desertplatform10,
<<<<<<< HEAD
			desertplatform11, desertback,map;
=======
			desertplatform11, desertback;
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

	//INTRO VARS

	//WELCOME VARS
	private Text pressText;
	private Wall wallPaper;
	private ArrowKey spacebar;
	public static boolean spaceDraw;

	//MAIN MENU VARS
	private Text gameText, controlsText, exitText;
	private Arrow row1, row2, row3;
	private int row = 1;
<<<<<<< HEAD
	//WORLD VARS
	private Map gameMap;
	double translate_x,translate_y;
	private Box box;
	private int level1startx=10,level1endx=70,level2startx=69,level2endx=100;
	private boolean pushed=false,pushRight=false,pushLeft;
	
	
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

	// declare different states for the game
	/*
	 * Intro - 'A Bosscoding Production' Welcome - 'Press Space' with wallpaper
	 * Main_Menu - Menu screen with the choices: Game, Settings, Quit Game - a
	 * screen with 3 game slots, if nothing there then it says 'new game', if
	 * there is a save file either there is an option to load or erase the file
	 * Settings - a screen where you could toggle the sound, change controls, or
	 * go back to the main menu Leveli - goes to the ith level World - goes to
	 * the world map and draws boss greed going to whatever level is next Exit -
	 * Exits the game Win/Gameover - have not decided if these are necessary yet
	 * but its good to keep them here if we do decide to use them
	 * 
	 * 
	 * Natural progression is Intro - welcome - main - game(new) - world -
	 * leveli - world - level(i+1)....exit
	 */
	enum State
	{
		INTRO, WELCOME, MAIN_MENU, GAME, SETTINGS, WORLD, LEVEL1, LEVEL2, EXIT, GAMEOVER, WIN;
	}

	public static State state = State.INTRO;

	public static void main(String [] args)
	{

		new GameShell();

	}

	public GameShell()
	{

		initGL(); // create window
		initFonts(); // load font
		initTextures();
		startTime = getTime();

		//Intro variables initialized

		//Welcome variables initialized
		pressText = new Text(50, 410, 256, 48);
		pressText.type = 22;
		wallPaper = new Wall(0, 0, 640, 480);
		spacebar = new ArrowKey(280, 410, 256, 48);
		spacebar.type = 4;
		spaceDraw = false;

		//Main menu variables initialized
		gameText = new Text(390, 200, 256, 48);
		gameText.type = 23;
		controlsText = new Text(320, 270, 400, 48);
		controlsText.type = 4;
		exitText = new Text(407, 340, 128, 48);
		exitText.type = 3;
		row1 = new Arrow(290, 200, 100, 48);
		row2 = new Arrow(290, 270, 100, 48);
		row3 = new Arrow(290, 340, 100, 48);
<<<<<<< HEAD
		
		//World variables initialized
		gameMap = new Map(0,0,4000,1024);
		box = new Box(10,830,32,32);
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

		//logic loop
		while (!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			render();
			input();
			Display.update();
			Display.sync(60);
		}

		Display.destroy();

		System.exit(0);

	}

	// standard GL initialization
	public void initGL()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("BossGreed");
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}

	private void fixKeyboard()
	{
		Keyboard.destroy();
		try
		{
			Keyboard.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}

	// standard font initialization
	@SuppressWarnings("unchecked")
	private void initFonts()
	{

		Font awtFont = new Font("", Font.PLAIN, FONT_SIZE);

		uniFont = new UnicodeFont(awtFont, FONT_SIZE, false, false);
		uniFont.addAsciiGlyphs();
		uniFont.addGlyphs(400, 600);           // Setting the unicode Range
		uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		try
		{
			uniFont.loadGlyphs();
		}
		catch (SlickException e)
		{
		}

		System.out.println("Fonts initialized!");
	}

	private void render()
	{
		switch (state)
		{
		case INTRO:
			drawIntro();
			break;
		case WELCOME:
			drawWelcome();
			break;
		case MAIN_MENU:
			drawMain();
			break;
		case GAME:
			drawGame();
			break;
		case WORLD:
			drawWorld();
			break;
		case LEVEL1:

			playLevel1();
			break;
		case LEVEL2:
			playLevel2();
			break;
		case SETTINGS:
			drawSettings();
			break;
		case EXIT:
			Display.destroy();
			System.exit(0);
			break;
		/*
		 * case GAMEOVER: drawGameOver(); break; case WIN: drawWin(); break;
		 */
		}
	}

	private void input()
	{
		switch (state)
		{
		case INTRO:
			//no input here, just animation
			break;
		case WELCOME:
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				state = State.MAIN_MENU;
			}
			break;
		case MAIN_MENU:
			if (Keyboard.isKeyDown(Keyboard.KEY_UP) && row > 1)
			{
				row--;
				fixKeyboard();
			} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && row < 3)
			{
				row++;
				fixKeyboard();
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
			{
				if (row == 1)
				{
<<<<<<< HEAD
					state = State.WORLD;
=======
					state = State.LEVEL1;
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2
				} else if (row == 2)
				{
					//settings
				} else
				{
					state = State.EXIT;
				}
			}
		case GAME:
			// same as above
		case SETTINGS:
			// same as above
			break;
		case WORLD:
<<<<<<< HEAD
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				if(inLevel1()){
					state = State.LEVEL1;
				}
				else if(inLevel2()){
					state = State.LEVEL2;
				}
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			{
				pushed=true;
				pushLeft=true;
				pushRight=false;
				box.lastDIR=-1;
			} 
			else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			{
				pushed=true;
				pushRight=true;
				pushLeft=false;
				box.lastDIR=1;
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			{
				translate_y+=3;
				System.out.println("Y:" + translate_y);
			} 
			else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				translate_y-=3;
				System.out.println("Y:" + translate_y);
=======
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
			{
				//enter current level
			} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			{
				//go left one level
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			{
				//go right one level
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2
			}
			break;
		case LEVEL1:
			//game input

			break;
		case LEVEL2:
			//game input
			break;

		case EXIT:
			//no input
			break;
		/*
		 * case GAMEOVER: drawGameOver(); break; case WIN: drawWin(); break;
		 */
		}
	}
<<<<<<< HEAD
	private void animateBox(){
		if(inLevel1()){
			if(pushRight){
				moveLevel2();
			}
			else if(pushLeft){
				moveLevel1();
			}
		}
		else if(inLevel2()){
			if(pushRight){
				moveLevel3();
			}
			if(pushLeft){
				moveLevel1();
			}
			
		}
		
		
	}
	
	private boolean inLevel1(){
		return box.getX()<level1endx;
	}
	
	private boolean inLevel2(){
		return box.getX()>level2startx &&box.getX()<level2endx;
	}
	
	private void moveLevel1(){
		
		if(box.getX()>level1startx||box.getY()<830){
			if(box.getX()>level1startx)
				box.setX(box.getX()-.7);
			
			if(box.getY()<830)
				box.setY(box.getY()+.7);	
			
			System.out.println("movelevel1");
		}
		else{
			pushed=false;
			pushLeft=false;
		}	
	}
	
	private void moveLevel2(){
		
		if(box.getX()<level2startx||box.getY()>780){
			if(box.getX()<level2startx)
				box.setX(box.getX()+.7);
			
			if(box.getY()>780)
				box.setY(box.getY()-.7);	
			System.out.println("movelevel2");
		}
		else{
			pushed=false;
			pushRight=false;
		}	
	}
	private void moveLevel3(){
		
		if(box.getX()<240||box.getY()<860){
			if(box.getX()<240)
				box.setX(box.getX()+.7);
			
			if(box.getY()<860)
				box.setY(box.getY()+.7);	
			System.out.println("movelevel3");
		}
		else{
			pushed=false;
			pushRight=false;
		}	
	}
	
	
	
	private void animate(){
		if(translate_x ==0 && translate_y>-500){
			translate_y-=2;
			//intro drop from the sun
		}
		/*else if(translate_x<-1000 &&translate_x>-1390 && translate_y<-225){
			if(translate_y>-230){
				translate_x-=1;
			}
			else{
				translate_y+=2;
				translate_x-=1;
			}
			//climb the mountain
			
		}
		else if(translate_y>-500){
			translate_y-=2;
			translate_x-=1;
			//climb down
		}
		else if(translate_x>-1600){
			translate_x-=2;
			//fin
		}*/
		else{	
			System.out.println("done animating");
		}
	}
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

	private void drawSettings()
	{
		// TODO Auto-generated method stub

	}

	private void playLevel2()
	{
<<<<<<< HEAD
		new GameOn("alex");
		state = State.INTRO;
		pressText.i = 1;
=======
		// TODO Auto-generated method stub
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

	}

	private void playLevel1()
	{
		new GameOn("alex");
		state = State.INTRO;
		pressText.i = 1;
	}

	private void drawWorld()
	{
<<<<<<< HEAD
		glPushMatrix();
			glTranslated(translate_x,translate_y,0);
			gameMap.draw();
			
			box.draw();
			System.out.println("box x:" + box.getX() + "\nbox y:" + box.getY());
			animate();
			if(pushed){
				animateBox();
			}
		glPopMatrix();
=======
		// TODO Auto-generated method stub
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2

	}

	private void drawGame()
	{
		// TODO Auto-generated method stub

	}

	private void drawMain()
	{
		wallPaper.faded = true;
		wallPaper.draw();

		gameText.draw();
		controlsText.draw();
		exitText.draw();
		if (row == 1)
			row1.draw();
		else if (row == 2)
			row2.draw();
		else if (row == 3)
			row3.draw();
	}

	private void drawWelcome()
	{
		wallPaper.faded = false;
		wallPaper.draw();
		pressText.draw();
		if (spaceDraw)
			spacebar.draw();
		//put current intro screen here

	}

	private void drawIntro()
	{
		uniFont.drawString(240, 200, "Bosscoding");
		//put future animation here

		endTime = getTime();
		if ((endTime - startTime) > INTRO_TIME)
		{
			state = State.WELCOME;
		}

	}

	private long getTime()
	{
		return (Sys.getTime() * 1000 / Sys.getTimerResolution());
	}

	public static Texture loadTexture(String key)
	{
		try
		{
			return TextureLoader.getTexture("png", new FileInputStream(
					new File("res/img/" + key + ".png")));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	// just a huge dump of all the textures needed
	public static void initTextures()
	{
		left = loadTexture("bag");
		right = loadTexture("bagi1");
		gleft = loadTexture("bagv1");
		gright = loadTexture("bagv2");

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
		// ski = loadTexture("sky");
		cloud1 = loadTexture("cloud1");
		cloud2 = loadTexture("cloud2");
		cloud3 = loadTexture("cloud3");
		cloud4 = loadTexture("cloud4");
		cloud5 = loadTexture("cloud5");
		cloud6 = loadTexture("cloud6");
		cloud7 = loadTexture("cloud7");
		cloud8 = loadTexture("cloud8");
		cloud9 = loadTexture("cloud9");
		cloud10 = loadTexture("cloud10");
		lboxi = loadTexture("lbox");
		doorjam = loadTexture("doorjam");
		woodi = loadTexture("wood");
		brick = loadTexture("brick");
		brickv = loadTexture("brickv");
		a1 = loadTexture("arrow");
		a2 = loadTexture("arrow1");
		al1 = loadTexture("arrowl1");
		al2 = loadTexture("arrowl2");
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
		words23 = loadTexture("wordsGame");
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

		cactus = loadTexture("cactus");
		desertbush = loadTexture("desertbush");
		desertback = loadTexture("deserthills1");
		desertplatform = loadTexture("desertplatform");
		desertplatform1 = loadTexture("desertplatform1");
		desertplatform2 = loadTexture("desertplatform2");
		desertplatform3 = loadTexture("desertplatform3");
		desertplatform4 = loadTexture("desertplatform4");
		desertplatform5 = loadTexture("desertplatform5");
		desertplatform6 = loadTexture("desertplatform6");
		desertplatform7 = loadTexture("desertplatform7");
		desertplatform8 = loadTexture("desertplatform8");
		desertplatform9 = loadTexture("desertplatform9");
		desertplatform10 = loadTexture("desertplatform10");
		desertplatform11 = loadTexture("desertplatform11");
		cliffdesert = loadTexture("cliffdesert");
		cliffdesert2 = loadTexture("cliffdesert2");
<<<<<<< HEAD
		map = loadTexture("map");
=======
>>>>>>> 2276985d9a0328a0fc96bc5ee539cc61599601e2
	}

}
