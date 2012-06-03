import java.awt.Color;

public class OPiece extends Piece
{		
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.YELLOW.getRGB());
	}
	
	public void setPiece()
	{
		grid[1][1] = 1;
		grid[1][2] = 1;
		grid[2][1] = 1;
		grid[2][2] = 1;
	}
}