//Tetris by bosscoding

//imports and functionality of swing stolen from Yahtzee.java(Zaxcoding) skeleton

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import com.apple.eawt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Tetris
{
	JFrame theWindow;
	Container thePane;
	MyPanel gamePanel;
	MyPanel array [][];
	MyPanel buttonPanel, leftPanel, rightPanel, nextPanel, timePanel;
	MyPanel linesPanel, totalPanel, nextLinePanel, levelPanel;
	JButton newGame, pause, endGame;
	MyListener theListener;
	Timer timer;
	Piece currPiece, nextPiece;
	JLabel gameTime,picLabel;
	GameBoard board = new GameBoard(10,20);
	BufferedImage [] myPicture = new BufferedImage[7];
	
	// all stuff for preferences menu
	Application app;
	AppListener appListener;
	JButton prefLeft, prefRight, prefRotateR, prefRotateL, prefHardDrop;
	JButton prefSave, prefClose;
	JLabel leftLabel, rightLabel, rotateRLabel, rotateLLabel, hardDropLabel;
	JLabel prefFeedback;
	JFrame preferences;
	MyPanel prefPanel;
	boolean changingKeys;	// are you changing the key?
	int changingWhich;		// which control are you changing?
							// 0 = move left, 1 = move right, 2 = rotateR
							// 3 = rotateL, 4 = hard drop
	int [] keys, tempKeys;	// since KeyCodes are just ints, make an array
							// for the current settings and the temporary
							// settings when changing.
	
	
	boolean paused, start, game = true, newpiece = true;
	Random rng; 			// put this here so we can access it all over

	public static void main(String [] args)
	{
		new Tetris();
	}

	public Tetris()
	{
		setup();
		
		int pieceDigit = rng.nextInt(6);		// get rand int
		nextPiece = selectPiece(pieceDigit); 	// and that piece
		currPiece = nextPiece;
		nextPiece = null;
		// new timing structure with its own action listener
		ActionListener timerListener = new ActionListener() 
        { 
        	public void actionPerformed(ActionEvent evt) 
        	{ 
        		if (nextPiece == null)
        		{
        			int rand = rng.nextInt(6);
        			nextPiece = selectPiece(rand); // and that piece
        			picLabel.setIcon(new ImageIcon( myPicture[rand] )); 
        		}
        			
        		// everytime the timer goes off (every second) it will basically move the piece down, with all existing logic
        		board.eraseTrail(currPiece);	
            	gameTime.setText(Double.parseDouble(gameTime.getText()) + 1 + "");
 
				currPiece.moveDown(board);
            	
				if (!currPiece.canMoveDown(board))
				{
					board.fill(currPiece);
					currPiece = nextPiece;
					int rand = rng.nextInt(6);
        			nextPiece = selectPiece(rand); // and that piece
        			picLabel.setIcon(new ImageIcon( myPicture[rand] ));	
				}
				board.fill(currPiece);
				colorPieces();
       		} 
        };
                
        timer = new Timer(500,timerListener);
		timer.start();
	}
	
	/* I changed this method to return a Piece, since we may want to use
	 * it for more than just generating the current piece. We could use it
	 * for previewing the next pieces or other areas where we just want a
	 * particular piece */
	public Piece selectPiece(int a)
	{
		// temp variable, just make it J, then change to what it needs to be
		Piece temp = new JPiece();
		
		if (a == 0)
		{
			temp = new JPiece();
		}
		else if (a == 1)
		{
			temp = new LPiece();
		}
		else if (a == 2)
		{
			temp = new OPiece();
		}
		else if (a == 3)
		{
			temp = new SPiece();
		}
		else if (a == 4)
		{
			temp = new ZPiece();
		}
		else if (a == 5)
		{
			temp = new IPiece();
		}
		else if (a == 6)
		{
			temp = new TPiece();
		}
		
		return temp;
	}
		
	public void setup()
	{
		theWindow = new JFrame("Tetris- by bosscoding");
		thePane = theWindow.getContentPane();
		thePane.setLayout(new GridLayout(1, 1));

		gamePanel = new MyPanel(500, 500, 1, 2);

		leftPanel = new MyPanel(100, 500, 20, 5);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		rightPanel = new MyPanel(400,500, 5, 1);
		nextPanel = new MyPanel(1,1, 2, 1);
		timePanel = new MyPanel(100,100, 2, 1);
		linesPanel = new MyPanel(100,100, 5, 1);
		levelPanel = new MyPanel(100,100, 2, 1);
		buttonPanel = new MyPanel(100,100, 3, 1);

		theListener = new MyListener();
		
		newGame = new JButton("New Game");
		newGame.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(newGame);
		newGame.addActionListener(theListener);

		pause = new JButton("Start Game");
		pause.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(pause);
		pause.addActionListener(theListener);

		endGame = new JButton("End Game");
		endGame.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(endGame);
		endGame.addActionListener(theListener);

		JLabel levelText = new JLabel("Level",SwingConstants.CENTER);
		levelText.setFont(new Font("Serif", Font.ITALIC, 20));
		levelText.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel currentLevel = new JLabel("0",SwingConstants.CENTER);
		currentLevel.setBorder(BorderFactory.createLineBorder(Color.gray));
		currentLevel.setFont(new Font("Serif", Font.ITALIC, 20));
		levelPanel.add(levelText);
		levelPanel.add(currentLevel);

		JLabel lineText = new JLabel("<html><b>Lines</b></html>",
										SwingConstants.CENTER);
		lineText.setFont(new Font("Serif", Font.ITALIC, 20));
		lineText.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel line1Text = new JLabel("Total",SwingConstants.CENTER);
		line1Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line1Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel line2Text = new JLabel("0",SwingConstants.CENTER);
		line2Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line2Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel line3Text = new JLabel("Next Level in",SwingConstants.CENTER);
		line3Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line3Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel line4Text = new JLabel("10",SwingConstants.CENTER);
		line4Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line4Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		linesPanel.add(lineText);
		linesPanel.add(line1Text);
		linesPanel.add(line2Text);
		linesPanel.add(line3Text);
		linesPanel.add(line4Text);

		JLabel timeText = new JLabel("Time",SwingConstants.CENTER);
		timeText.setFont(new Font("Serif", Font.ITALIC, 20));
		timeText.setBorder(BorderFactory.createLineBorder(Color.gray));
		gameTime = new JLabel("0.00",SwingConstants.CENTER); 
		gameTime.setFont(new Font("Serif", Font.ITALIC, 20));
		gameTime.setBorder(BorderFactory.createLineBorder(Color.gray));
		timePanel.add(timeText);
		timePanel.add(gameTime);

		JLabel nextText = new JLabel("<html><center>Next</center></html>"
										,SwingConstants.CENTER);
		nextText.setFont(new Font("Serif", Font.ITALIC, 20));
		nextText.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		try
		{
			myPicture[0] = ImageIO.read(new File("Tetrominoes/J.png"));	
			myPicture[1] = ImageIO.read(new File("Tetrominoes/L.png"));
			myPicture[2] = ImageIO.read(new File("Tetrominoes/O.png"));
			myPicture[3] = ImageIO.read(new File("Tetrominoes/S.png"));
			myPicture[4] = ImageIO.read(new File("Tetrominoes/Z.png"));
			myPicture[5] = ImageIO.read(new File("Tetrominoes/I.png"));
			myPicture[6] = ImageIO.read(new File("Tetrominoes/T.png"));
			
			picLabel = new JLabel("",JLabel.CENTER);
			
			nextPanel.add(nextText);
		    nextPanel.add(picLabel);
		    nextPanel.add(nextText);
		    nextPanel.add(picLabel);
		}
		catch(Exception a)
		{
			System.out.println("pic didnt work :/");
			System.exit(0);
		}
	
		rightPanel.add(nextPanel);
		rightPanel.add(timePanel);
		rightPanel.add(linesPanel);
		rightPanel.add(levelPanel);
		rightPanel.add(buttonPanel);

		array = new MyPanel[20][10];

		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				array[i][j] = new MyPanel(10,10);
			array[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));
			leftPanel.add(array[i][j]);
			}
		}

		gamePanel.add(leftPanel);
		gamePanel.add(rightPanel);

		thePane.add(gamePanel);
		theWindow.pack();
		theWindow.setVisible(true);
		
		gamePanel.setFocusable(true);
		gamePanel.grabFocus();
		gamePanel.addKeyListener(theListener);
	
	
		/*--------------------------------------------------------------------
		 * 					END OF GUI WINDOW SETUP
	   	 *------------------------------------------------------------------*/
			
		paused = true;
		start = false;
		
		// for preferences pane
		
		app = Application.getApplication();
		appListener = new AppListener();
		app.setPreferencesHandler(appListener);
		
		// hardcode in the default control settings
		
		keys = new int[5];
		tempKeys = new int[5];
				
		keys[0] = 65;
		keys[1] = 83;
		keys[2] = 75;
		keys[3] = 76;
		keys[4] = 32;
		
		rng = new Random();		// init the random in setup
	
		initBoard();
		colorPieces();
	
	//	currPiece = new JPiece();
	//	nextPiece = new JPiece();
	}

	public void pause()
	{
		if(!start)
		{
			start = true;
			//timer.start();
		}
		
		paused = !paused;
		if (!paused)
		{
			pause.setText("Pause");
			//timer.start();
		}
		else
		{
			pause.setText("Resume");
			timer.stop();
		}
	}
	
	// this method colors all the pieces on the GameBoard
	public void colorPieces()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
				if (board.grid[i][j] != 0)
					array[i][j].setBackground(new Color(board.grid[i][j]));
	}

	// this simply makes all the labels initially black
	public void initBoard()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
			{
				array[i][j].setBackground(new Color(Color.TRANSLUCENT));
				board.grid[i][j] = Color.TRANSLUCENT;
			}
	}
	
	// this method is more of a hard wipe and is now basically
	// obsolete thanks to GameBoard.eraseTrail()
	public void clearBoard()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
				if (board.grid[i][j] != 0)
				{
					board.grid[i][j] = 0;
					array[i][j].setBackground(new Color(Color.TRANSLUCENT));
				}
	}			

	private class AppListener implements PreferencesHandler
	{
		public void handlePreferences(AppEvent.PreferencesEvent e)
		{
			preferences = new JFrame("Preferences");
			preferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			preferences.setLocation(350, 200);
			prefPanel = new MyPanel(300, 400, 4, 1);
			
			prefPanel.add(new JLabel("<html><p>Change the control scheme! <br>Just click a button and then press the key you'd like to use.</p><html>"));
			MyPanel temp = new MyPanel(500, 116, 5, 2);
			prefLeft 	 = new JButton("Move Left");
			prefRight    = new JButton("Move Right");
			prefRotateR  = new JButton("Rotate R");
			prefRotateL  = new JButton("Rotate L");
			prefHardDrop = new JButton("Hard Drop");
			prefLeft.addActionListener(theListener);
			prefRight.addActionListener(theListener);
			prefRotateR.addActionListener(theListener);
			prefRotateL.addActionListener(theListener);
			prefHardDrop.addActionListener(theListener);
			
			leftLabel = new JLabel("", SwingConstants.CENTER);
			leftLabel.setText(KeyEvent.getKeyText(keys[0]));
			rightLabel = new JLabel("", SwingConstants.CENTER);
			rightLabel.setText(KeyEvent.getKeyText(keys[1]));
			rotateRLabel = new JLabel("", SwingConstants.CENTER);
			rotateRLabel.setText(KeyEvent.getKeyText(keys[2]));
			rotateLLabel = new JLabel("", SwingConstants.CENTER);
			rotateLLabel.setText(KeyEvent.getKeyText(keys[3]));
			hardDropLabel = new JLabel("", SwingConstants.CENTER);
			hardDropLabel.setText(KeyEvent.getKeyText(keys[4]));
			if (keys[4] == 32)
				hardDropLabel.setText("SPACE");

			temp.add(prefLeft);
			temp.add(leftLabel);
			temp.add(prefRight);
			temp.add(rightLabel);
			temp.add(prefRotateR);
			temp.add(rotateRLabel);
			temp.add(prefRotateL);
			temp.add(rotateLLabel);
			temp.add(prefHardDrop);
			temp.add(hardDropLabel);
			prefPanel.add(temp);
			
			prefFeedback = new JLabel("No changes have been made yet.",
												SwingConstants.CENTER);
			prefPanel.add(prefFeedback);
						
			temp = new MyPanel(500, 116, 1, 2);
			prefSave		 = new JButton("Save changes");
			prefClose 		 = new JButton("Close");
			prefSave.addActionListener(theListener);
			prefClose.addActionListener(theListener);
			temp.add(prefSave);
			temp.add(prefClose);			
			prefPanel.add(temp);
			
			prefPanel.addKeyListener(theListener);
			prefPanel.setFocusable(true);
			prefPanel.grabFocus();
			
			preferences.add(prefPanel);
	        preferences.pack();
	        preferences.setVisible(true);
			
			// copy in the key values to tempKeys
			for (int i = 0; i < 5; i++)
				tempKeys[i] = keys[i];
		}
	}
	
	class MyListener extends KeyAdapter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == newGame)
				if (JOptionPane.showConfirmDialog(null, "Are you sure you"
				 	 + " want to start a new game?",	"New Game", 
						JOptionPane.YES_NO_OPTION) == 0)
					new Tetris();
			if (e.getSource() == pause)
				pause();
			if (e.getSource() == endGame)
				System.exit(0);
			if (e.getSource() == prefClose)
			{
				boolean same = true;
				for (int i = 0; i < 5; i++)
					if (keys[i] != tempKeys[i])
						same = false;
				if (!same)
				{	
					if (JOptionPane.showConfirmDialog(null, "You have unsaved"
				 	 + " changes. Quit anyways?",	"", 
						JOptionPane.YES_NO_OPTION) == 0)
					{
						preferences.setVisible(false);
						gamePanel.grabFocus();
						changingKeys = false;
					}
				}
				else
				{
					preferences.setVisible(false);	
					gamePanel.grabFocus();
					changingKeys = false;
				}
			}		
		
			if (e.getSource() == prefLeft)
			{
				changingKeys = true;
				changingWhich = 0;
				prefFeedback.setText("Press the key for Move Left");
				prefPanel.grabFocus();
			}
			if (e.getSource() == prefRight)
			{
				changingKeys = true;
				changingWhich = 1;
				prefFeedback.setText("Press the key for Move Right");
				prefPanel.grabFocus();
			}
			if (e.getSource() == prefRotateR)
			{
				changingKeys = true;
				changingWhich = 2;
				prefFeedback.setText("Press the key for Rotate Right");
				prefPanel.grabFocus();
			}
			if (e.getSource() == prefRotateL)
			{
				changingKeys = true;
				changingWhich = 3;
				prefFeedback.setText("Press the key for Rotate Left");
				prefPanel.grabFocus();
			}
			if (e.getSource() == prefHardDrop)
			{
				changingKeys = true;
				changingWhich = 4;
				prefFeedback.setText("Press the key for Hard Drop");
				prefPanel.grabFocus();
			}
			
			if (e.getSource() == prefSave)
			{	
				boolean dupes = false;
				for (int i = 0; i < 5; i++)
					for (int j = i+1; j < 5; j++)
						if (tempKeys[i] == tempKeys[j])
							dupes = true;
				if (dupes)
					JOptionPane.showMessageDialog(null, 
					"You have created duplicate keys, please fix this.",
					"Duplicates", JOptionPane.INFORMATION_MESSAGE);	
				else
				{
					for (int i = 0; i < 5; i++)
						keys[i] = tempKeys[i];
					changingKeys = false;
					prefFeedback.setText("Changes saved!");
				}
				prefPanel.grabFocus();
			}			
		}
		
		public void keyPressed(KeyEvent e) 
		{
			if (changingKeys)
			{
				tempKeys[changingWhich] = e.getKeyCode();

				if (changingWhich == 0)
					leftLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				if (changingWhich == 1)
					rightLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				if (changingWhich == 2)
					rotateRLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				if (changingWhich == 3)
				 rotateLLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				if (changingWhich == 4)
				hardDropLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				if (changingWhich == 4 && e.getKeyCode() == 32)
					hardDropLabel.setText("SPACE");
				prefFeedback.setText("Key changed! Save"
											+ " changes before closing.");
			}    
			else
			{
				board.eraseTrail(currPiece);
				gamePanel.grabFocus();
				if (e.getKeyCode() == keys[0])	// move left
					currPiece.moveLeft(board);
				if (e.getKeyCode() == keys[1])	// move right
					currPiece.moveRight(board);
				if (e.getKeyCode() == keys[2])	// rotateR
					currPiece.rotateR(board);
				if (e.getKeyCode() == keys[3])	// rotateL
					currPiece.rotateL(board);

				//** this works for hard drop, potential problem is that if you keep holding space, the piece basically spawns at the bottom
				if (e.getKeyCode() == keys[4])
					while(currPiece.canMoveDown(board))
						currPiece.moveDown(board);
					
				System.out.println("\n\nDEBUG INFO:\n"
				 		+ "x value: " + currPiece.getGridX() + "\n"
						+ "y value: " + currPiece.getGridY() + "\n"
						+ "Rotational Position: " + currPiece.getPosition());
			
				board.fill(currPiece);
				colorPieces();
				
			/*	if (!currPiece.canMoveDown(board))
				{
					currPiece = nextPiece;
					int rand = rng.nextInt(6);
        			nextPiece = selectPiece(rand); // and that piece
        			picLabel.setIcon(new ImageIcon( myPicture[rand] ));
        		}*/
			}
		}
	}
}