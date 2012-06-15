import java.util.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class helloworld {
	
	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean selected = false;
	//private volatile randomColorCooldown = false;
	
	
	
	public static void main(String[] args){
		
		new helloworld();
	}

	public helloworld(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("helloworld");
			Display.create();
			
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		shapes.add(new Box (15,15));
		shapes.add(new Box (100,100));
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,640,480,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			while(Keyboard.next())
			{
				if(Keyboard.getEventKey()== Keyboard.KEY_C && Keyboard.getEventKeyState()){
					shapes.add(new Box(15,15));
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			
			
			
			
			int dy = -Mouse.getDY();
			int dx = Mouse.getDX();
			
			
			
			for (Box box : shapes){
				if(Mouse.isButtonDown(0)&& box.inBounds(Mouse.getX(), 480-Mouse.getY())&& !selected){
					selected = true;
					box.selected = true;
					box.randomizeColors();
				}
				
				if(Mouse.isButtonDown(1)){
					box.selected = false;
					selected = false;
				}
				
				
					
				if(box.selected){
					box.update(dx, dy);
				}
					
				box.draw();
			}
			
			
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		
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
