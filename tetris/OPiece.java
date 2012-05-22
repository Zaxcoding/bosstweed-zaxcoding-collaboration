public class OPiece extends Piece
{		
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.YELLOW.getRGB());
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
		grid[2][2] = 1;
		grid[2][3] = 1;
		grid[3][2] = 1;
		grid[3][3] = 1;
	}
}