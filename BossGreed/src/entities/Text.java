package entities;

import game.GameOn;
import game.GameShell;
import static org.lwjgl.opengl.GL11.*;


public class Text extends Shape
{
	public Text(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 21;
		name = "Text";
		defaultWidth = 256;
		defaultHeight = 48;
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			GameOn.words.bind();
		}
		else if(this.type==1){
			GameOn.words2.bind();
		}
		else if(this.type==2){
			GameOn.words3.bind();
		}
		else if(this.type==3){
			GameOn.words4.bind();
		}
		else if(this.type==4){
			GameOn.words5.bind();
		}
		else if(this.type==5){
			GameOn.words6.bind();
		}
		else if(this.type==6){
			GameOn.words7.bind();
		}
		else if(this.type==7){
			GameOn.words8.bind();
		}
		else if(this.type==8){
			GameOn.words9.bind();
		}
		else if(this.type==9){
			GameOn.words10.bind();
		}
		else if(this.type==10){
			GameOn.words11.bind();
		}
		else if(this.type==11){
			GameOn.words12.bind();
		}
		else if(this.type==12){
			GameOn.words13.bind();
		}
		else if(this.type==13){
			GameOn.words14.bind();
		}
		else if(this.type==14){
			GameOn.words15.bind();
		}
		else if(this.type==15){
			GameOn.words16.bind();
		}
		else if(this.type==16){
			GameOn.words17.bind();
		}
		else if(this.type==17){
			GameOn.words18.bind();
		}
		else if(this.type==18){
			GameOn.words19.bind();
		}
		else if(this.type==19){
			GameOn.words20.bind();
		}
		else if(this.type==20){
			GameOn.words21.bind();
		}
		else if(this.type==21){
			GameOn.words22.bind();
		}
		else if(this.type==22){
			if(this.i<30){
				glColor4d(1.0,1,1,0);
			}
			else if(this.i>=30 && this.i<60){
				GameOn.p.bind();
			}
			else if(this.i>=60 && this.i<90){
				GameOn.pr.bind();
			}
			else if(this.i>=90 && this.i<120){
				GameOn.pre.bind();
			}
			else if(this.i>=120 && this.i<150){
				GameOn.pres.bind();
			}
			else if(this.i>=150 && this.i<350){
				GameOn.press.bind();
	/*			if(i>=180&&i<349){
					GameOn.spaceDraw = true;
				}
				if(this.i==349){
					this.i=0;
					GameOn.spaceDraw = false;
				}
	*/			
			}
			i++;
		}
		textureVertices();
	}

	
	public void drawShell()
	{
		textureStart();
		
		if(this.type==0){
			GameShell.words.bind();
		}
		else if(this.type==1){
			GameShell.words2.bind();
		}
		else if(this.type==2){
			GameShell.words3.bind();
		}
		else if(this.type==3){
			GameShell.words4.bind();
		}
		else if(this.type==4){
			GameShell.words5.bind();
		}
		else if(this.type==5){
			GameShell.words6.bind();
		}
		else if(this.type==6){
			GameShell.words7.bind();
		}
		else if(this.type==7){
			GameShell.words8.bind();
		}
		else if(this.type==8){
			GameShell.words9.bind();
		}
		else if(this.type==9){
			GameShell.words10.bind();
		}
		else if(this.type==10){
			GameShell.words11.bind();
		}
		else if(this.type==11){
			GameShell.words12.bind();
		}
		else if(this.type==12){
			GameShell.words13.bind();
		}
		else if(this.type==13){
			GameShell.words14.bind();
		}
		else if(this.type==14){
			GameShell.words15.bind();
		}
		else if(this.type==15){
			GameShell.words16.bind();
		}
		else if(this.type==16){
			GameShell.words17.bind();
		}
		else if(this.type==17){
			GameShell.words18.bind();
		}
		else if(this.type==18){
			GameShell.words19.bind();
		}
		else if(this.type==19){
			GameShell.words20.bind();
		}
		else if(this.type==20){
			GameShell.words21.bind();
		}
		else if(this.type==21){
			GameShell.words22.bind();
		}
		else if(this.type==22){
			if(this.i<30){
				glColor4d(1.0,1,1,0);
			}
			else if(this.i>=30 && this.i<60){
				GameShell.p.bind();
			}
			else if(this.i>=60 && this.i<90){
				GameShell.pr.bind();
			}
			else if(this.i>=90 && this.i<120){
				GameShell.pre.bind();
			}
			else if(this.i>=120 && this.i<150){
				GameShell.pres.bind();
			}
			else if(this.i>=150 && this.i<350){
				GameShell.press.bind();
				if(i>=180&&i<349){
					GameShell.spaceDraw = true;
				}
				if(this.i==349){
					this.i=0;
					GameShell.spaceDraw = false;
				}
				
				if(this.i==349){
					this.i=0;
				}
			}
			i++;
		}
		else if(this.type == 23){
			GameShell.words23.bind();
		}
		textureVertices();
	}
	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

	@Override
	public void interact(Box player) 
	{
		// nothing
	}
}
