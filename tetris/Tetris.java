//Tetris by bosscoding

//imports and functionality of swing stolen from Yahtzee.java(Zaxcoding) skeleton

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import com.apple.eawt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

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
	Piece currPiece, nextPiece;
	
	
	GameBoard board = new GameBoard(10,20);
	
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
	
	
	boolean paused,start,game=true, newpiece=true;
	TimeUp t;

	public static void main(String [] args)
	{
		new Tetris();
	}

	public Tetris()
	{
		setup();
		while(game)
		{
			if(newpiece)
			{
				Random r = new Random();
  				int pieceDigit = r.nextInt(6);
  				selectPiece(pieceDigit);
 			}
			t = new TimeUp(1,currPiece);
		}
		
	}
	
	public void selectPiece(int a)
	{
		if(a ==0)
		{
			currPiece = new JPiece();
		}
		else if(a ==1)
		{
			currPiece = new LPiece();
		}
		else if(a ==2)
		{
			currPiece = new OPiece();
		}
		else if(a ==3)
		{
			currPiece = new SPiece();
		}
		else if(a ==4)
		{
			currPiece = new ZPiece();
		}
		else if(a ==5)
		{
			currPiece = new IPiece();
		}
		else if(a ==6)
		{
			currPiece = new TPiece();
		}
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
		JLabel gameTime = new JLabel("0",SwingConstants.CENTER);
		gameTime.setFont(new Font("Serif", Font.ITALIC, 20));
		gameTime.setBorder(BorderFactory.createLineBorder(Color.gray));
		timePanel.add(timeText);
		timePanel.add(gameTime);

		JLabel nextText = new JLabel("<html> <center>Next</center></html>"
										,SwingConstants.CENTER);
		nextText.setFont(new Font("Serif", Font.ITALIC, 20));
		nextText.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel nextPiece = new JLabel("0",SwingConstants.CENTER);
		nextPiece.setFont(new Font("Serif", Font.ITALIC, 20));

		nextPanel.add(nextText);
		nextPanel.add(nextPiece);

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
		
		paused = true;
		start = false;
		
		app = Application.getApplication();
		appListener = new AppListener();
		app.setPreferencesHandler(appListener);
		
		keys = new int[5];
		tempKeys = new int[5];
		
		// hardcode in the default control settings
		
		keys[0] = 65;
		keys[1] = 83;
		keys[2] = 75;
		keys[3] = 76;
		keys[4] = 32;
	}

	public void pause()
	{
		if(!start)
			start = true;
		
		paused = !paused;
		if (!paused)
			pause.setText("Pause");
		else
			pause.setText("Resume");
	}
	
	public void colorPieces()
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++)
				if (board.grid[i][j] != 0)
					array[i][j].setBackground(new Color(board.grid[i][j]));
	}

	//found this timer structure, looks to be useful
	// should call run() every 1 second then call the movedown of the currentpiece on the board
	// movedown not yet officially implemented
	private class TimeUp 
	{
    	Timer timer;
		//Piece current;
    	public TimeUp(int seconds,Piece p) 
    	{
    		//current =p;
        	timer = new Timer();
        	timer.schedule(new Task(), seconds*1000);
		}

    	class Task extends TimerTask 
    	{
        
        	public void run() 
        	{
            	//current.moveDown(board);//does movedown exist here?, does current exist here? ..so rusty 
            	//currPiece.moveDown(board);//does movedown exist here?, does currPiece exist here? ..so rusty 
            	System.out.println("hey");
            	//timer.cancel(); //Terminate the timer thread
        	}
    
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
						preferences.setVisible(false);
				}
				else
					preferences.setVisible(false);	
			}		
		
			if (e.getSource() == prefLeft)
			{
				changingKeys = true;
				changingWhich = 0;
				prefFeedback.setText("Press the key for Move Left");
			}
			if (e.getSource() == prefRight)
			{
				changingKeys = true;
				changingWhich = 1;
				prefFeedback.setText("Press the key for Move Right");
			}
			if (e.getSource() == prefRotateR)
			{
				changingKeys = true;
				changingWhich = 2;
				prefFeedback.setText("Press the key for Rotate Right");
			}
			if (e.getSource() == prefRotateL)
			{
				changingKeys = true;
				changingWhich = 3;
				prefFeedback.setText("Press the key for Rotate Left");
			}
			if (e.getSource() == prefHardDrop)
			{
				changingKeys = true;
				changingWhich = 4;
				prefFeedback.setText("Press the key for Hard Drop");
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
			}
			prefPanel.grabFocus();
			
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
		}
		
	}
}