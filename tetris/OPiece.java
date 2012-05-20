public class OPiece extends Piece
{		
	public void spawn()
	{
		gridSize = 4;
		gridX = 3;
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
	}
	/*
	public boolean canMoveLeft(GameBoard board)
	{}
		
	public abstract boolean canMoveRight(GameBoard board)
	{}
	
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