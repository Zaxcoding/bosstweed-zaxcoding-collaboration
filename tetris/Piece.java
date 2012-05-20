/* My idea is to basically just keep track of the
 * 3x3 grid and where it fits into that, (or 4x4 for line)
 * and also the bottom left x and y of the grid, 
 * with all other coordinates derived relative to that */

public abstract class Piece
{
	protected int [][] grid;

	protected int gridSize, gridX, gridY;	
	
	protected int position;
	/*
	public abstract boolean canMoveLeft(GameBoard board);
	 
	public abstract boolean canMoveRight(GameBoard board);
	 
	public abstract boolean canMoveDown(GameBoard board);
	*/ 
	public abstract void spawn();
		
	public abstract void setPiece();
	
	public Piece()
	{
		position = 1;
		spawn();
	}
		
	public int [][] getGrid()
	{
		return grid;
	}
	
	public void setGrid(int [][] newGrid)
	{
		grid = newGrid;
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
	
	public int getPosition()
	{
		return position;
	}
	
	public void setPosition(int p)
	{
		position = p;
	}
	
	/*
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
	*/
	public void clearGrid()
	{
		for(int i = 0; i < gridSize; i++)
			for(int j = 0; j < gridSize; j++)
				grid[i][j] = 0;
	}
	
	public void rotateR()
	{
		rotate(1);
	}
	
	public void rotateL()
	{
		rotate(-1);
	}
	
	public void rotate(int d)
	{
		//1 - cw
		//2 - ccw
		// 1 for R, -1 for L
		
		// note- need to also add the rotation checking to make sure
		// it's possible to do this rotate
		clearGrid();
		position = (position + d) % 4;
		if (position <= 0)		// since java % can give negative results
			position += 4;
		setPiece();
		System.out.println("Position: " + position);
	}
}