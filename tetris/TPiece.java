public class TPiece extends Piece
{	
	/* 0000
	 * 0100
	 * 1110 */
	
	public void spawn()
	{
		gridSize = 3;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		/* 000
	 	 * 010
	 	 * 111 */
	}
	/*
	public boolean canMoveLeft(GameBoard board)
	{}
		
	public abstract boolean canMoveRight(GameBoard board)
	{}
	
	public abstract boolean canMoveDown(GameBoard board)
	{}
	*/
	
	public void setPiece()
	{
		if (position == 1)
		{
			grid[1][1] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
			/* 000
	 		 * 010
	 		 * 111 */
		}
		else if (position == 2)
		{
			grid[0][0] = 1;
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][0] = 1;
			/* 100
	 		 * 110
	 		 * 100 */
		}
		else if (position == 3)
		{
		    grid[1][0] = 1;
		    grid[1][1] = 1;
		    grid[1][2] = 1;
		    grid[2][1] = 1; 
		 	/* 000
	 	 	 * 111
	 		 * 010 */
		}
		else if (position == 4)
		{
			grid[0][2] = 1;
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][2] = 1;
	 		/* 001
	 	 	 * 011
	 		 * 001 */
		}
	}
}