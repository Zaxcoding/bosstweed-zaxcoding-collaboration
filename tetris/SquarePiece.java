/* My idea is to basically just keep track of the
 * 3x3 grid and where it fits into that, (or 4x4 for line)
 * and also the bottom left x and y of the grid, 
 * with all other coordinates derived relative to that */

public class SquarePiece extends Piece
{	
	/* 0000
	 * 0110
	 * 0110
	 * 0000 */
	
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		grid[2][2] = 1;
		grid[2][3] = 1;
		grid[3][2] = 1;
		grid[3][3] = 1;
	}
	
	public boolean canMoveLeft(GameBoard board)
	{}
		
	public abstract boolean canMoveRight(GameBoard board)
	{}
	
	public abstract boolean canMoveDown(GameBoard board)
	{}
	 
	public abstract void rotateL()
	{
		// nothing to do
	}
	
	public abstract void rotateR();
	{
		// nothing to do
	}
	
}
