import java.awt.Color;

public class TPiece extends Piece
{	
	/* 0000
	 * 0100
	 * 1110 */
	
	public Piece clone()
	{
		Piece clone = new TPiece();
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
		gridY = 0;
		grid = new int[gridSize][gridSize];
		setPiece();
		color = new Color(Color.MAGENTA.getRGB());
		/* 000
	 	 * 010
	 	 * 111 */
	}

	public void setPiece()
	{
		if (position == 1)
		{
			grid[1][1] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			grid[2][2] = 1;
			/* 000
	 		 * 010
	 		 * 111 */
		}
		else if (position == 2)
		{
			grid[0][1] = 1;
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][1] = 1;
			/* 010
	 		 * 011
	 		 * 010 */
		}
		else if (position == 3)
		{
		    grid[1][0] = 1;
		    grid[1][1] = 1;
		    grid[1][2] = 1;
		    grid[2][1] = 1; 
		 	/* 000
	 	 	 * 111
	 		 * 010 */
		}
		else if (position == 4)
		{
			grid[0][1] = 1;
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][1] = 1;
	 		/* 010
	 	 	 * 110
	 		 * 010 */
		}
	}
}