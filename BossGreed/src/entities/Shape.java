package entities;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_LINE_STIPPLE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineStipple;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public abstract class Shape
{
	public boolean visible = true, touched, selected, user, removeMe = false;
	public boolean solid = false, moving = false, upDown = true,
			downRight = true;
	public double dx, dy, x, y, width, height, endPos, startPos, partnerX,
			partnerY, moveSpeed;
	protected int code;
	public String name;

	public Texture pic;					// for drawing in the level editor
	public static int BORDER = 5;		// for selecting in the level editor

	// -- these are only used in some classes, but I put them here so they can be 
	// -- accessed through a common Shape variable. By default they're all 0 or false,
	// -- so I only set them to true (or 5 or whatever) in the individual classes' constructor.
	public int i, type;
	public boolean on, vert, alive;

	public String tileSet = "Default";
	public int editorPage = 1;
	public int displayOrder = 3;

	public int defaultWidth, defaultHeight, action;

	public Shape partner;

	public boolean faded = false;

	public Shape(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void interact(Box player);

	public abstract void draw();

	public abstract boolean intersects(Shape other);

	public void action()
	{
		System.out.println("Actioning");
		if (action == 1)
			this.visible = true;
		if (action == 2)
			this.visible = false;
		if (action == 3)
			this.moving = true;
	}

	public void editorDraw()
	{
		if (name.equals("Sky"))
			this.draw();
		else
		{
			textureStart();
			pic.bind();
			textureVertices();
		}
	}

	public void setPic(Texture tex)
	{
		pic = tex;
	}

	public static Shape load(ObjectInputStream IS, int shapeCode)
	{
		Shape temp = new Arrow(0, 0, 0, 0);
		if (shapeCode == 1)
			temp = new Arrow(0, 0, 0, 0);
		else if (shapeCode == 2)
			temp = new ArrowKey(0, 0, 0, 0);
		else if (shapeCode == 3)
			temp = new Bat(0, 0, 0, 0);
		else if (shapeCode == 4)
			temp = new Box(0, 0, 0, 0);
		else if (shapeCode == 5)
			temp = new Brick(0, 0, 0, 0);
		else if (shapeCode == 6)
			temp = new Cloud(0, 0, 0, 0);
		else if (shapeCode == 7)
			temp = new Coin(0, 0, 0, 0);
		else if (shapeCode == 8)
			temp = new Dead(0, 0, 0, 0);
		else if (shapeCode == 9)
			temp = new Doorjam(0, 0, 0, 0);
		else if (shapeCode == 10)
			temp = new Gem(0, 0, 0, 0);
		else if (shapeCode == 11)
			temp = new Grav(0, 0, 0, 0);
		else if (shapeCode == 12)
			temp = new Hang(0, 0, 0, 0);
		else if (shapeCode == 13)
			temp = new Ice(0, 0, 0, 0);
		else if (shapeCode == 14)
			temp = new Lbox(0, 0, 0, 0);
		else if (shapeCode == 15)
			temp = new Ledge(0, 0, 0, 0);
		else if (shapeCode == 16)
			temp = new Loff(0, 0, 0, 0);
		else if (shapeCode == 17)
			temp = new News(0, 0, 0, 0);
		else if (shapeCode == 18)
			temp = new Rope(0, 0, 0, 0);
		else if (shapeCode == 19)
			temp = new Sky(0, 0, 0, 0);
		else if (shapeCode == 20)
			temp = new Skyline(0, 0, 0, 0);
		else if (shapeCode == 21)
			temp = new Text(0, 0, 0, 0);
		else if (shapeCode == 22)
			temp = new Wall(0, 0, 0, 0);
		else if (shapeCode == 23)
			temp = new Wheel(0, 0, 0, 0);
		else if (shapeCode == 24)
			temp = new Cactus(0, 0, 0, 0);
		else if (shapeCode == 25)
			temp = new Platform(0, 0, 0, 0);
		else if (shapeCode == 26)
			temp = new Hills(0, 0, 0, 0);
		else if (shapeCode == 27)
			temp = new Bush(0, 0, 0, 0);
		else if (shapeCode == 28)
			temp = new Map(0,0,0,0);
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

			temp.partnerX = IS.readDouble();
			temp.partnerY = IS.readDouble();
			temp.endPos = IS.readDouble();
			temp.startPos = IS.readDouble();
			temp.moveSpeed = IS.readDouble();

			temp.visible = IS.readBoolean();
			temp.upDown = IS.readBoolean();
			temp.downRight = IS.readBoolean();
			temp.moving = IS.readBoolean();

			temp.i = IS.readInt();
			temp.type = IS.readInt();
			temp.action = IS.readInt();
			//	temp.displayOrder = IS.readInt();

			temp.on = IS.readBoolean();
			temp.vert = IS.readBoolean();
			temp.alive = IS.readBoolean();
			temp.solid = IS.readBoolean();
		}
		catch (IOException e)
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

			OS.writeDouble(temp.partnerX);
			OS.writeDouble(temp.partnerY);
			OS.writeDouble(temp.endPos);
			OS.writeDouble(temp.startPos);
			OS.writeDouble(temp.moveSpeed);

			OS.writeBoolean(temp.visible);
			OS.writeBoolean(temp.upDown);
			OS.writeBoolean(temp.downRight);
			OS.writeBoolean(temp.moving);

			OS.writeInt(temp.i);
			OS.writeInt(temp.type);
			OS.writeInt(temp.action);
			OS.writeInt(temp.displayOrder);

			OS.writeBoolean(temp.on);
			OS.writeBoolean(temp.vert);
			OS.writeBoolean(temp.alive);
			OS.writeBoolean(temp.solid);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void textureStart()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		if (!faded)
			glColor4d(1, 1, 1, 1);
		else
			glColor4d(1, 1, 1, .7);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void textureVertices()
	{
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2d(this.x, this.y);
		glTexCoord2f(1, 0);
		glVertex2d(this.x + this.width, this.y);
		glTexCoord2f(1, 1);
		glVertex2d(this.x + this.width, this.y + this.height);
		glTexCoord2f(0, 1);
		glVertex2d(this.x, this.y + this.height);
		glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		if (this.selected)
		{
			drawBorder(false);
			if (partner != null)
				partner.drawBorder(true);
		}
		if (faded)
			glColor4d(1, 1, 1, 1);	// fix it back
	}

	public void drawHitbox()
	{
		glBegin(GL_LINE_LOOP);
		glVertex2d(x, y);
		glVertex2d(x + width, y);
		glVertex2d(x + width, y + height);
		glVertex2d(x, y + height);
		glEnd();
	}

	// for selecting in the level editor
	public void drawBorder(boolean blue)
	{
		// copied most of the following from http://forums.inside3d.com/viewtopic.php?t=1326
		if (blue)
			glColor3f(0, 0, 1); // blue
		else
			glColor3f(1, 0, 0); // red

		glLineWidth(2);  // Set line width to 2
		glLineStipple(1, (short) 0xf0f0);  // Repeat count, repeat pattern
		glEnable(GL_LINE_STIPPLE); // Turn stipple on

		glBegin(GL_LINE_LOOP);
		glVertex2d(x - BORDER, y - BORDER);
		glVertex2d(x + BORDER + width, y - BORDER);
		glVertex2d(x + BORDER + width, y + BORDER + height);
		glVertex2d(x - BORDER, y + BORDER + height);
		glEnd();
		if (action == 3)
		{
			glBegin(GL_LINE_LOOP);
			double endPoint = endPos;
			if (!downRight)
				endPoint = startPos;
			//System.out.println("StartPos, EndPos, endPoint: " + startPos + ", " + endPos + ", " + endPoint);
			if (upDown)
			{
				glVertex2d(x - BORDER, endPoint - BORDER);
				glVertex2d(x + BORDER + width, endPoint - BORDER);
				glVertex2d(x + BORDER + width, endPoint + BORDER + height);
				glVertex2d(x - BORDER, endPoint + BORDER + height);
			} else
			{
				glVertex2d(endPoint - BORDER, y - BORDER);
				glVertex2d(endPoint + BORDER + width, y - BORDER);
				glVertex2d(endPoint + BORDER + width, y + BORDER + height);
				glVertex2d(endPoint - BORDER, y + BORDER + height);
			}
			glEnd();
		}

		glDisable(GL_LINE_LOOP); // Turn it back off
		glDisable(GL_LINE_STIPPLE); // Turn it back off
		glEnd();
	}

	public boolean isVisible()
	{
		return visible;
	}

	public boolean contains(Shape other)
	{
		return ((this.getX() < other.getX())
				&& ((this.getX() + this.getWidth()) > (other.getX() + other
						.getWidth())) && this.getY() < other.getY() && (this
				.getY() + this.getHeight()) > (other.getY() + other.getHeight()));
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
		this.width = width;
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
