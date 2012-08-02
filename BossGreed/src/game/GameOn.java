package game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.WaveData;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import entities.Box;
import entities.Shape;
import entities.Sky;

import org.lwjgl.openal.AL;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import static org.lwjgl.openal.AL10.*;

public class GameOn 
{
	public static final int WIDTH = 640;			// game resolution
	public static final int HEIGHT = 480;
	public static final int GRAVITY_MOVEMENT_AMOUNT = 4;	// for jumping/falling
	public static final int FONT_SIZE = 18;		
	
	private float translateX = WIDTH/2, translateY = HEIGHT/2;
	private float startX = translateX, startY = translateY;
	private long lastFrame, startTime;	
		
	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont;
	private String inputValue;
	
	private Sky background = new Sky(-5000, -5000, 10000, 10000);
	double r, g, b;
	
	public static Texture left,right,gright,gleft,icel,cliffi,cliffv,icev,deadi,deadi1,deadi2,deadi3,deadi4,deadv,deadv1,deadv2,deadv3,deadv4,coini,door,doorv,gravflip,gravflip2,gravflip3,gravflip4,gravflip5,gravflip6,gravflip7,gravflip8,gravflip9,gravflip10,gravflip11,gravflip12,gravflip13,gravflip14,gravflip15,gravflip16,gravflip17,gravflip18,Lon,Loff,brick,brickv,wallpaper;
	public static Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10,lboxi,doorjam,doorjamv,woodi,ledgei,ropei,hangi,hangv,wheeli,wheeli2,sky1,sky2,sky3,sky4,sky5,sky6,a1,a2,a3,a4,a5,esc,space,words,words2,words3,words4,words5,words6,words7,words8,words9,words10,words11,words12,words13,words14,words15,words16,words17,words18,words19,words20,words21,words22;
	public static Texture p,pr,pre,pres,press,news;
	
	private Box player;
	
	public String fileName;
	
	// sound, still needs work
	WaveData coin, jump;
	int source;
	
	// RUNNING, JUMPING, SLIDING - ADJUST THESE ANDY
	double runSpeed;
	boolean running, moving, pressingJump;
	int failCount = 0;			
	
	int HANGTIME = 300;				// for normal jumps, how long you rise
	int HANGTIME_RUN = 400;			// when running, you rise this long
	int MAX_FAILS = 5;				// this is to determine when the player has let go of the jump key
	double MOVEMENT_AMOUNT = 3;		// max walk speed
	double CHANGE_DIR_SPEED = .20;	// deceleration amount when changing directions
	double RUN_ACCEL_SPEED = .1;	// how fast your speed goes up when running
	double INIT_ACCEL_SPEED = .25;	// how fast you raise up to the normal MOVEMENT_AMOUNT
	double DEACCEL_SPEED = .20;		// how fast you slow down if you're above MOVEMENT_AMOUNT and let go of run, but keep moving
	double RUN_SLIDE_AMOUNT = .15;	// deceleration if you're running and stop moving
	double WALK_SLIDE_AMOUNT = .35;	// deceleration if you're walking and stop moving
	
	
	public GameOn()
	{		
		loadLevel();
		initGL();
		initFonts();		// not needed for now
		initTextures();
		initSound();
		
		lastFrame = getTime();
		startTime = lastFrame;
	
		
		
		while (!Display.isCloseRequested())
		{			
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();
					
			glTranslatef(translateX, 0, 0);
			glTranslatef(0, translateY, 0);

			player.grounded = onGround();
			input();
						
			update();
			render();
						
			glPopMatrix();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
		System.exit(0);
	}	
	
	private void initSound()
	{
		WaveData data;
		try {
			data = WaveData.create(new FileInputStream("res/jump.wav"));
			int buffer = alGenBuffers();
	        alBufferData(buffer, data.format, data.data, data.samplerate);
	        data.dispose();
	        source = alGenSources();
	        alSourcei(source, AL_BUFFER, buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void initTextures()
	{
		/*
		 * these top 4 are an attempt at a new boss greed but he is too small for it to be useful
		left = loadTexture("newbagleft");
		right = loadTexture("newbag");
		gleft = loadTexture("newbagleftv");
		gright = loadTexture("newbagrightv");
		
		
		these next 20 are him 'gaining weight' as coins are acquired, not good enough to use right now
		left1 = loadTexture("bagsi1");
		right1 =loadTexture("bagsi1_2");
		gleft1 =loadTexture("bagsv1");
		gright1 =loadTexture("bagsv1_2");
		
		
		left2 = loadTexture("bagsi2");
		right2 =loadTexture("bagsi2_2");
		gleft2 =loadTexture("bagsv2");
		gright2 =loadTexture("bagsv2_2");
		
		
		left3 = loadTexture("bagsi3");
		right3 =loadTexture("bagsi3_2");
		gleft3 =loadTexture("bagsv3");
		gright3 =loadTexture("bagsv3_2");
		
		
		left4 = loadTexture("bagsi4");
		right4 =loadTexture("bagsi4_2");
		gleft4 =loadTexture("bagsv4");
		gright4 =loadTexture("bagsv4_2");
		
		
		left5 = loadTexture("bagsi5");
		right5 =loadTexture("bagsi5_2");
		gleft5 =loadTexture("bagsv5");
		gright5 =loadTexture("bagsv5_2");
		*/
	
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
	
	// good
	private void load(String filename)
	{
		try
		{
			shapes.clear();
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(inputValue));
			int size = IS.readInt();
			
			r = IS.readDouble();
			g = IS.readDouble();
			b = IS.readDouble();
			
			background.setRGB(r,g,b);
			//shapes.add(background);
			
			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();
				Shape temp = Shape.load(IS, code);
				shapes.add(temp);
			}
			
			startX = IS.readFloat();
			startY = IS.readFloat();
			IS.close();


			player = new Box(startX, startY, 26, 26);
			shapes.add(player);
			
			
			for (Shape shape : shapes)
				if (shape.partnerX != 0 && shape.partnerY != 0)
					for (Shape shaper : shapes)
						if (shape.partnerX == shaper.x && shape.partnerY == shaper.y)
						{
							shape.partner = shaper;
							if (shape == shaper && shape.action == 3)
								shape.action();
						}
			// start here
			// with the actioning
			
			
			
			System.out.println("Loaded level, now for fonts!");
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadLevel()
	{
		inputValue = JOptionPane.showInputDialog("Enter the filename to load please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
			load(inputValue);
			fileName = inputValue;
		}	
	}
	
	// good
	private void update()
	{
		if (!running && (getTime() - lastFrame > HANGTIME || !pressingJump))
			player.jumping = false;
		if (running && (getTime() - lastFrame > HANGTIME_RUN || !pressingJump))
			player.jumping = false;		// CHANGE THIS ^ to adjust the run jump height
		
		if (!player.jumping && !player.grounded)
		{
			player.setY(player.y + GRAVITY_MOVEMENT_AMOUNT*player.gravityMod);
			player.groundPiece = null;
		}
		else
		{
			if (player.jumping)
				player.setY(player.getY() - GRAVITY_MOVEMENT_AMOUNT*player.gravityMod);		// go up/down by 4				
		}
		if (!player.alive)
		{
			System.out.println("DEAD");
			restart();
		}
		if (player.groundPiece != null)
			if (!player.groundPiece.name.equals("Ice"))
				player.onIce = false;
		
		if (player.onIce)
			player.x += runSpeed*player.lastDIR;
		
		Shape temp = new Box(0,0,0,0);
		for (Shape shape: shapes)
		{
			if (player.intersects(shape) && shape.name.equals("Coin"))
			{
				// play coin sound
			}
			if (shape.removeMe)
				temp = shape;
			if (shape.moving)
				handleMoving(shape);
			
		}
		if (temp.removeMe)
			shapes.remove(temp);
		
		// sliding/running
		
		if (moving)
			player.x += runSpeed*player.lastDIR;
		else if (!player.onIce)
		{
			if (runSpeed > 0)
			{
				if (running)
					runSpeed -= RUN_SLIDE_AMOUNT;	// slide longer when running
				else
					runSpeed -= WALK_SLIDE_AMOUNT;	// if not running, slow down faster
				player.x += runSpeed*player.lastDIR;
			}
		}
		
		moving = false;
			
		
		
	}
	
	private void handleMoving(Shape shape)
	{
		if (shape.name.equals("Cloud"))
		{
			shape.x += shape.moveSpeed;
		}
		else
		{
			if (shape.upDown && shape.downRight)
			{
				shape.y += shape.moveSpeed;
				if (player.groundPiece == shape)
					player.y += shape.moveSpeed;
				if (shape.y > shape.endPos)
					shape.downRight = false;
			}
			else if (shape.upDown && !shape.downRight)
			{
				shape.y -= shape.moveSpeed;
				if (player.groundPiece == shape)
					player.y -= shape.moveSpeed;
				if (shape.y < shape.startPos)
					shape.downRight = true;
			}
			else if (!shape.upDown && shape.downRight)
			{
				shape.x += shape.moveSpeed;
				if (player.groundPiece == shape)
					player.x += shape.moveSpeed;
				if (shape.x > shape.endPos)
					shape.downRight = false;
			}
			else if (!shape.upDown && !shape.downRight)
			{
				shape.x -= shape.moveSpeed;
				if (player.groundPiece == shape)
					player.x -= shape.moveSpeed;
				if (shape.x < shape.startPos)
					shape.downRight = true;
			}
		}
	}
	
	// good
	public void input()
	{
		// W or up arrow to jump
		failCount++;
		if (failCount > MAX_FAILS)			// if you've let go of jump for 5 straight times,
			pressingJump = false;	// then you're not pressing jump
		if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			pressingJump = true;
			failCount = 0;			// reset the failCount
			if (player.groundPiece != null)
				if (!player.groundPiece.name.equals("Grav") && player.grounded && !player.jumping)
				{	
					jump(player);
					lastFrame = getTime();
				}
		}
		running = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			running = true;		
		// move the player and the camera left
		if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			move(player, -1);
		// move the player and the camera right
		if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			move(player, 1);
		// reset the player's position, the camera, and the gravity
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			restart();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_G))	// debugging
		{
			player.gravityMod *= -1;
			fixKeyboard();
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
	
	// needs testing
	public boolean onGround()
	{
		if (player.bounce)
		{
			player.bounce = false;
			player.groundPiece = null;
			return false;
		}
					
		// if we're already on the ground, then just check that we're still on top of the groundPiece
		// note the extra 3/4 width cushion for the player (to make it more fun)
		if (player.groundPiece != null && player.grounded)
		{
			if ((player.groundPiece.x - player.width + 5 <= player.x && 
					(player.groundPiece.x + player.groundPiece.width >= player.x + 5)))
			{
				player.groundPiece.interact(player);
				return true;
			}
		}
		
		// if we're not on the ground, time to see if we're on the ground somewhere else
		for (Shape shape : shapes)
		{		
			// lined up between the ends, not the player, and player not jumping
			if ((shape.x - player.width + 5 <= player.x  && 
					(shape.x + shape.width >= player.x + 5)) &&
					!player.jumping && !shape.name.equals("Box") && shape.solid && shape.visible && shape.width > 15)
			{
				if (player.gravityMod == 1 && 					// falling
					(player.y + player.height > shape.y) &&		// through top
						 (player.y + player.height <= shape.y + MOVEMENT_AMOUNT + 1))	// but not by much
				{
					player.y = shape.y - player.height;
					shape.interact(player);
					if (shape.partner != null)
						shape.partner.action();
					player.groundPiece = shape;
					return true;
				}
				if (player.gravityMod == -1 && 						// rising
						(player.y < shape.y + shape.height) &&		// through bottom
							 (player.y >= shape.y + shape.height - MOVEMENT_AMOUNT - 1))	// but not by much
				{
					player.y = shape.y + shape.height;
					shape.interact(player);
					if (shape.partner != null)
						shape.partner.action();
					player.groundPiece = shape;
					return true;
				}				
			}
		}
		return false;
	}
	
	// needs testing
	private void jump(Box player)
	{	
		System.out.println("JUMP");
		player.jumping = true;
		player.grounded = false;
		player.groundPiece = null;
		alSourcePlay(source);
	}
	
	private void move(Box player, int dir)
	{	
		if (dir != player.lastDIR && !player.onIce)
		{
			runSpeed -= CHANGE_DIR_SPEED;			// changing directions deceleration amount
			// slide sound
			if (runSpeed < 0)
			{
				player.lastDIR = dir;
				runSpeed = .25;			// just so you're not going the wrong direction
			}
		}
		else if (!player.onIce)
		{
			moving = true;
						
			if (runSpeed < 2*MOVEMENT_AMOUNT && running)
			{
				runSpeed += RUN_ACCEL_SPEED;			// acceleration amount
				System.out.println("runspeed: " + runSpeed);
			}
			if (runSpeed < MOVEMENT_AMOUNT)
				runSpeed += INIT_ACCEL_SPEED;		// initial acceleration amount
			if (!running && runSpeed > MOVEMENT_AMOUNT)
			{
				runSpeed -= DEACCEL_SPEED;			// when you let go of running
				if (runSpeed < MOVEMENT_AMOUNT)
					runSpeed = MOVEMENT_AMOUNT;
			}
		}
	}
	
	// empty
	private void restart()
	{
		player.alive = true;
		player.x = startX;
		player.y = startY;
		translateX = WIDTH/2;
		translateY = HEIGHT/2;
		player.gravityMod = 1;
		player.onIce = false;
		startTime = getTime();
		load(fileName);
		fixKeyboard();
	}
	
	// done
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("BossGreed");
			Display.create();
			AL.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		
		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	// done
	private void render()
	{			
		background.draw();
		
		for (Shape shape : shapes)
			if (shape.visible && shape.name.equals("Cloud"))
				shape.draw();

		for (Shape shape : shapes)
			if (shape.visible && !shape.name.equals("Cloud"))
				shape.draw();

		uniFont.drawString(5 - translateX, 5 - translateY,"GoldCount: " + player.goldCount);
		uniFont.drawString(5 - translateX + WIDTH/3, 5 - translateY,"Time: " + (getTime() - startTime)/1000.);
		
		translateY = HEIGHT/2 - (int)player.y;
		translateX = WIDTH/2 - (int)player.x;
	}
	
	// done
	private int getDelta()
	{
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		
		return delta;
	}
	
	// done
	private long getTime()
	{
		return(Sys.getTime() *1000/ Sys.getTimerResolution());
	}
	
	// done
	@SuppressWarnings("unchecked")
	private void initFonts() {

        Font awtFont = new Font("", Font.PLAIN, FONT_SIZE);
       
        uniFont = new UnicodeFont(awtFont, FONT_SIZE, false, false);
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400,600);           // Setting the unicode Range
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {};
        
        System.out.println("Fonts initialized!");
    }
	
	
	
	// done
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
	
	// done
	public static void main(String [] args)
	{
		new GameOn();
	}
	
}