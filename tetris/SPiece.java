import java.awt.Color;


public class SPiece extends Piece
{	
	/* 000
	 * 011
	 * 110 */
	
	public Piece clone()
	{
		Piece clone = new SPiece();
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
		color = new Color(Color.RED.getRGB());
	}
	
	public void setPiece()
	{
		if (position == 2 || position == 4)
		{
			grid[1][1] = 1;
			grid[1][2] = 1;
			grid[2][0] = 1;
			grid[2][1] = 1;
			/* 000
	 	 	 * 011
	 	 	 * 110 */
		}
		else if (position == 1 || position == 3)
		{
			grid[0][0] = 1;
			grid[1][0] = 1;
			grid[1][1] = 1;
			grid[2][1] = 1;
			/* 100
			 * 110
	 		 * 010 */
		}		
	}
}
