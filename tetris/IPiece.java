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
		/* 0010
	 	* 0010
	 	* 0010
	 	* 0010 */
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
			grid[3][0] = 1;
		 	grid[3][1] = 1;
		 	grid[3][2] = 1;
			grid[3][3] = 1; 	
			/* 0000
	 	 	 * 0000
	 	 	 * 0000
	 		 * 1111 */
		}
	}
}