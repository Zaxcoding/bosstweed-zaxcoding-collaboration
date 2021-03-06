package game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import entities.Box;
import entities.Shape;
import entities.Sky;

public class GameOn
{
	public boolean done = false;
	public static final int WIDTH = 640;	// game window resolution
	public static final int HEIGHT = 480;
	public static final int FONT_SIZE = 18;

	private float translateX = WIDTH / 2, translateY = HEIGHT * 2 / 5;
	private float startX = translateX, startY = translateY;
	private long lastFrame, startTime;

	private List<Shape> shapes = new ArrayList<Shape>(20);
	private UnicodeFont uniFont = GameShell.uniFont;
	private String inputValue, fileName;

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
			words18, words19, words20, words21, words22;
	public static Texture p, pr, pre, pres, press, news;
	public static Texture cliffdesert, cliffdesert2, desertbush, cactus,
			desertplatform, desertplatform1, desertplatform2, desertplatform3,
			desertplatform4, desertplatform5, desertplatform6, desertplatform7,
			desertplatform8, desertplatform9, desertplatform10,
			desertplatform11, desertback;

	private Box player;
	private Sky background = new Sky(-5000, -5000, 10000, 10000);

	// RUNNING, JUMPING, SLIDING - ADJUST THESE ANDY
	double runSpeed;
	boolean running, moving, pressingJump;
	int failCount = 0;

	int MAX_FAILS = 5;	// this is to determine when the player has let go of the jump key
	double MOVEMENT_AMOUNT = 7;		// max walk speed
	double CHANGE_DIR_SPEED = .20;	// deceleration amount when changing directions
	double RUN_ACCEL_SPEED = .15;	// how fast your speed goes up when running
	double INIT_ACCEL_SPEED = .2;	// how fast you raise up to the normal MOVEMENT_AMOUNT
	double DEACCEL_SPEED = .30;		// how fast you slow down if you're above MOVEMENT_AMOUNT and let go of run, but keep moving
	double RUN_SLIDE_AMOUNT = .25;	// deceleration if you're running and stop moving
	double WALK_SLIDE_AMOUNT = .25;	// deceleration if you're walking and stop moving

	// Jumping and gravity
	// this is the order of jumping: Press Jump to START_SLOWDOWN1_TIME
	// START_SLOWDOWN1_TIME to START_SLOWDOWN2_TIME
	// START_SLOWDOWN2_TIME to HANGTIME
	// HANGTIME to HANGTIME_RUN (if the player is sprinting)
	//
	// The corresponding gravSpeeds are INIT_JUMP_SPEED for the first period,
	// SLOWDOWN1_JUMP_SPEED for the second period, SLOWDOWN2_JUMP_SPEED for the
	// third and fourth.

	double START_SLOWDOWN1_TIME = 250;	// transition point between the two above constants
	double START_SLOWDOWN2_TIME = 350;	// transition point between the two above constants
	double HANGTIME = 450;				// for normal jumps, how long you rise (MUST BE BIGGER THAN START_SLOWDOWN1_TIME)
	double HANGTIME_RUN = 500;			// when running, you rise this long (MUST BE BIGGER THAN HANGTIME)

	double gravSpeed;
	double INIT_JUMP_SPEED = 5.2;		// the initial speed that you start rising with
	double SLOWDOWN1_JUMP_SPEED = 3.2;	// once past START_SLOWDOWN1_TIME, you rise with this speed
	double SLOWDOWN2_JUMP_SPEED = 1.2;	// once past START_SLOWDOWN2_TIME, you rise with this speed

	double GRAVITY_FALL_AMOUNT = 6;		// speed that you fall after the jump's apex, or when walking off cliffs and such
	long fallTime, startFall;			// startFall is when you start falling and fallTime is how long you've been falling
	double TERMINAL_VELOCITY_TIME = 200;
	double INITIAL_FALL_SPEED = 1.2;

	// Sounds!
	private Audio jumpSound, coinSound;

	// Show hitboxes
	boolean displayHitbox = false;

	public GameOn(String level)
	{
		fileName = level; 	// make the filename the level name so restart() functions properly
		// loadLevel();		// not needed
		// initGL();		// already done in GameShell.initGL();
		//initFonts();		// already done
		//initTextures();	// already done
		initSound();
		load(level); //load the level passed in

		lastFrame = getTime();
		startTime = lastFrame;

		while (!Display.isCloseRequested() && !done)
		{
			glClear(GL_COLOR_BUFFER_BIT);
			glPushMatrix();

			glTranslatef(translateX, 0, 0);
			glTranslatef(0, translateY, 0);

			player.grounded = onGround();
			if (player.grounded)
				fallTime = 0;
			input();
			update();
			render();

			glPopMatrix();

			Display.update();
			Display.sync(60);
		}

	}

	// a ton of the game logic is done here
	private void update()
	{
		// LOOK HERE ANDY
		// if you want, you can adjust the values here and run in debug mode to
		// see the changes without restarting
		INIT_JUMP_SPEED = 5.1;		// the initial speed that you start rising with
		SLOWDOWN1_JUMP_SPEED = 2.9;	// once past START_SLOWDOWN1_TIME, you rise
									// with this speed
		SLOWDOWN2_JUMP_SPEED = 2.3;
		START_SLOWDOWN1_TIME = 300;	// transition point between the two above
									// constants HANGTIME = 500; // for normal
									// jumps, how long you rise (MUST BE BIGGER
									// THAN START_SLOWDOWN_TIME)
		START_SLOWDOWN2_TIME = 410;
		HANGTIME = 460;				// for normal jumps, how long you rise (MUST BE BIGGER THAN START_SLOWDOWN_TIME)
		GRAVITY_FALL_AMOUNT = 7;
		double BONUS_RUN_AMOUNT = .5;
		MAX_FAILS = 3;
		TERMINAL_VELOCITY_TIME = 300;
		INITIAL_FALL_SPEED = .2;

		if (getTime() - lastFrame > HANGTIME || !pressingJump)
			player.jumping = false;

		if (!player.jumping && !player.grounded
				&& getTime() - lastFrame > HANGTIME)
		{
			startFall = getTime();
			lastFrame = getTime();
		}

		// this is for falling - if you're not grounded and not on the way up
		if (!player.jumping && !player.grounded)
		{
			if (getTime() - lastFrame - HANGTIME > TERMINAL_VELOCITY_TIME)
			{
				player.setY(player.y + INITIAL_FALL_SPEED * player.gravityMod);
			} else
			{
				player.setY(player.y + GRAVITY_FALL_AMOUNT * player.gravityMod);
			}

			player.groundPiece = null;
		} else
		{
			// this is for rising - if you're jumping
			if (player.jumping)
			{
				gravSpeed = INIT_JUMP_SPEED;
				if (runSpeed == MOVEMENT_AMOUNT)
					gravSpeed = INIT_JUMP_SPEED + BONUS_RUN_AMOUNT;
				if (getTime() - lastFrame > START_SLOWDOWN1_TIME)
					gravSpeed = SLOWDOWN1_JUMP_SPEED;
				if (getTime() - lastFrame > START_SLOWDOWN2_TIME)
					gravSpeed = SLOWDOWN2_JUMP_SPEED;
				player.setY(player.getY() - gravSpeed * player.gravityMod);
			}
		}

		// kinda just filler for now, if you're dead then restart
		if (!player.alive)
		{
			System.out.println("DEAD");
			restart();
		}

		// if the ground piece is ice, then you're onIce
		if (player.groundPiece != null)
			if (!player.groundPiece.name.equals("Ice"))
				player.onIce = false;

		// and if you're on ice, then you're going to keep going at constant speed.
		if (player.onIce)
			player.x += runSpeed * player.lastDIR;

		// a single loop through to check if there's any shapes to move or remove
		Shape temp = new Box(0, 0, 0, 0);
		for (Shape shape : shapes)
		{
			if (player.intersects(shape) && shape.name.equals("Coin"))
			{
				coinSound.playAsSoundEffect(1, 1, false);
			}
			if (shape.removeMe)
				temp = shape;
			if (shape.moving)
				handleMoving(shape);
		}

		if (temp.removeMe)
			shapes.remove(temp);		// one item max can be removed per loop

		// if you pushed left or right, then move in that direction
		if (moving)
		{

			player.x += runSpeed * player.lastDIR;
			for (Shape shape : shapes)
				if (player.intersects(shape) && shape.solid && shape.visible
						&& player.groundPiece != shape)
				{
					System.out.println("Hit!  " + shape.name);
					player.x -= runSpeed * player.lastDIR;
					moving = false;
					runSpeed = 0;
				}
		} else if (!player.onIce)
		{
			// otherwise, as long as you're not on ice, start sliding
			if (runSpeed > 0)
			{
				if (running)
					runSpeed -= RUN_SLIDE_AMOUNT;		// decelerate at this rate if running
				else
					runSpeed -= WALK_SLIDE_AMOUNT;		// and at this rate if not running
				player.x += runSpeed * player.lastDIR;	// this is the actual slide
			}
		}

		// the camera logic
		// translateY = HEIGHT/2 - (int)player.y;
		translateX = WIDTH / 2 - (int) player.x;
	}

	// this handles the movement of clouds and moving platforms/deads/etc
	private void handleMoving(Shape shape)
	{
		if (shape.name.equals("Cloud"))
		{
			shape.x += shape.moveSpeed;
		} else
		{
			// pretty simple, solid logic
			if (shape.upDown && shape.downRight)
			{
				shape.y += shape.moveSpeed;				// do the move
				if (player.groundPiece == shape)		// if the player is on it
					player.y += shape.moveSpeed;		// then move him too
				if (shape.y > shape.endPos)				// check if you hit the end
					shape.downRight = false;			// and if so toggle the direction
			} else if (shape.upDown && !shape.downRight)
			{
				shape.y -= shape.moveSpeed;
				if (player.groundPiece == shape)
					player.y -= shape.moveSpeed;
				if (shape.y < shape.startPos)
					shape.downRight = true;
			} else if (!shape.upDown && shape.downRight)
			{
				shape.x += shape.moveSpeed;
				if (player.groundPiece == shape)
					player.x += shape.moveSpeed;
				if (shape.x > shape.endPos)
					shape.downRight = false;
			} else if (!shape.upDown && !shape.downRight)
			{
				shape.x -= shape.moveSpeed;
				if (player.groundPiece == shape)
					player.x -= shape.moveSpeed;
				if (shape.x < shape.startPos)
					shape.downRight = true;
			}
		}
	}

	// all the keypresses are checked here and then the appropriate methods are called
	public void input()
	{
		if (failCount++ > MAX_FAILS)	// if you've let go of jump for MAX_FAILS straight times,
			pressingJump = false;		// then you're not pressing jump

		// W or up arrow to jump
		if (Keyboard.isKeyDown(Keyboard.KEY_W)
				|| Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			pressingJump = true;	// so you can still be pressing jump but not necessarily 'jumping'
			failCount = 0;			// reset the failCount
			if (player.groundPiece != null)
				if (!player.groundPiece.name.equals("Grav") && player.grounded
						&& !player.jumping)
				{
					jump(player);
					lastFrame = getTime();
				}
		}

		running = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			running = true;

		moving = false;
		// move the player and the camera left
		if (Keyboard.isKeyDown(Keyboard.KEY_A)
				|| Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			move(-1);
		// move the player and the camera right
		if (Keyboard.isKeyDown(Keyboard.KEY_D)
				|| Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			move(1);

		// reset the player's position, the camera, and the gravity
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			restart();

		if (Keyboard.isKeyDown(Keyboard.KEY_G))	// debugging
		{
			player.gravityMod *= -1;
			coinSound.playAsSoundEffect(1, 1, false);
			fixKeyboard();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_H))
		{
			displayHitbox = !displayHitbox;
			fixKeyboard();
		}

	}

	// this method is called from the main loop (grounded = onGround()) to see
	// if the player is standing firmly on a solid, visible piece
	public boolean onGround()
	{
		// mostly for grav, possibly could be used for a trampoline
		if (player.bounce)
		{
			player.bounce = false;
			player.groundPiece = null;
			return false;				// no, not grounded
		}

		// if we're already on the ground, then just check that we're still on
		// top of the groundPiece
		if (player.groundPiece != null && player.grounded)
		{
			if ((player.groundPiece.x - player.width + 5 <= player.x && (player.groundPiece.x
					+ player.groundPiece.width >= player.x + 5)))
			{
				player.groundPiece.interact(player);	// interact with the
														// groundPiece
				return true;							// and say yes, you are grounded
			}
		}

		// if we're not on the ground, time to see if we're on the ground somewhere else
		for (Shape shape : shapes)
		{
			if ((shape.x - player.width + 5 <= player.x && 		// these two are to check
					(shape.x + shape.width >= player.x + 5))	// you're above it
					&& !player.jumping							// jumping
					&& !shape.name.equals("Box")				// not on yourself
					&& shape.solid && shape.visible && shape.width > 15)	// not a blip
			{
				if (player.gravityMod == 1 && 						// player is falling
						(player.y + player.height > shape.y) &&		// through the top
						(player.y + player.height <= shape.y + MOVEMENT_AMOUNT
								+ 1))								// but not by much
				{
					player.y = shape.y - player.height;			// put the player on the top
					shape.interact(player);						// interact with it
					if (shape.partner != null)					// and if the piece has a partner
						shape.partner.action();					// then do that partner's action
					player.groundPiece = shape;					// and set the player's groundPiece to be what he's on
					return true;								// and yes, you're grounded
				}
				if (player.gravityMod == -1 && 						// player is rising
						(player.y < shape.y + shape.height) &&		// through bottom
						(player.y >= shape.y + shape.height - MOVEMENT_AMOUNT
								- 1))								// but not by much
				{
					player.y = shape.y + shape.height;				// do all the same stuff
					shape.interact(player);
					if (shape.partner != null)
						shape.partner.action();
					player.groundPiece = shape;
					return true;
				}
			}
		}

		return false;		// you weren't grounded before, you're not on any of the other shapes, you're not grounded
	}

	// the things that happen when a player jumps
	private void jump(Box player)
	{
		player.jumping = true;			// you're jumping
		player.grounded = false;		// you aren't grounded (note this is not grounded = onGround() grounded, 
		// that will be determined on its own)
		player.groundPiece = null;		// you have no groundPiece
		jumpSound.playAsSoundEffect(1, 1, false);	// play the jump sound!
	}

	// this is a generalized version of moveLeft and moveRight
	private void move(int dir)
	{
		// if the direction is different from the player's lastDIR, and you're
		// !onIce then you're changing directions
		if (dir != player.lastDIR && !player.onIce)
		{
			runSpeed -= CHANGE_DIR_SPEED;	// decelerate by CHANGE_DIR_SPEED
			// play slide sound
			if (runSpeed < 0)				// at this point, you've completely turned around
			{
				player.lastDIR = dir;		// so change the player's direction and
				runSpeed = .25;				// start with a little push in the right direction
			}
		} else if (!player.onIce)		// you want to keep moving in the same direction, and you're not onIce
		{
			moving = true;		// okay, then you're moving (and the player.x will be increased in update())

			// you're sprinting and not yet at top run speed
			if (runSpeed < 2 * MOVEMENT_AMOUNT && running)
				runSpeed += RUN_ACCEL_SPEED;			// acceleration amount

			// if you're walking and not yet at top walk speed
			if (runSpeed < MOVEMENT_AMOUNT)
				runSpeed += INIT_ACCEL_SPEED;		// initial acceleration amount

			// if you were running at beyond top walk speed, then decelerate
			// to top walk speed
			if (!running && runSpeed > MOVEMENT_AMOUNT)
			{
				runSpeed -= DEACCEL_SPEED;			// when you let go of running
				if (runSpeed < MOVEMENT_AMOUNT)		// to make sure you don't go
					// below top walk speed
					runSpeed = MOVEMENT_AMOUNT;
			}
		}
	}

	// when the user hits 'R', this stuff happens
	private void restart()
	{

		player.alive = true;
		player.x = startX;
		player.y = startY;
		translateX = WIDTH / 2;
		translateY = HEIGHT * 2 / 5;
		player.gravityMod = 1;
		player.onIce = false;
		startTime = getTime();
		load(fileName);
		fixKeyboard();
		runSpeed = 0;
		done = true;
	}

	// the render loop- very simple
	private void render()
	{
		background.draw();		// draw the sky

		// draw everything in order
		for (int i = 1; i <= 4; i++)
			for (Shape shape : shapes)
				if (shape.visible && shape.displayOrder == i)
				{
					shape.draw();
					if (displayHitbox)
						shape.drawHitbox();
				}

		// draw the text
		uniFont.drawString(5 - translateX, 5 - translateY, "GoldCount: "
				+ player.goldCount);
		uniFont.drawString(5 - translateX + WIDTH / 3, 5 - translateY, "Time: "
				+ (getTime() - startTime) / 1000.);
	}

	// a method needed for times you only want to collect a single keypress
	// (like a toggle)
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

	// for judging hangtime, playtime, and such
	private long getTime()
	{
		return (Sys.getTime() * 1000 / Sys.getTimerResolution());
	}

	// the actual work of loading from the editor-made file
	public void load(String filename)
	{
		try
		{
			shapes.clear();		// clear the array of the old shapes (if any)
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(
					"levels/" + filename));
			int size = IS.readInt();	// first thing is an int with the amount of
										// shapes

			double r = IS.readDouble();		// then the sky's rgb
			double g = IS.readDouble();
			double b = IS.readDouble();

			background.setRGB(r, g, b);	// so set the sky's color

			for (int i = 0; i < size; i++)
			{
				int code = IS.readInt();			// first there's a shapeCode to determine what to load
				Shape temp = Shape.load(IS, code);	// this static method in Shape does all the work
				shapes.add(temp);					// add it to the shape array
			}

			startX = IS.readFloat();	// lastly we have the start position
			startY = IS.readFloat();
			IS.close();					// gotta close the file stream

			// make the player
			player = new Box(startX, startY, 32, 32);
			shapes.add(player);

			// this loop goes through and links all the partners, since they weren't strictly saved
			for (Shape shape : shapes)
				if (shape.partnerX != 0 || shape.partnerY != 0)		// note that if the partner's x,y is 0,0 for some reason,
					// this won't work
					for (Shape shaper : shapes)
						if (shape.partnerX == shaper.x
								&& shape.partnerY == shaper.y)
						{
							shape.partner = shaper;
							if (shape == shaper && shape.action == 3)	// if the shape is partners with itself and the
																		// action is moving, then start moving
								shape.action();
						}

			System.out.println("Loaded level, now for fonts!");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// gets the name of the file to load and then calls the private method
	public void loadLevel()
	{
		inputValue = JOptionPane
				.showInputDialog("Enter the filename to load please: ");
		if (inputValue != null && !inputValue.equals(""))
		{
			load(inputValue);
			fileName = inputValue;
		}
	}

	// NEEDS MORE SOUNDS
	private void initSound()
	{
		try
		{
			jumpSound = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("res/sound/jump.wav"));
			coinSound = AudioLoader.getAudio("WAV",
					ResourceLoader.getResourceAsStream("res/sound/coin.wav"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	// if you move around the file hierarchy, adjust the image file locations here
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

	// GameOn
	public static void main(String [] args)
	{
		new GameOn(null);
	}

}
