public class SPiece extends Piece
{	
	/* 000
	 * 011
	 * 110 */
	
	public void spawn()
	{
		gridSize = 3;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.GREEN.getRGB());
	}
	// So my idea is that since we know the leftmost part of the grid, if it is on the edge
	// of the board,(or in contact with another piece, which will be addressed later) it cant move
	public boolean canMoveLeft(GameBoard board)
	{
		boolean move= true;
		
		if(gridX ==0)//  ||contact();
		{
			move = false;
		}
		
		return move;
	
	}
	// Similarly is that since we know the leftmost part of the grid and the gridsize, if 
	// the addition of these two variables are the rightmost part of the board, or in contact
	// with another piece, which will be addressed later, it cant move
	public boolean canMoveRight(GameBoard board)
	{
		boolean move= true;
		
		if(gridX + gridSize ==9) // ||contact();
		{
			move = false;
		}
		
		return move;
		
	}
	/*
	public abstract boolean canMoveDown(GameBoard board)
	{}
	*/
	
	public void setPiece()
	{
		if (position == 1 || position == 3)
		{
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			/* 000
	 	 	 * 011
	 	 	 * 110 */
		}
		else if (position == 2 || position == 4)
		{
			grid[0][0] = 1;
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][1] = 1;
			/* 100
			 * 110
	 		 * 010 */
		}		
	}
}
