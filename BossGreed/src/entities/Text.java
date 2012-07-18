package entities;

import game.Game;
import static org.lwjgl.opengl.GL11.*;


public class Text extends Shape
{
	public Text(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		code = 21;
		name = "Text";
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			Game.words.bind();
		}
		else if(this.type==1){
			Game.words2.bind();
		}
		else if(this.type==2){
			Game.words3.bind();
		}
		else if(this.type==3){
			Game.words4.bind();
		}
		else if(this.type==4){
			Game.words5.bind();
		}
		else if(this.type==5){
			Game.words6.bind();
		}
		else if(this.type==6){
			Game.words7.bind();
		}
		else if(this.type==7){
			Game.words8.bind();
		}
		else if(this.type==8){
			Game.words9.bind();
		}
		else if(this.type==9){
			Game.words10.bind();
		}
		else if(this.type==10){
			Game.words11.bind();
		}
		else if(this.type==11){
			Game.words12.bind();
		}
		else if(this.type==12){
			Game.words13.bind();
		}
		else if(this.type==13){
			Game.words14.bind();
		}
		else if(this.type==14){
			Game.words15.bind();
		}
		else if(this.type==15){
			Game.words16.bind();
		}
		else if(this.type==16){
			Game.words17.bind();
		}
		else if(this.type==17){
			Game.words18.bind();
		}
		else if(this.type==18){
			Game.words19.bind();
		}
		else if(this.type==19){
			Game.words20.bind();
		}
		else if(this.type==20){
			Game.words21.bind();
		}
		else if(this.type==21){
			Game.words22.bind();
		}
		else if(this.type==22){
			if(this.i<30){
				glColor4d(1.0,1,1,0);
			}
			else if(this.i>=30 && this.i<60){
				Game.p.bind();
			}
			else if(this.i>=60 && this.i<90){
				Game.pr.bind();
			}
			else if(this.i>=90 && this.i<120){
				Game.pre.bind();
			}
			else if(this.i>=120 && this.i<150){
				Game.pres.bind();
			}
			else if(this.i>=150 && this.i<350){
				Game.press.bind();
				if(i>=180&&i<349){
					Game.spaceDraw = true;
				}
				if(this.i==349){
					this.i=0;
					Game.spaceDraw = false;
				}
			}
			i++;
		}
		textureVertices();
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
