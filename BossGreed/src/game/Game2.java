package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display; 

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import editor.LevelEditor;
import entities.*;

public class Game2 {
	public static final int width = 640;
	public static final int height = 480;
	float translate_x = 0;
	float translate_y =0;
	private long lastFrame,oldFrame;
	
	// ******Variables for LEVEL1 and LEVEL2************
	public static Texture left,right,gright,gleft,icel,cliffi,cliffv,icev,deadi,deadi1,deadi2,deadi3,deadi4,deadv,deadv1,deadv2,deadv3,deadv4,coini,door,doorv,gravflip,gravflip2,gravflip3,gravflip4,gravflip5,gravflip6,gravflip7,gravflip8,gravflip9,gravflip10,gravflip11,gravflip12,gravflip13,gravflip14,gravflip15,gravflip16,gravflip17,gravflip18,Lon,Loff,brick,brickv,wallpaper;
	public static Texture cloud1,cloud2,cloud3,cloud4,cloud5,cloud6,cloud7,cloud8,cloud9,cloud10,lboxi,doorjam,doorjamv,woodi,ledgei,ropei,hangi,hangv,wheeli,wheeli2,sky1,sky2,sky3,sky4,sky5,sky6,a1,a2,a3,a4,a5,esc,space,words,words2,words3,words4,words5,words6,words7,words8,words9,words10,words11,words12,words13,words14,words15,words16,words17,words18,words19,words20,words21,words22;
	public static Texture p,pr,pre,pres,press,news;
	//private Texture left1,right1,gright1,gleft1,left2,right2,gleft2,gright2,left3,right3,gleft3,gright3,left4,right4,gleft4,gright4,left5,right5,gleft5,gright5,ski;
	int x = 10,goldCount=0;
	int y =440;
	int dx =1;
	int dy =1;
	int currLevel=0;
	int endHeight =220,bumper=4,endHeight1 = -1285;
	
	private Sky skyi,skimain;
	private Box box,hitbox;
	private Bat[] cliff;
	private Ice[] ice;
	private Dead[] dead;
	private Dead window;
	private Grav[] grav,bit,appear;
	
	private Coin[] gold;
	private Ice wall;
	
	private Gem gem;
	private Cloud []clouds;
	
	private Doorjam doorj,doorj1;
	
	private Rope rope,rope1,rope2,rope3;
	private Hang hang1,hang11;
	private Wheel wheel,wheel1;
	private Ledge ledge,ledge1;
	private Wall wallp;
	private News [] newspaper;
	int cliffnum=8,icenum=10,deadnum=46,gravnum=17;
	int count;
	int delta = getDelta();
	int cliffWidth =12,iceWidth =12,deadWidth =18,gravWidth=12,lightWidth =16,brickWidth =80,coinWidth=16,coinHeight=2*coinWidth,bitSize=10;
	public static boolean jump=false,fall=true,onGround=false,DIRlock = false,jumpvar,blueFound=false,onCliff=false;
	public static boolean blueFound2 = false,goldFound=false,up=false,goldFound2=false,goldFound3=false,goldFound4=false,blueFound3=false;
	public static boolean goldFound5 = false,win=false,win1=false,ledgeFall=false,ledgeFall1=false;
	public static boolean wallhit=false;
	public static boolean gravity=true,spaceDraw=false;
	public static int lastDIR;
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	private List<Shape> shapes = new ArrayList<Shape>(20);
	
	String inputValue;
	
	private Box player, player1;
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	
	//********variables for main menu***********
	private Text []text;
	private Brick[] bat;
	private Skyline[] skyl;
	int batnum;
	boolean moveLeft = true;
	private Arrow[] arrow;
	private ArrowKey[] keys;
	
	//********main********
	public static void main(String [] args){
		
		new Game2();
	}

	
	//********actual game********
	public Game2(){
		//initialize main menu variables [some of these variables are used throughout the main program, not necessarily in main so there is some inconsistency here]
		
		//initialize window and opengl stuff
		initGL();
		
		//initialize all textures
		initTextures();
		
		//$$$$ I took out a redundant gl setup here
		
		skyi = new Sky(-10000,-10000,20000,20000);
		
		//main loop
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			checkInput();
			renderer();
			
			Display.update();
			Display.sync(60);
			
			
		}
		
		Display.destroy();
		System.exit(0);
		
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
	
	
	//check input of whatever state you may be in
	private void checkInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&!DIRlock)
			{
				
					box.setX(box.getX() +3);
					hitbox.setX(hitbox.getX() +3);
					translate_x -= 3;
					lastDIR = 2;
				
				
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&!DIRlock){
				
				box.setX(box.getX() -3);
				hitbox.setX(hitbox.getX() -3);
				translate_x += 3;
				lastDIR = 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)&&onGround){
				
				jump = true;
				fall = false;
				onGround=false;
				jumpvar = true;
				//DIRlock = false;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				translate_y+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				translate_x+=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				translate_y-=4;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				translate_x-=4;
			}
	}
	//what to render depending on the state of the game
	private void renderer()
	{
			render();
	}
	//initialize opengl
	public void initGL()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Andy's Game");
			Display.create();
		} catch (LWJGLException e) 
		{	e.printStackTrace();	}

		
		// Set-up an orthographic presentation where (0, 0) is the upper-left corner and (1024, 600) is the bottom right one.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void loadLevel(String filename)
	{
		currLevel=0;
		translate_x=0;
		translate_y=0;
		endHeight =270;
		lastDIR =1;
		goldCount =0;
		jump=false;fall=true;gravity=true;onGround=false;DIRlock = false;blueFound=false;onCliff=false;
		blueFound2 = false;goldFound=false;up=false;goldFound2=false;goldFound3=false;goldFound4=false;blueFound3=false;
		goldFound5 = false;win=false;
		wallhit=false;
		
		player = new Box(0,0, 26, 26);
		player1 = new Box(0,0, 18, 18);
	
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
					LevelEditor.assignPic(temp);
					shapes.add(temp);
				}
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
	
	//rendering function for LEVEL1
	private void render(){
		glPushMatrix();
		glTranslatef(translate_x,translate_y,0);
		
		for (Shape shape: shapes)
			if (shape.visible)
				shape.draw();
		
		
		
		//Scrolling clouds
		for (Shape shape : shapes)
		{
			if (shape.name.equals("Cloud"))
			{
				shape.setX(shape.getX() + dx);
				if (shape.getX() >= 3300)
					shape.setX(-500);
			}
		}
		
		/*
		//changes the point at which the spike will stop
		for (Shape shape : shapes)
			if (shape.name.equals("Doorjam"))
			{
				shape.endHeight = (int) (shape.getY() + shape.getHeight() - 12*hitbox.goldCount);
		
		
				//these 5 ifs are the logic for moving the door up and down
				if (win && shape.getY() > endHeight && ledgeFall)
					shape.upp = true;
				
				if(shape.upp){
					shape.setY(shape.getY()-.2);
					ledge.setY(ledge.getY()+.2);
					rope.setHeight(rope.getHeight()+.2);
					translate_y -=.22;
				
				}
				if(shape.getY()<=endHeight){
					shape.upp = false;
					shape.pause = true;
				}
				if(!ledgeFall){
					shape.pause =false;
				}
				if(!shape.upp&&shape.getY()<230&&!shape.pause){
					shape.setY(shape.getY()+.7);
					ledge.setY(ledge.getY()-.7);
					rope.setHeight(rope.getHeight()-.7);
					translate_y +=.1;
					
					
				}
			}
		*/ 
		
		//criterion for winning the game
		if(gem.contains(hitbox)||hitbox.intersects(gem)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
		}
		
		
		//How jumping is handled
		if(jump)
		{
			if(gravity){
				
				if(getTime()-oldFrame <300)
				{
					player.setY(player.getY()-4);
					player1.setY(player1.getY()-4);
					translate_y+=4;
				}
				else{
					fall = true;
					jump = false;
					jumpvar=false;
					//DIRlock = false;
				}
			}
			else{
				if(getTime()-oldFrame <300)
				{
					player.setY(player.getY()+4);
					player1.setY(player1.getY()+4);
					translate_y-=4;
				}
				else{
					fall = true;
					jump = false;
					jumpvar =false;
					//DIRlock = false;
				}
			}
			
		}
		else{
			oldFrame =getTime();
			doorj.upp = false;
		}
		
		//How falling is handled
		if(fall&&!jump&&!jumpvar){
			if(gravity){
				player.setY(player.getY()+4);
				player1.setY(player1.getY()+4);
				if(!onGround){
					translate_y-=4;
				
				}
				onGround = false;
				//DIRlock = false;
			}
			else if(!gravity){
				
				player.setY(player.getY()-4);
				player1.setY(player1.getY()-4);
				if(!onGround){
					translate_y+=4;
					
				}
				onGround = false;
				//DIRlock = false;
				
			}
				if(ledgeFall)
				{
					ledgeFall = false;
					
				}
				doorj.upp = false;
		}
		
		
		
		
		//a cliff is a grass texture and this is how they are drawn and dealt with interactions
		for(int i=0; i<cliffnum;i++){
			if(player.on(cliff[i])&&!jump&&gravity){
				if(i ==0){
					if(player.on(cliff[i]))
						translate_y= -76;
				}
				else if(i==6)
				{}
				player.setY((cliff[i]).getY()-player.getHeight());
				player1.setY((cliff[i]).getY()-player1.getHeight()-bumper);
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
				
			}
			else if(player.under(cliff[i])&&!jump&&!gravity){
				player.setY((cliff[i]).getY()+cliff[i].getHeight());
				player1.setY((cliff[i]).getY()+cliff[i].getHeight()+bumper);
				fall = false;
				onGround=true;
				onCliff = true;
				DIRlock = false;
				
			}
			else{
				fall = true;
				onCliff= false;
				//onGround = false;
				
				
			}
			cliff[i].draw();
		}
		
		
		//a ice is a icy texture and this is how they are drawn and dealt with interactions
		for(int i=0; i<icenum;i++){
			if(player.on(ice[i])&&!jump&&gravity){
				player.setY((ice[i]).getY()-player.getHeight());
				player1.setY((ice[i]).getY()-player1.getHeight()-bumper);
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
			}
			else if(player.under(ice[i])&&!jump&&!gravity){
				player.setY((ice[i]).getY()+ice[i].getHeight());
				player1.setY((ice[i]).getY()+ice[i].getHeight()+bumper);
				fall = false;
				onGround=true;
				DIRlock= true;
				
				
				
			}
			else{
				fall = true;
				//onGround = false;
				
				
			}
			
			ice[i].draw();
			
			
		}
		
		
		//a dead is a fire texture and this is how they are drawn and dealt with interactions
		for(int i=0;i<deadnum;i++){
			if(player1.intersects(dead[i])){
				//translate_x=0;
				//translate_y=0;
				if(jump){
					fall = true;
					jump = false;
					jumpvar =false;
				}
				System.out.println(i);
			}
			dead[i].draw();
		}
		
		//a doorj is the spike, and i just copied the dying process from dead 
		if(player1.intersects(doorj)){
			if(jump){
				fall = true;
				jump = false;
				jumpvar =false;
			}
		}
		
		// moving deads for the gauntlet type part of the level
		for(int i=9;i<15;i++){
			if(dead[i].getY()+dead[i].getHeight()>=900){
				dead[i].up=true;
			}
			
			if(dead[i].getY()<=718){
				dead[i].up=false;
			}
			if(dead[i].up)
			{
				if(i%2 ==0)
					dead[i].setY(dead[i].getY()-5);
				else
					dead[i].setY(dead[i].getY()-8);
				
			}
			else{
				
				if(i%2 ==0)
					dead[i].setY(dead[i].getY()+5);
				else
					dead[i].setY(dead[i].getY()+8);
			}
		
			dead[i].draw();
		}
		
		//moving for the falling gauntlet part of the level
		for(int i=25; i<34;i++)
		{
			if(dead[i].getX()>=378){
				dead[i].right=false;
			}
			
			if(dead[i].getX()<=18){
				dead[i].right=true;
			}
			if(dead[i].right)
			{
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()+3);
				else
					dead[i].setX(dead[i].getX()+2);
				
			}
			else{
				
				if(i%2 ==0)
					dead[i].setX(dead[i].getX()-3);
				else
					dead[i].setX(dead[i].getX()-2);
			}
		
			dead[i].draw();
		}
		
				
		//a grav is a gravity texture and this is how they are drawn and dealt with interactions
		for(int i=0;i<gravnum;i++){
			if(player.under(grav[i])&&!gravity&&!jump){
				gravity = !gravity;
				DIRlock = false;
				
				fall = true;
			}
			
			if(player.on(grav[i])&&gravity&&!jump){
				gravity = !gravity;
				DIRlock = false;
				
				fall=true;
			}
			grav[i].draw();
		}
		
		
		//a bit is a blip that spawns gravity flippers and they are monitored with blueFounds
		if(player.intersects(bit[0])||player.contains(bit[0])){
			blueFound = true;
		}
		if(player.intersects(bit[1])||player.contains(bit[1])){
			blueFound2 = true;
			
		}
		if(player.intersects(bit[2])||player.contains(bit[2])){
			blueFound3 = true;
			
		}
		
		//a gold is a coin texture and they are monitored with goldFounds
		if(player.intersects(gold[0])||player.contains(gold[0])&&!goldFound){
			
			if(!goldFound)
				goldCount++;
			goldFound=true;
		}
		
		if(player.intersects(gold[1])||player.contains(gold[1])&&!goldFound2){
			
			if(!goldFound2)
				goldCount++;
			goldFound2=true;
		}
		if(player.intersects(gold[2])||player.contains(gold[2])&&!goldFound3){
			
			if(!goldFound3)
				goldCount++;
			goldFound3=true;
		}
		if(player.intersects(gold[3])||player.contains(gold[3])&&!goldFound4){
			
			if(!goldFound4)
				goldCount++;
			goldFound4=true;
		}
		if(player.intersects(gold[4])||player.contains(gold[4])&&!goldFound5){
			if(!goldFound5)
				goldCount++;
			goldFound5=true;
			
		}
		
		
		if(blueFound){
			appear[0].draw();
		}
		else{
			bit[0].draw();
		}
			
		if(blueFound2){
			appear[1].draw();
		}
		else{
			bit[1].draw();
		}
		if(blueFound3){
			appear[2].draw();
		}
		else{
			bit[2].draw();
		}
		
		//drawing of found and unfound coins
		if(!goldFound){
			gold[0].draw();
		}
		if(!goldFound2){
			gold[1].draw();
		}
		
		if(!goldFound3){
			gold[2].draw();
		}
		
		if(!goldFound4){
			gold[3].draw();
		}
		if(!goldFound5){
			gold[4].draw();
		}
		
			
	
		
		//a appear is a gravity flip that is spawned by a blip, this is how interactions are handled
		if(player.on(appear[0])&&blueFound){
			player.setY(appear[0].getY()-player.getHeight());
			player1.setY(appear[0].getY()-player1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		if(player.on(appear[1])&&blueFound2){
			player.setY(appear[1].getY()-player.getHeight());
			player1.setY(appear[1].getY()-player1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		if(player.on(appear[2])&&blueFound3){
			player.setY(appear[2].getY()-player.getHeight());
			player1.setY(appear[2].getY()-player1.getHeight()-bumper);
			gravity = !gravity;
			DIRlock = false;
			
			fall = true;
		}
		
		
		//this is how ice sliding is handled
		if(DIRlock){
			if(lastDIR ==1){
				player.setX(player.getX() -3);
				player1.setX(player1.getX() -3);
				translate_x +=3;
			}
			else if(lastDIR==2){
				player.setX(player.getX() +3);
				player1.setX(player.getX() +3);
				translate_x -=3;
			}
			//bounce off wall
			if(player.intersects(wall)){
				if(lastDIR==1){
					lastDIR=2;
				}
				if(lastDIR==2){
					lastDIR=1;
				}
			}
		}
		/*
		for(int i=0;i<5;i++){
			lights[i].draw();
			if(lights[i].on){
				//light[i].draw();
				
			}
		}
		
		*/
		
		
		//this is the ledge attatched to the spike
		if(player.on(ledge)&&!jump){
			player.setY(ledge.getY()-player.getHeight());
			player1.setY(ledge.getY()-player1.getHeight()-bumper);
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			win = true;
			ledgeFall = true;
		}
		
		
		/*
		if(player.on(ledge)&&!jump){
			player.setY(ledge.getY()-player.getHeight());
			fall = false;
			onGround=true;
			onCliff = true;
			DIRlock = false;
			if(goldFound){
				if(!lights[0].on){
					lights[0].on=true;
					//light[0].draw();
					goldCount--;
				}
			}
			if(goldFound2){
				if(!lights[1].on){
					lights[1].on=true;
					//light[1].draw();
					goldCount--;
				}
			}
			if(goldFound3){
				if(!lights[2].on){
					lights[2].on=true;
					//light[2].draw();
					goldCount--;
				}
			}
			if(goldFound4){
				if(!lights[3].on){
					lights[3].on=true;
					//light[3].draw();
					goldCount--;
				}
			}
			if(goldFound5){
				if(!lights[4].on){
					lights[4].on=true;
					//light[4].draw();
					goldCount--;
					
				}
			}
			
			if(goldFound&&goldFound2&&goldFound3&&goldFound4&&goldFound5){
				win=true;
			}
			
		}
		*/
		
		
		
		doorj.draw();
		ledge.draw();
		gem.draw();
		player1.draw();
		player.draw();
		
		glPopMatrix();
		
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		
		return delta;
		
	}
	
	private long getTime(){
		return(Sys.getTime() *1000/ Sys.getTimerResolution());
		
	}
	
	
	//loading textures
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
}