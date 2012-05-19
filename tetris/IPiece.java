/* My idea is to basically just keep track of the
 * 3x3 grid and where it fits into that, (or 4x4 for line)
 * and also the bottom left x and y of the grid, 
 * with all other coordinates derived relative to that */

public class IPiece extends Piece
{	
	/* 0010
	 * 0010
	 * 0010
	 * 0010 */
	
	int position;
	
	//Assuming that grid indicies start at 1
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		grid[1][3] = 1;
		grid[2][3] = 1;
		grid[3][3] = 1;
		grid[4][3] = 1;
		position =1;
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
	public void rotate(int d)
	{
		//1 - cw
		//2 - ccw
		clearGrid();
		int [][] changedGrid= new int[gridSize][gridSize];
		int [][] newGrid;
		newGrid = new int[gridSize][gridSize];
		
		int p = getPosition();
		if(p ==1 )
		{
			changedGrid=setPiece(2,newGrid);
		}
		else if(p ==2)
		{
			changedGrid=setPiece(1,newGrid);
		}
		
		setGrid(changedGrid);
	
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void setPosition(int p)
	{
		
		position = p;
	}
	
	public int [][] setPiece(int d, int[][] newGrid)
	{
		if(d==1)
		{
			newGrid[1][3] = 1;
			newGrid[2][3] = 1;
			newGrid[3][3] = 1;
			newGrid[4][3] = 1;
			setPosition(d);
			
			/* 0010
		 	 * 0010
	 		 * 0010
	 		 * 0010 */
	 	 	
		}
		else if(d==2)
		{
			newGrid[4][1] = 1;
		 	newGrid[4][2] = 1;
		 	newGrid[4][3] = 1;
			newGrid[4][4] = 1; 
	 	
			setPosition(d);
			
			/* 0000
	 	 	 * 0000
	 	 	 * 0000
	 		 * 1111 */
		}
		
		return newGrid;
	}
	
}
