public class GameBoard
{
	int [][] grid;
	
	public static final int MIN_LEFT = 0;
	public static final int MAX_RIGHT = 10;
	public static final int HEIGHT = 20;
	
	public GameBoard(int width, int height)
	{
		grid = new int[height][width];		
	}
	
	public void fill(Piece piece)
	{	
		int [][] PieceGrid;
		PieceGrid = piece.getGrid();
		int x, y, size, px = 0, py = 0;
		x = piece.getGridX();
		y = piece.getGridY();
		size = piece.getGridSize();
		
		
		/* this is the weirdest structure ive ever made, its probably not
		 * right but hopefully enough for you to go off of because i know what
		 * i want to do but coding is weird right now so I am under the
		 * impression that the official for loops will go through the 'box' of
		 * the piece grid superimposed on the gameboard, now with that said
		 * this loop should be for the gameboard exclusively...not so fast! i
		 * have added px, and py variables basically as
		 * a regular for loop (starting at 0 going to the end) would progress.
		 * so the px,py will progress through the piecegrid, while when that
		 * finds a truth(1) it will also produce that 1 on the superimposed
		 * grid, showing the 1 in the correct locatio on the gameboard. 
		 */
		for (int i = x; i < (x + size); i++)
		{
			py = 0;
			for (int j=y; j < (y + size); j++)
			{
				if(PieceGrid[px][py] ==1)
						grid[j][i] = piece.getColor().getRGB();
				py++;
			}
			px++;
		}		
	}
}