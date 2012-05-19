public class JPiece extends Piece
{	
	/* 0000
	 * 0010
	 * 0010
	 * 0110 */
	
	//position indicator so hardcoding rotations will work, we can rotate based on current position
	int position ;
	
	//Assuming that grid indicies start at 1
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		grid[2][4] = 1;
		grid[3][2] = 1;
		grid[3][3] = 1;
		grid[3][4] = 1;
		position = 1;
	}
	
	public boolean canMoveLeft(GameBoard board)
	{}
		
	public abstract boolean canMoveRight(GameBoard board)
	{}
	
	public abstract boolean canMoveDown(GameBoard board)
	{}
	 
	public int [][] rotateL()
	{
	
		// as in counterclockwise
		clearGrid();
	 	grid = new int[gridSize][gridSize];
	 	int p = getPosition();
		if(p ==1)
	 	{
	 		grid[3][1] = 1;
			grid[3][2] = 1;
			grid[3][3] = 1;
			grid[4][3] = 1; 
		 	setPosition(4);
	 		/* 0000
	 		 * 0000
	 	 	 * 1110
	 		 * 0010 */
		}
		else if(p == 2)
		{
		 	grid[2][4] = 1;
			grid[3][2] = 1;
			grid[3][3] = 1;
			grid[3][4] = 1;
			setPosition(1);
			/* 0000
	 		 * 0010
			 * 0010
	 		 * 0110 */
		}
		else if(p == 3)
		{
		 	grid[1][3] = 1;
			grid[1][4] = 1;
			grid[2][4] = 1;
			grid[3][4] = 1;
		 	setPosition(2);
		   /* 0000
	 		* 0000
	 	    * 1000
	 		* 1110 */
		}
		else if(p == 4)
		{
			grid[3][2] = 1;
			grid[2][2] = 1;
			grid[2][3] = 1;
			grid[2][4] = 1; 
		 	setPosition(3);
	 		/* 0000
	 		 * 0110
	 	 	 * 0100
	 		 * 0100 */
	 	} 
		
	}
	
	public int [][] rotateR();
	{
		// as in clockwise
	 	// this is the safest way i think just because the bottom should always be the bottom
	 	// is there need for clear grid?
	 	clearGrid();
	 	grid = new int[gridSize][gridSize];
	 	int p = getPosition();
		if(p ==1)
	 	{
	 	 	grid[1][3] = 1;
			grid[1][4] = 1;
			grid[2][4] = 1;
			grid[3][4] = 1;
		 	setPosition(2);
		   /* 0000
	 		* 0000
	 	    * 1000
	 		* 1110 */
		}
		else if(p == 2)
		{
		 	grid[3][2] = 1;
			grid[2][2] = 1;
			grid[2][3] = 1;
			grid[2][4] = 1; 
		 	setPosition(3);
	 		/* 0000
	 		 * 0110
	 	 	 * 0100
	 		 * 0100 */
		}
		else if(p == 3)
		{
		 	grid[3][1] = 1;
			grid[3][2] = 1;
			grid[3][3] = 1;
			grid[4][3] = 1; 
		 	setPosition(4);
	 		/* 0000
	 		 * 0000
	 	 	 * 1110
	 		 * 0010 */
		}
		else if(p == 4)
		{
			grid[2][4] = 1;
			grid[3][2] = 1;
			grid[3][3] = 1;
			grid[3][4] = 1;
			setPosition(1);
			/* 0000
	 		 * 0010
			 * 0010
	 		 * 0110 */
	 	}
	}
	
	public int getPosition();
	{
		return position;
	}
	
	public void setPosition(int p);
	{
		position = p;
	}
	
}
