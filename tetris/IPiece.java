import java.awt.Color;

public class IPiece extends Piece
{	
	/* 0010
	 * 0010
	 * 0010
	 * 0010 */
		
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.CYAN.getRGB());
		/* 0010
	 	 * 0010
	 	 * 0010
	 	 * 0010 */
	}
	
	public void setPiece()
	{
		if (position == 1 || position == 3)
		{
			grid[0][2] = 1;
			grid[1][2] = 1;
			grid[2][2] = 1;
			grid[3][2] = 1;			
			/* 0010
		 	 * 0010
	 		 * 0010
	 		 * 0010 */
	 	 	
		}
		else if (position == 2 || position == 4)
		{
			grid[2][0] = 1;
		 	grid[2][1] = 1;
		 	grid[2][2] = 1;
			grid[2][3] = 1; 	
			/* 0000
	 	 	 * 0000
	 	 	 * 1111
	 		 * 0000 */
		}
	}
}