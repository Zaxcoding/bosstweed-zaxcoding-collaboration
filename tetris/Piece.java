/* My idea is to basically just keep track of the
 * 3x3 grid and where it fits into that, (or 4x4 for line)
 * and also the bottom left x and y of the grid, 
 * with all other coordinates derived relative to that */

import java.awt.Color;


public abstract class Piece
{
	protected int [][] grid;

	protected int gridSize, gridX, gridY;	
	
	protected int position;
	
	protected Color color;
	
	public boolean canMoveLeft(GameBoard board)
	{
		boolean ans = true;
		gridX--;		// temporarily do the move, then look for problems
		
		/* loop through, when we find a hit on the piece's grid
		 * (a non-zero spot) see if there's a conflict */
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridX + i < 0)
						ans = false;
		gridX++;
		return ans;
	}
	 
	public boolean canMoveRight(GameBoard board)
	{
		boolean ans = true;
		gridX++;		// temporarily do the move, then look for problems

		/* loop through, when we find a hit on the piece's grid
		 * (a non-zero spot) see if there's a conflict */
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridX + i >= 10)
						ans = false;
		gridX--;
		return ans;
	}
	 
	public boolean canMoveDown(GameBoard board)
	{
		boolean ans = true;
		gridY++;		// temporarily do the move, then look for problems

		/* loop through, when we find a hit on the piece's grid
		 * (a non-zero spot) see if there's a conflict */
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridY + j >= 20)
						ans = false;
		gridY--;
		return ans;
	}	
	
	public boolean canRotateR(GameBoard board)
	{
		boolean ans = true;
		rotate(1);		// temporarily do the move, then look for problems

		/* loop through, when we find a hit on the piece's grid
		 * (a non-zero spot) see if there's a conflict */
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridX + i >= 10 || gridX + i < 0)
						ans = false;
		rotate(-1);
		return ans;
	}
	
	public boolean canRotateL(GameBoard board)
	{
		boolean ans = true;
		rotate(-1);		// temporarily do the move, then look for problems

		/* loop through, when we find a hit on the piece's grid
		 * (a non-zero spot) see if there's a conflict */
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridX + i >= 10 || gridX + i < 0)
						ans = false;
		rotate(1);
		return ans;
	}
		
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
	
	public Color getColor()
	{
		return color;
	}
	
	public void moveLeft(GameBoard board)
	{
		if (canMoveLeft(board))
			gridX--;
	}
	
	public void moveRight(GameBoard board)
	{
		if (canMoveRight(board))
			gridX++;
	}
	
	public void moveDown(GameBoard board)
	{
		if (canMoveDown(board))
			gridY++;
	}
	
	public void clearGrid()
	{
		for(int i = 0; i < gridSize; i++)
			for(int j = 0; j < gridSize; j++)
				grid[i][j] = 0;
	}
	
	public void rotateR(GameBoard board)
	{
		if (canRotateR(board))
			rotate(1);
	}
	
	public void rotateL(GameBoard board)
	{
		if (canRotateL(board))
			rotate(-1);
	}
	
	private void rotate(int d)
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
	//	System.out.println("Position: " + position);
	}
}