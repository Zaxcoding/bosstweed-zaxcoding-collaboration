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
	
	public abstract void spawn();
		
	public abstract void setPiece();
	
	public abstract Piece clone();
	
	public Piece()
	{
		position = 1;
		spawn();
	}
	
	public boolean pieceCollision(GameBoard board)
	{
		boolean ans = false;	// by default, no collision

		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (board.grid[gridY + j][gridX + i] != 3)
						if (board.grid[gridY + j][gridX + i] 
									!= Color.gray.getRGB())
							ans = true;
		return ans;
	}
	
	public boolean wallCollision(GameBoard board)
	{
		boolean ans = false;
		
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				if (grid[i][j] != 0)
					if (gridX + i >= 10 || gridX + i < 0 || gridY + j >= 20)
						ans = true;
		return ans;
	}
		
	public boolean canMoveLeft(GameBoard board)
	{
		boolean ans = true;
		gridX--;		// temporarily do the move, then look for problems
	
		ans = !wallCollision(board) && !pieceCollision(board);
						
		gridX++;
		return ans;
	}
	 
	public boolean canMoveRight(GameBoard board)
	{
		boolean ans = true;
		gridX++;		// temporarily do the move, then look for problems
		
		ans = !wallCollision(board) && !pieceCollision(board);
						
		gridX--;
		return ans;
	}
	 
	public boolean canMoveDown(GameBoard board)
	{
		boolean ans = true;
		gridY++;		// temporarily do the move, then look for problems

		ans = !wallCollision(board) && !pieceCollision(board);
				
		gridY--;
		return ans;
	}	
	
	public boolean canRotateR(GameBoard board)
	{
		boolean ans = true;
		rotate(1);		// temporarily do the move, then look for problems
	
		ans = !wallCollision(board) && !pieceCollision(board);
	
		rotate(-1);
		return ans;
	}
	
	public boolean canRotateL(GameBoard board)
	{
		boolean ans = true;
		rotate(-1);		// temporarily do the move, then look for problems
	
		ans = !wallCollision(board) && !pieceCollision(board);	
	
		rotate(1);
		return ans;
	}
		
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
	
	public void setGrid(int [][] newGrid)
	{
		grid = newGrid;
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
		else
		{
			gridX--;
			if (canRotateR(board))
				rotate(1);
			else
			{
				gridX--;
				if (canRotateR(board))
					rotate(1);
				else
				{
					gridX += 3;
					if (canRotateR(board))
						rotate(1);
					else
					{
						gridX++;
						if (canRotateR(board))
							rotate(1);
						else
							gridX -= 2;
					}
				}
			}
		}	
	}
	
	public void rotateL(GameBoard board)
	{
		if (canRotateL(board))
			rotate(-1);
		else
		{
			gridX++;
			if (canRotateL(board))
				rotate(-1);
			else
			{
				gridX++;
				if (canRotateL(board))
					rotate(-1);
				else
				{
					gridX -= 3;
					if (canRotateL(board))
						rotate(-1);
					else
					{
						gridX--;
						if (canRotateL(board))
							rotate(-1);
						else
							gridX += 2;
					}
				}
			}
		}	
	}
	
	private void rotate(int d)
	{
		clearGrid();
		position = (position + d) % 4;
		if (position <= 0)		// since java % can give negative results
			position += 4;
		setPiece();
	}
}