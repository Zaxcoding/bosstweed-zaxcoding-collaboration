/* My idea is to basically just keep track of the
 * 3x3 grid and where it fits into that, (or 4x4 for line)
 * and also the bottom left x and y of the grid, 
 * with all other coordinates derived relative to that */

public abstract class Piece
{
	protected int [][] grid;

	protected int gridSize, gridX, gridY;	
	
	public abstract boolean canMoveLeft(GameBoard board);
	 
	public abstract boolean canMoveRight(GameBoard board);
	 
	public abstract boolean canMoveDown(GameBoard board);
	 
	public abstract void spawn();
	
	public abstract void rotateL();
	
	public abstract void rotateR();
		
	public int [][] getGrid()
	{
		return grid;
	}
	
	public int getGridSize()
	{
		return gridSize;
	}
	
	public int getGridX()
	{
		return gridX;
	}
	
	public int getGridY()
	{
		return gridY;
	}
	
	public void moveLeft(GameBoard board)
	{
		if (canMoveLeft(board) && gridX > board.MAX_LEFT)
			gridX--;
	}
	
	public void moveRight(GameBoard board)
	{
		if (canMoveRight(board) && gridX < board.MAX_RIGHT)
			gridX++;
	}
	
	public void moveDown(GameBoard board)
	{
		if (canMoveDown(board) && gridY < board.HEIGHT)
			gridY++;
	}
}
	