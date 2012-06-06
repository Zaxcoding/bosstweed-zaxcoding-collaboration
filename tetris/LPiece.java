import java.awt.Color;


public class LPiece extends Piece
{	
	/* 010
	 * 010
	 * 011 */

	public Piece clone()
	{
		Piece clone = new LPiece();
		clone.position = this.position;	
		clone.spawn();
		clone.gridX = this.gridX;
		clone.gridY = this.gridY;
		return clone;
	}

	public void spawn()
	{
		gridSize = 3;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.BLUE.getRGB());
	}
	
	public void setPiece()
	{
		if (position == 1)
		{
			grid[0][0] = 1;
			grid[1][0] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			/* 100
			 * 100
	 		 * 110 */
		}
		else if (position == 2)
		{
			grid[0][0] = 1;
			grid[1][0] = 1;
			grid[0][1] = 1;
			grid[0][2] = 1;
		   /* 111
	 	    * 100
	 		* 000 */
		}
		else if (position == 3)
		{
			grid[0][2] = 1;
			grid[0][1] = 1;
			grid[1][2] = 1;
			grid[2][2] = 1; 
	 		/* 011
	 	 	 * 001
	 		 * 001 */
		}
		else if (position == 4)
		{
			grid[1][2] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1; 
	 		/* 000
	 	 	 * 001
	 		 * 111 */
		}
	}
}