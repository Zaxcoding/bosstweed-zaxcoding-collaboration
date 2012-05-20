public class ZPiece extends Piece
{	
	/* 000
	 * 110
	 * 011 */
		
	public void spawn()
	{
		gridSize = 3;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		/* 0000
	 	 * 0000
	 	 * 1100
	 	 * 0110 */
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
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
			/* 000
	 	 	 * 110
	 	 	 * 011 */
		}
		else if (position == 2 || position == 4)
		{
			grid[0][2] = 1;
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][1] = 1;
			/* 001
			 * 011
	 		 * 010 */
		}
	}
}