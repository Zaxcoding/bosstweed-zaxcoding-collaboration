import java.awt.Color;

public class JPiece extends Piece
{	
	/* 001
	 * 001
	 * 011 */
	
	// position indicator so hardcoding rotations will work, we can rotate
	// based on current position
		
	public Piece clone()
	{
		Piece clone = new JPiece();
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
		color = new Color(Color.ORANGE.getRGB());
	}

	public void setPiece()
	{
		if (position == 1)
		{
			grid[0][2] = 1;
			grid[1][2] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
			/* 001
			 * 001
	 		 * 011 */
		}
		else if (position == 2)
		{
			grid[1][0] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
		   /* 000
	 	    * 100
	 		* 111 */
		}
		else if (position == 3)
		{
			grid[0][0] = 1;
			grid[0][1] = 1; 
			grid[1][0] = 1;
			grid[2][0] = 1;
	 		/* 110
	 	 	 * 100
	 		 * 100 */
		}
		else if (position == 4)
		{
			grid[0][0] = 1;
			grid[0][1] = 1;
			grid[0][2] = 1;
			grid[1][2] = 1; 
	 		/* 111
	 	 	 * 001
	 		 * 000 */
		}
	}
}