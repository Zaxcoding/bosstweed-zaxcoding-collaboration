public class TPiece extends Piece
{	
	/* 0000
	 * 0000
	 * 0100
	 * 1110 */
	
	//position indicator so hardcoding rotations will work, we can rotate based on current position
	int position;
	
	//Assuming that grid indicies start at 1
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		grid[4][1] = 1;
		grid[4][2] = 1;
		grid[4][3] = 1;
		grid[3][2] = 1;
		position = 1;
		/* 0000
	 	 * 0000
	 	 * 0100
	 	 * 1110 */
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
		int [][] changedGrid = new int[gridSize][gridSize];
		int [][] newGrid;
		newGrid = new int[gridSize][gridSize];
		clearGrid();
		int p = getPosition();
		if(d == 1)
		{
			if(p ==1 )
			{
				changedGrid = setPiece(2,newGrid);
			}
			else if(p ==2)
			{
				changedGrid = setPiece(3,newGrid);
			}
			else if(p == 3)
			{
				changedGrid = setPiece(4,newGrid);
			}
			else if(p==4)
			{
				changedGrid = setPiece(1,newGrid);
			}
		}
		else if(d == -1)
		{
			if(p ==1 )
			{
				changedGrid = setPiece(4,newGrid);
			}
			else if(p ==2)
			{
				changedGrid = setPiece(1,newGrid);
			}
			else if(p == 3)
			{
				changedGrid = setPiece(2,newGrid);
			}
			else if(p==4)
			{
				changedGrid = setPiece(3,newGrid);
			}
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
	
	public int[][] setPiece(int d,int [][] newGrid)
	{
		if(d==1)
		{
			grid[4][1] = 1;
			grid[4][2] = 1;
			grid[4][3] = 1;
			grid[3][2] = 1;
			setPosition(d);
			/* 0000
	 		 * 0000
	 		 * 0100
	 		 * 1110 */
		}
		else if(d==2)
		{
			grid[2][1] = 1;
			grid[3][1] = 1;
			grid[4][1] = 1;
			grid[3][2] = 1;
			setPosition(d);
			/* 0000
	 		 * 1000
	 		 * 1100
	 		 * 1000 */
		}
		else if(d==3)
		{
			newGrid[3][1] = 1;
			newGrid[3][2] = 1;
			newGrid[3][3] = 1;
			newGrid[4][2] = 1; 
		 	setPosition(d);
	 		/* 0000
	 		 * 0000
	 	 	 * 1110
	 		 * 0100 */
		}
		else if(d==4)
		{
			newGrid[2][3] = 1;
			newGrid[3][3] = 1;
			newGrid[4][3] = 1;
			newGrid[3][2] = 1; 
		 	setPosition(d);
	 		/* 0000
	 		 * 0010
	 	 	 * 0110
	 		 * 0010 */
		}
		return newGrid;
	}

}

