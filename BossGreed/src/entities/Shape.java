package entities;

import static org.lwjgl.opengl.GL11.*;
import java.io.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import entities.Shape;

public abstract class Shape
{
	public boolean visible, touched, selected, user, removeMe = false;
	protected double dx, dy, x, y, width, height;
	protected int code;
	public String name;
	
	public Texture pic;					// for drawing in the level editor
	public static int BORDER = 5;		// for selecting in the level editor
	
	// -- these are only used in some classes, but I put them here so they can be 
	// -- accessed through a common Shape variable. By default they're all 0 or false,
	// -- so I only set them to true (or 5 or whatever) in the individual classes' constructor.
	public int i, j, type, init, that;
	public boolean pause, on, up, vert, upp, right, alive, switch1;
	

	public Shape(double x, double y, double width, double height) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
//		this.touched = false;
		this.visible = true;
	}
	
//	public abstract void interact(Player player);
//	public abstract void doYourThing();
	public abstract void draw();
	public abstract boolean intersects(Shape other);
//	public abstract void touch(Player player);
	
	// basically a void method, but also a return for in onGround
/*
	public boolean collision(Player player)
	{
		boolean ans = false;
		if (!this.name.equals("Player"))
		{
			double left = player.x;
			double right = player.x + player.width;
		
			if ((left <= (this.x + this.width) && (right >= this.x)) 
				&& (player.y > this.y)  && (player.y < this.y + this.height) )
			{
				// hit the bottom, stay under instead of flipping to the side
				if (player.y + player.height >= this.y + this.height && player.jumping)
				{
			//		System.out.println("AAAAAAA");
					player.y = this.y + this.height;
					ans = true;
				}
				if (player.y + player.height < this.y + this.height)
				{
				boolean closerToLeft = player.x <= this.x + this.width/2;
				if (closerToLeft)
					player.x = this.x - player.width - 1;
				else
					player.x = this.x + this.width;
				ans = true;
				}
			}
		}
		if (ans)
			this.touch(player);
		
		return ans;
	}
*/
	
	public void editorDraw()
	{
		textureStart();
		pic.bind();
		textureVertices();
	}
	
	public void setPic(Texture tex)
	{
		pic = tex;
	}
	
	public static Shape load(ObjectInputStream IS, int shapeCode)
	{
		Shape temp = new Arrow(0,0,0,0);		
		if (shapeCode == 1)
			temp = new Arrow(0,0,0,0);
		else if (shapeCode == 2)
			temp = new ArrowKey(0,0,0,0);
		else if (shapeCode == 3)
			temp = new Bat(0,0,0,0);
		else if (shapeCode == 4)
			temp = new Box(0,0,0,0);
		else if (shapeCode == 5)
			temp = new Brick(0,0,0,0);
		else if (shapeCode == 6)
			temp = new Cloud(0,0,0,0);
		else if (shapeCode == 7)
			temp = new Coin(0,0,0,0);
		else if (shapeCode == 8)
			temp = new Dead(0,0,0,0);
		else if (shapeCode == 9)
			temp = new Doorjam(0,0,0,0);
		else if (shapeCode == 10)
			temp = new Gem(0,0,0,0);
		else if (shapeCode == 11)
			temp = new Grav(0,0,0,0);
		else if (shapeCode == 12)
			temp = new Hang(0,0,0,0);
		else if (shapeCode == 13)
			temp = new Ice(0,0,0,0);
		else if (shapeCode == 14)
			temp = new Lbox(0,0,0,0);
		else if (shapeCode == 15)
			temp = new Ledge(0,0,0,0);
		else if (shapeCode == 16)
			temp = new Loff(0,0,0,0);
		else if (shapeCode == 17)
			temp = new News(0,0,0,0);
		else if (shapeCode == 18)
			temp = new Rope(0,0,0,0);
		else if (shapeCode == 19)
			temp = new Sky(0,0,0,0);
		else if (shapeCode == 20)
			temp = new Skyline(0,0,0,0);
		else if (shapeCode == 21)
			temp = new Text(0,0,0,0);
		else if (shapeCode == 22)
			temp = new Wall(0,0,0,0);
		else if (shapeCode == 23)
			temp = new Wheel(0,0,0,0);
		
		loadInstanceVars(IS, temp);
		return temp;
	}
	
	public static void loadInstanceVars(ObjectInputStream IS, Shape temp)
	{
		try
		{
			temp.x = IS.readDouble();
			temp.y = IS.readDouble();
			temp.width = IS.readDouble();
			temp.height = IS.readDouble();
			
			temp.visible = IS.readBoolean();
			
			temp.i = IS.readInt();
			temp.j = IS.readInt();
			temp.type = IS.readInt();
			temp.init = IS.readInt();
			temp.that = IS.readInt();
			
			temp.pause = IS.readBoolean();
			temp.on = IS.readBoolean();
			temp.up = IS.readBoolean();
			temp.vert = IS.readBoolean();
			temp.upp = IS.readBoolean();
			temp.right = IS.readBoolean();
			temp.alive = IS.readBoolean();
			temp.switch1 = IS.readBoolean();
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void save(ObjectOutputStream OS, Shape temp)
	{	
		try
		{
			OS.writeInt(temp.code);
			
			OS.writeDouble(temp.x);
			OS.writeDouble(temp.y);
			OS.writeDouble(temp.width);
			OS.writeDouble(temp.height);
	
			OS.writeBoolean(temp.visible);
	
			OS.writeInt(temp.i);
			OS.writeInt(temp.j);
			OS.writeInt(temp.type);
			OS.writeInt(temp.init);
			OS.writeInt(temp.that);
			
			OS.writeBoolean(temp.pause);
			OS.writeBoolean(temp.on);
			OS.writeBoolean(temp.up);
			OS.writeBoolean(temp.vert);
			OS.writeBoolean(temp.upp);
			OS.writeBoolean(temp.right);
			OS.writeBoolean(temp.alive);
			OS.writeBoolean(temp.switch1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public void textureVertices()
	{
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2d(this.x,this.y);
			glTexCoord2f(1,0);
			glVertex2d(this.x+this.width,this.y);
			glTexCoord2f(1,1);
			glVertex2d(this.x+this.width,this.y+this.height);
			glTexCoord2f(0,1);
			glVertex2d(this.x,this.y+this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	public void textureStart()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glColor4d(1.0,1,1,1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	// for selecting in the level editor
	public void drawBorder()
	{
		if (selected)
		{
			// copied most of the following from http://forums.inside3d.com/viewtopic.php?t=1326
			glColor3f(1,0,0); // red

			glLineWidth(2);  // Set line width to 2
			glLineStipple(1, (short)0xf0f0);  // Repeat count, repeat pattern
			glEnable(GL_LINE_STIPPLE); // Turn stipple on

			glBegin(GL_LINE_LOOP); 
				glVertex2d(x - BORDER, y - BORDER);
				glVertex2d(x + BORDER + width, y - BORDER);
				glVertex2d(x + BORDER + width, y + BORDER + height);
				glVertex2d(x - BORDER, y + BORDER + height);
			glEnd ();
			glDisable(GL_LINE_LOOP); // Turn it back off
			glEnd();
		}
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public boolean contains(Shape other)
	{
		return((this.getX()<other.getX()) && ((this.getX()+this.getWidth())>(other.getX()+other.getWidth())) && this.getY()<other.getY() && (this.getY() + this.getHeight())>(other.getY()+other.getHeight()));
	}
	
	// ---- From AbstractMoveableEntity

	public void update(int delta)
	{
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getDX()
	{
		return dx;
	}
	
	public double getDY()
	{
		return dy;
	}
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public void setWidth(double width)
	{
		this.width= width;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setDX(double dx)
	{
		this.dx = dx;
	}
	
	public void setDY(double dy)
	{
		this.dy = dy;
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	//-- End AbstractMoveableEntity

}
