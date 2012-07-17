import java.awt.Color;

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
		doFill(piece, piece.getColor().getRGB());		
	}
	
	public void eraseTrail(Piece piece)
	{
		doFill(piece, Color.TRANSLUCENT);
	}
	
	public void ghostFill(Piece piece)
	{
		doFill(piece, Color.gray.getRGB());
	}
	
	// this private method does all the work, but you can
	// only call it from the two public methods
	private void doFill(Piece piece, int color)
	{
		int [][] PieceGrid = piece.getGrid();
		int x = piece.getGridX(), y = piece.getGridY(), size, px = 0, py = 0;
		size = piece.getGridSize();
	
		for (int i = x; i < (x + size); i++)
		{
			py = 0;
			for (int j = y; j < (y + size); j++)
			{
				if(PieceGrid[px][py] == 1 && j >= 0 && j < 20 && i >= 0 && i <= 9)
						grid[j][i] =  color;
				py++;
			}
			px++;
		}
	}
		
}