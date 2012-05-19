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
		grid[3][1] = 1;
		grid[3][2] = 1;
		grid[3][3] = 1;
		grid[3][4] = 1;
		position =1;
	}
	
	public boolean canMoveLeft(GameBoard board)
	{}
		
	public abstract boolean canMoveRight(GameBoard board)
	{}
	
	public abstract boolean canMoveDown(GameBoard board)
	{}
	 
	public void rotateL()
	{
		
		clearGrid();
	 	grid = new int[gridSize][gridSize];
		int p = getPosition();
		if(p ==1)
		{
			grid[1][4] = 1;
		 	grid[2][4] = 1;
		 	grid[3][4] = 1;
			grid[4][4] = 1; 
	 	
			setPosition(2);
			
			/* 0000
	 	 	* 0000
	 	 	* 0000
	 		* 1111 */
	 	 	// this is the safest way i think just because the bottom should always be the bottom
	 	 	// is there need for clear grid?
		}
		else if(p==2)
		{
			grid[3][1] = 1;
			grid[3][2] = 1;
			grid[3][3] = 1;
			grid[3][4] = 1;
			position =1;
			
			/* 0010
		 	* 0010
	 		* 0010
	 		* 0010 */
		}
			
	}
	
	public void rotateR();
	{
		//These are the same in this case
		rotateL();
	 	
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
