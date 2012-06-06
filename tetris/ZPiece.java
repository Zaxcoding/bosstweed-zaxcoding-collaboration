import java.awt.Color;

public class ZPiece extends Piece
{	
	/* 000
	 * 110
	 * 011 */
		
	public Piece clone()
	{
		Piece clone = new ZPiece();
		clone.position = this.position;	
		clone.spawn();
		clone.gridX = this.gridX;
		clone.gridY = this.gridY;
		return clone;
	}	
		
	public void spawn()
	{
		gridSize = 3;
		gridX = 3;
		gridY = -2;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.GREEN.getRGB());
		/* 0000
	 	 * 0000
	 	 * 1100
	 	 * 0110 */
	}

	public void setPiece()
	{
		if (position == 2 || position == 4)
		{
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
			/* 000
	 	 	 * 110
	 	 	 * 011 */
		}
		else if (position == 1 || position == 3)
		{
			grid[0][2] = 1;
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][1] = 1;
			/* 001
			 * 011
	 		 * 010 */
		}
	}
}