// Tetris by BossCoding
// Version 1.0.0
// Released June 11, 2012

import com.apple.eawt.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Timer;
import sun.audio.*;

public class Tetris
{
	Application app;
	AppListener appListener;

	AudioStream backgroundMusic;

	boolean game = true, newPiece = true, stopTheMusic = false;
	boolean paused, hardDropped, changingKeys;	

	BufferedImage [] myPicture = new BufferedImage[7];

	Container thePane, menuPane, exitPane;

	double currentTime = 0;

	GameBoard board = new GameBoard(10,20);

	int timerDelay = 1000, score = 0, level = 1;	
	int changingWhich;
				
	int [] keys, tempKeys;	

	JButton newGame, pause, endGame,startGame,menuPref,exitGame;
	JButton prefLeft, prefRight, prefRotateR, prefRotateL, prefSoftDrop, 
			prefHardDrop, prefSave, prefClose;
	
	JFrame theWindow, menuWindow, exitWindow, preferences;

	JLabel scoreText,finalScore,exitLabel;
	JLabel leftLabel, rightLabel, rotateRLabel, rotateLLabel, hardDropLabel, 	
			softDropLabel, prefFeedback;
	JLabel gameScore, picLabel, line1Text, line2Text, line4Text, currentLevel, 
			menuLabel, picLabel2, picLabel3;

	MyListener theListener;

	MyPanel gamePanel, menuPanel, exitPanel;
	MyPanel buttonPanel, leftPanel, rightPanel, nextPanel, scorePanel, prefPanel;
	MyPanel linesPanel, totalPanel, nextLinePanel, levelPanel, buttonMenuPanel, exitMenuPanel;
	MyPanel array [][];

	Piece currPiece, nextPiece, nextNextPiece, nextNextNextPiece, ghost;

	Random rng; 

	Timer timer;
	
	TimerListener timerListener;

	static boolean start;

	//-----End of huge declaration----//


	public static void main(String [] args)
	{
		start = true;
		new Tetris();
	}

	public void menu()
	{
		menuWindow = new JFrame("Menu");
		menuWindow.setLocation(350, 200);
		menuPane = menuWindow.getContentPane();
		menuPane.setLayout(new GridLayout(2, 1));
		
		theListener = new MyListener();
		menuPanel = new MyPanel(728,90,1,1);
		buttonMenuPanel = new MyPanel(728,90,1,1);
		
		try 
		{
			BufferedImage banner = ImageIO.read(new	File("img/banner.png"));
			menuLabel = new JLabel("",JLabel.CENTER);
			menuLabel.setIcon(new ImageIcon( banner ));	
		
		 	buttonMenuPanel.add(menuLabel);
		}
		catch(Exception a)
		{
			System.out.println("banner did not work :/");
		}	
		
		startGame = new JButton("New Game");
		startGame.setFont(new Font("Serif", Font.ITALIC, 20));
		menuPanel.add(startGame);
		startGame.addActionListener(theListener);
		
		menuPref  = new JButton("Controls");
		menuPref.setFont(new Font("Serif", Font.ITALIC, 20));
		menuPanel.add(menuPref);
		menuPref.addActionListener(theListener);
		
		endGame = new JButton("End Game");
		endGame.setFont(new Font("Serif", Font.ITALIC, 20));
		menuPanel.add(endGame);
		endGame.addActionListener(theListener);
		
		
				
		menuPane.add(buttonMenuPanel);
		menuPane.add(menuPanel);
		menuWindow.pack();
		menuWindow.setVisible(true);
		
		menuPanel.setFocusable(true);
		menuPanel.grabFocus();
		menuPanel.addKeyListener(theListener);		
	}
	
	
	public void gameover()
	{
		
		exitWindow = new JFrame("Game Over");
		exitWindow.setLocation(350, 200);
		exitPane = exitWindow.getContentPane();
		exitPane.setLayout(new GridLayout(2, 1));
		
	
		exitPanel = new MyPanel(728,90,1,1);
		exitMenuPanel = new MyPanel(728,90,1,1);
		
		try 
		{
			BufferedImage banner = ImageIO.read(new	File("img/gameover.png"));
			exitLabel = new JLabel("",JLabel.CENTER);
			exitLabel.setIcon(new ImageIcon( banner ));	
		
		 	exitMenuPanel.add(exitLabel);
		}
		catch(Exception a)
		{
			System.out.println("banner did not work :/");
		}	
		
		finalScore = new JLabel("",JLabel.CENTER);
		finalScore.setText(gameScore.getText());
		finalScore.setFont(new Font("Serif", Font.ITALIC, 20));
		exitPanel.add(finalScore);
		
		
				
		exitPane.add(exitMenuPanel);
		exitPane.add(exitPanel);
		exitWindow.pack();
		exitWindow.setVisible(true);
		
		exitPanel.setFocusable(true);
		exitPanel.grabFocus();	
	}

	public Tetris()
	{
		initKeys();
		
		if (start)
			menu();
		else
		{
			setup();
			
			int pieceDigit = rng.nextInt(7);		// get rand int
			nextPiece = selectPiece(pieceDigit); 	// and that piece
			currPiece = nextPiece;
			nextPiece = null;
			nextNextPiece = null;
			nextNextNextPiece = null;
			ghost = new JPiece();
		
			// new timing structure with its own action listener
			timerListener = new TimerListener(); 
                
        	timer = new Timer(timerDelay,timerListener);
			timer.start();
			music();
		}
	}
	
	public Piece selectPiece(int a)
	{
		// temp variable, just make it J, then change to what it needs to be
		Piece temp = new TPiece();
		
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
		if (a == 6)
		{
			temp = new TPiece();
		}
		
		return temp;
	}
		
	public void setup()
	{
		theWindow = new JFrame("Tetris- by bosscoding");
		theWindow.setLocation(350, 200);
		
		thePane = theWindow.getContentPane();
		thePane.setLayout(new GridLayout(1, 1));
		
		gamePanel = new MyPanel(500, 500, 1, 2);

		leftPanel = new MyPanel(100, 500, 20, 5);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		rightPanel = new MyPanel(400,500, 5, 1);
		nextPanel = new MyPanel(1,1, 2, 1);
		scorePanel = new MyPanel(100,100, 2, 1);
		linesPanel = new MyPanel(100,100, 5, 1);
		levelPanel = new MyPanel(100,100, 2, 1);
		buttonPanel = new MyPanel(100,100, 3, 1);

		theListener = new MyListener();
		
		newGame = new JButton("New Game");
		newGame.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(newGame);
		newGame.addActionListener(theListener);

		pause = new JButton("Pause");
		pause.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(pause);
		pause.addActionListener(theListener);

		exitGame = new JButton("Exit to Menu");
		exitGame.setFont(new Font("Serif", Font.ITALIC, 20));
		buttonPanel.add(exitGame);
		exitGame.addActionListener(theListener);

		JLabel levelText = new JLabel("Level",SwingConstants.CENTER);
		levelText.setFont(new Font("Serif", Font.ITALIC, 20));
		levelText.setBorder(BorderFactory.createLineBorder(Color.gray));
		currentLevel = new JLabel("1",SwingConstants.CENTER);
		currentLevel.setBorder(BorderFactory.createLineBorder(Color.gray));
		currentLevel.setFont(new Font("Serif", Font.ITALIC, 20));
		levelPanel.add(levelText);
		levelPanel.add(currentLevel);

		JLabel lineText = new JLabel("<html><b>Lines</b></html>",
										SwingConstants.CENTER);
		lineText.setFont(new Font("Serif", Font.ITALIC, 20));
		lineText.setBorder(BorderFactory.createLineBorder(Color.gray));
		line1Text = new JLabel("Total",SwingConstants.CENTER);
		line1Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line1Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		line2Text = new JLabel("0",SwingConstants.CENTER);
		line2Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line2Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel line3Text = new JLabel("Next Level in",SwingConstants.CENTER);
		line3Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line3Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		line4Text = new JLabel("10",SwingConstants.CENTER);
		line4Text.setFont(new Font("Serif", Font.ITALIC, 16));
		line4Text.setBorder(BorderFactory.createLineBorder(Color.gray));
		linesPanel.add(lineText);
		linesPanel.add(line1Text);
		linesPanel.add(line2Text);
		linesPanel.add(line3Text);
		linesPanel.add(line4Text);

		scoreText = new JLabel("Score",SwingConstants.CENTER);
		scoreText.setFont(new Font("Serif", Font.ITALIC, 20));
		scoreText.setBorder(BorderFactory.createLineBorder(Color.gray));
		gameScore = new JLabel("0",SwingConstants.CENTER); 
		gameScore.setFont(new Font("Serif", Font.ITALIC, 20));
		gameScore.setBorder(BorderFactory.createLineBorder(Color.gray));
		scorePanel.add(scoreText);
		scorePanel.add(gameScore);

		JLabel nextText = new JLabel("<html><center>Next</center></html>"
										,SwingConstants.CENTER);
		nextText.setFont(new Font("Serif", Font.ITALIC, 20));
		nextText.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		try
		{
			myPicture[0] = ImageIO.read(new File("img/L.png"));	
			myPicture[1] = ImageIO.read(new File("img/J.png"));
			myPicture[2] = ImageIO.read(new File("img/O.png"));
			myPicture[3] = ImageIO.read(new File("img/Z.png"));
			myPicture[4] = ImageIO.read(new File("img/S.png"));
			myPicture[5] = ImageIO.read(new File("img/I.png"));
			myPicture[6] = ImageIO.read(new File("img/T.png"));
			
			
			MyPanel nextsPanel = new MyPanel(1,1, 1, 3);
			picLabel = new JLabel("",JLabel.CENTER);
			picLabel2 = new JLabel("", JLabel.CENTER);
			picLabel3 = new JLabel("", JLabel.CENTER);
			nextsPanel.add(picLabel);
			nextsPanel.add(picLabel2);
			nextsPanel.add(picLabel3);

			nextPanel.add(nextText);
		    nextPanel.add(nextsPanel);
		}
		catch(Exception a)
		{
			System.out.println("pic didnt work :/");
			System.exit(0);
		}
	
		rightPanel.add(nextPanel);
		rightPanel.add(scorePanel);
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
				
		rng = new Random();		// init the random in setup
	
		initBoard();
		colorPieces();
	}

	public void music() 
	{
			
	    AudioData musicData;
	    AudioPlayer musicPlayer = AudioPlayer.player;
	    ContinuousAudioDataStream loop = null;
	    try 
	    {
	    	InputStream test = new FileInputStream("sound/tetristheme.wav");
	        backgroundMusic = new AudioStream(test);
	        AudioPlayer.player.start(backgroundMusic);
	        //musicData = backgroundMusic.getData();
	        //loop = new ContinuousAudioDataStream(musicData);
	        musicPlayer.start(loop);
	    } 
	    catch (IOException error) 
	    { 
	     	System.out.println(error);
	    }
    }

	public void pause()
	{
		paused = !paused;
		if (!paused)
		{
			pause.setText("Resume");
			timer.stop();
		}
		else
		{
			pause.setText("Pause");
			timer.start();
		}
		gamePanel.grabFocus();
	}
	
	public void colorPieces()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
				if (board.grid[i][j] != 0)
					array[i][j].setBackground(new Color(board.grid[i][j]));
	}

	public void initKeys()
	{
		keys = new int[6];
		tempKeys = new int[6];

		keys[0] = 65;
		keys[1] = 68;
		keys[2] = 76;
		keys[3] = 75;
		keys[4] = 32;
		keys[5] = 83;	
	}

	public void initBoard()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
			{
				array[i][j].setBackground(new Color(Color.TRANSLUCENT));
				board.grid[i][j] = Color.TRANSLUCENT;
			}
	}		
	
	public void checkBoard()
	{
		int linesCleared = 0;
		boolean check=false;
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
			{
				if (board.grid[i][j] == Color.TRANSLUCENT)
				{
					j=9;
					check = false;
				}
				else
				{
					if(j ==9)
						check = true;
				}
				
				if(check)
				{
					linesCleared++;
					killLine(i);
					check =false;
				}
			}
		if (linesCleared == 1)
			score += 100*level;
		if (linesCleared == 2)
			score += 300*level;
		if (linesCleared == 3)
			score += 500*level;
		if (linesCleared == 4)
			score += 800*level;
		gameScore.setText(Integer.toString(score));
	}
	
	public void ghostPiece()
	{
		board.eraseTrail(currPiece);
		ghost = currPiece.clone();
		while(ghost.canMoveDown(board))
			ghost.moveDown(board);
		board.fill(currPiece);
	}
	
	public void killLine(int a)
	{
		for(int i =a; i>1; i--)
			for(int j =0;j<10;j++)
				board.grid[i][j] = board.grid[i-1][j];
			
		line2Text.setText(Integer.parseInt(line2Text.getText()) + 1 + "");	
		
		if(Integer.parseInt(line4Text.getText()) == 1)
		{
			line4Text.setText("10");
			level++;
			currentLevel.setText(Integer.toString(level));
			timerDelay *= .9;
			timer.setDelay(timerDelay);
		}
		else
			line4Text.setText(Integer.parseInt(line4Text.getText()) - 1 + "");			
	}

	public void makePreferences()
	{
		preferences = new JFrame("Preferences");
		preferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		preferences.setLocation(350, 200);
		prefPanel = new MyPanel(300, 400, 4, 1);
		
		prefPanel.add(new JLabel("<html><p>Change the control scheme! <br>Just click a button and then press the key you'd like to use.</p><html>"));
		MyPanel temp = new MyPanel(500, 116, 6, 2);
		prefLeft 	 = new JButton("Move Left");
		prefRight    = new JButton("Move Right");
		prefRotateL  = new JButton("Rotate L");
		prefRotateR  = new JButton("Rotate R");
		prefHardDrop = new JButton("Hard Drop");
		prefSoftDrop = new JButton("Soft Drop");
		prefLeft.addActionListener(theListener);
		prefRight.addActionListener(theListener);
		prefRotateL.addActionListener(theListener);
		prefRotateR.addActionListener(theListener);
		prefHardDrop.addActionListener(theListener);
		prefSoftDrop.addActionListener(theListener);
		
		leftLabel = new JLabel("", SwingConstants.CENTER);
		leftLabel.setText(KeyEvent.getKeyText(keys[0]));
		rightLabel = new JLabel("", SwingConstants.CENTER);
		rightLabel.setText(KeyEvent.getKeyText(keys[1]));
		rotateLLabel = new JLabel("", SwingConstants.CENTER);
		rotateLLabel.setText(KeyEvent.getKeyText(keys[2]));
		rotateRLabel = new JLabel("", SwingConstants.CENTER);
		rotateRLabel.setText(KeyEvent.getKeyText(keys[3]));
		hardDropLabel = new JLabel("", SwingConstants.CENTER);
		hardDropLabel.setText(KeyEvent.getKeyText(keys[4]));
		softDropLabel = new JLabel("", SwingConstants.CENTER);
		softDropLabel.setText(KeyEvent.getKeyText(keys[5]));
		
		if (keys[4] == 32)
			hardDropLabel.setText("SPACE");

		temp.add(prefLeft);
		temp.add(leftLabel);
		temp.add(prefRight);
		temp.add(rightLabel);
		temp.add(prefRotateL);
		temp.add(rotateLLabel);
		temp.add(prefRotateR);
		temp.add(rotateRLabel);
		temp.add(prefHardDrop);
		temp.add(hardDropLabel);
		temp.add(prefSoftDrop);
		temp.add(softDropLabel);
		
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
		for (int i = 0; i < 6; i++)
			tempKeys[i] = keys[i];
	}

	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{ 
			currentTime += timerDelay*.001;
			if (currentTime > 1.5 && currentTime % 85 <= 1.5 && !stopTheMusic)
			{
				stopTheMusic = true;		// prevents double music
				music();
			}
			if (currentTime % 85 > 2)
				stopTheMusic = false;
			
			if (nextNextPiece == null)
			{
				int rand = rng.nextInt(7);
				nextNextPiece = selectPiece(rand); // and that piece
				picLabel2.setIcon(new ImageIcon( myPicture[rand] )); 
			}

			if (nextNextNextPiece == null)
			{
				int rand = rng.nextInt(7);
				nextNextNextPiece = selectPiece(rand); // and that piece
				picLabel3.setIcon(new ImageIcon( myPicture[rand] )); 
			}

			if (nextPiece == null)
			{
				int rand = rng.nextInt(7);
				nextPiece = selectPiece(rand); // and that piece
				picLabel.setIcon(new ImageIcon( myPicture[rand] )); 
			}
			gameScore.setText(Integer.toString(score));

			// everytime the timer goes off (every second) it will basically 
			// move the piece down, with all existing logic
			board.eraseTrail(currPiece);
			board.eraseTrail(ghost);
    	
			currPiece.moveDown(board);
    		
			if (!currPiece.canMoveDown(board))
			{
				if (newPiece)
				{
					board.fill(currPiece);
					currPiece = nextPiece;
					nextPiece = nextNextPiece;
					nextNextPiece = nextNextNextPiece;
					int rand = rng.nextInt(7);
    				nextNextNextPiece = selectPiece(rand); // and that piece
    				
    				picLabel.setIcon(picLabel2.getIcon());
    				picLabel2.setIcon(picLabel3.getIcon());
    				picLabel3.setIcon(new ImageIcon(myPicture[rand]));

					newPiece = false;
					hardDropped = false;
					if (!currPiece.canMoveDown(board))
					{
						hardDropped = true;
						paused = true;
						System.out.println("GAME OVER");
						try
						{
							backgroundMusic.close();
						} 
						catch (IOException f)	
						{	
							System.out.println("Oh no! Can't stop the music!");
						}
						timer.stop();
						theWindow.setVisible(false);
						menu();
						gameover();
						
					//	gamePanel.setVisible(false);
						
					}
				}
				else
					newPiece = true;
			}
			checkBoard();
			board.fill(currPiece);
			ghostPiece();
			board.ghostFill(ghost);
			colorPieces();
		} 	
	}

	private class AppListener implements PreferencesHandler
	{
		public void handlePreferences(AppEvent.PreferencesEvent e)
		{
			makePreferences();
		}
	}
	
	private class MyListener extends KeyAdapter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == startGame)
			{
				start = false;
				menuWindow.setVisible(false);
				new Tetris();
			}
				
			if(e.getSource() == newGame)
				if (JOptionPane.showConfirmDialog(null, "Are you sure you"+ " want to start a new game?",	"New Game", JOptionPane.YES_NO_OPTION) == 0)
				{	
					try
					{
						backgroundMusic.close();
					} 
					catch (IOException f)	
					{	
						System.out.println("Oh no! Can't stop the music!");
					}
				
					theWindow.setVisible(false);
					new Tetris();
				}
				
			if (e.getSource() == menuPref)
				makePreferences();
					
			if (e.getSource() == pause)
				pause();
			if (e.getSource() == endGame)
				System.exit(0);
			if (e.getSource() == exitGame)
			{
				theWindow.setVisible(false);
				try{
				backgroundMusic.close();
				} catch (IOException f)	
				{	System.out.println("Oh no! Can't stop the music!");}	
				menu();
			}
			if (e.getSource() == prefClose)
			{
				boolean same = true;
				for (int i = 0; i < 6; i++)
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
					if (gamePanel != null)
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
			if (e.getSource() == prefSoftDrop)
			{
				changingKeys = true;
				changingWhich = 5;
				prefFeedback.setText("Press the key for Soft Drop");
				prefPanel.grabFocus();
			}
					
			if (e.getSource() == prefSave)
			{	
				boolean dupes = false;
				for (int i = 0; i < 6; i++)
					for (int j = i+1; j < 6; j++)
						if (tempKeys[i] == tempKeys[j])
							dupes = true;
				if (dupes)
					JOptionPane.showMessageDialog(null, 
					"You have created duplicate keys, please fix this.",
					"Duplicates", JOptionPane.INFORMATION_MESSAGE);	
				else
				{
					for (int i = 0; i < 6; i++)
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
				if (changingWhich == 5)
				softDropLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
				
				prefFeedback.setText("Key changed! Save"
											+ " changes before closing.");
			}    
			else
			{
				if (!hardDropped)
				{
					board.eraseTrail(currPiece);
					board.eraseTrail(ghost);
					gamePanel.grabFocus();
			
					if (e.getKeyCode() == keys[0])	// move left
						currPiece.moveLeft(board);
					if (e.getKeyCode() == keys[1])	// move right
						currPiece.moveRight(board);
					if (e.getKeyCode() == keys[2])	// rotateL
						currPiece.rotateL(board);
					if (e.getKeyCode() == keys[3])	// rotateR
						currPiece.rotateR(board);
				

					//** this works for hard drop, potential problem is that if you keep holding space, the piece basically spawns at the bottom
					if (e.getKeyCode() == keys[4])
					{
						while(currPiece.canMoveDown(board))
						{
							currPiece.moveDown(board);
							score += 2;
							gameScore.setText(Integer.toString(score));
						}
						newPiece = true;
						hardDropped = true;
					}
					if (e.getKeyCode() == keys[5])
					{	
						score += 1;
						currPiece.moveDown(board);
						gameScore.setText(Integer.toString(score));
					}
					
					board.fill(currPiece);
					ghostPiece();
					board.ghostFill(ghost);
					colorPieces();
				}
			}
		}
	}
}