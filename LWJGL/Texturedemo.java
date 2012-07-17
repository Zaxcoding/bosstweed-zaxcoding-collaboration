import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Texturedemo {
	
	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean selected = false;
	//private volatile randomColorCooldown = false;
	
	private Texture wood;
	
	
	public static void main(String[] args){
		
		new Texturedemo();
	}

	public Texturedemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Texture Demo");
			Display.create();
			
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		wood = loadTexture("wood");
		
		
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,640,480,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			wood.bind();
			
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2i(400,400);
				glTexCoord2f(1,0);
				glVertex2i(450,400);
				glTexCoord2f(1,1);
				glVertex2i(450,450);
				glTexCoord2f(0,1);
				glVertex2i(400,450);
			glEnd();
			
			
			
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			

			
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
	}
	
	private Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("jpg",new FileInputStream(new File("res/" + key + ".jpg")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	class Box{
		public int x,y;
		public boolean selected =false;
		private float colorRed,colorBlue,colorGreen;
		
		Box(int x,int y){
			this.x = x;
			this.y = y;
			
			Random randomGenerator = new Random();
			
			colorRed = randomGenerator.nextFloat();
			colorBlue =randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();
			
		}
		boolean inBounds(int mousex,int mousey){
			if(mousex > x && mousex < x+50 && mousey > y && mousey < y+50 )
				return true;
			else
				return false;
			
			
		}
		void update (int dx,int dy)
		{
			x +=dx;
			y +=dy;
			
		}
		void randomizeColors(){
			Random randomGenerator = new Random();
			
			colorRed = randomGenerator.nextFloat();
			colorBlue =randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();	
		}
		
		void draw() {
			glColor3f(colorRed,colorGreen,colorBlue);
			
			glBegin(GL_QUADS);
				glVertex2f(x,y);
				glVertex2f(x+50,y);
				glVertex2f(x+50,y+50);
				glVertex2f(x,y+50);
			glEnd();
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
