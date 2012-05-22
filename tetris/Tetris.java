//Tetris by bosscoding

//imports and functionality of swing stolen from Yahtzee.java(Zaxcoding) skeleton

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import com.apple.eawt.*;
import java.awt.*;
import java.awt.event.*;

public class Tetris
{
	JFrame theWindow;
	Container thePane;
	MyPanel gamePanel;
	MyPanel array [];
	MyPanel buttonPanel, leftPanel, rightPanel, nextPanel, timePanel;
	MyPanel linesPanel, totalPanel, nextLinePanel, levelPanel;
	JButton newGame, pause, endGame;
	MyListener theListener;
	Piece currPiece, nextPiece;
	
	GameBoard board = new GameBoard(10,20);
	Application app;
	AppListener appListener;
	JButton prefLeft, prefRight, prefRotateR, prefRotateL, prefHardDrop;
	JButton prefSave, prefClose;
	JLabel leftLabel, rightLabel, rotateRLabel, rotateLLabel, hardDropLabel;
	JFrame preferences;
	MyPanel prefPanel;
	
	boolean paused,start,game=true;

	public static void main(String [] args)
	{
		new Tetris();
	}

	public Tetris()
	{
		setup();
		
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

		pause = new JButton("Pause");
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

		array = new MyPanel[200];

		for(int i = 0; i < 200; i++)
		{
			array[i] = new MyPanel(10,10);
			array[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			leftPanel.add(array[i]);
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

	}

	public void pause()
	{
		if(!start)
		{
			start = true;
			System.out.println("Game Start");
		}
		paused = !paused;
		System.out.println("Paused: " + paused);
	}

	private class AppListener implements PreferencesHandler
	{
		public void handlePreferences(AppEvent.PreferencesEvent e)
		{
			preferences = new JFrame("Preferences");
			preferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			preferences.setLocation(350, 200);
			MyPanel prefPanel = new MyPanel(300, 400, 4, 1);
			
			prefPanel.add(new JLabel("<html><p>Change the controls! <br>Just click a button and then press the key you'd like to use.</p><html>"));
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
			
			leftLabel = new JLabel("A", SwingConstants.CENTER);
			rightLabel = new JLabel("D", SwingConstants.CENTER);
			rotateLLabel = new JLabel("K", SwingConstants.CENTER);
			rotateRLabel = new JLabel("L", SwingConstants.CENTER);
			hardDropLabel = new JLabel("SPACE", SwingConstants.CENTER);
			
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
			
			prefPanel.add(new MyPanel(300, 10));
			
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
				preferences.setVisible(false);
		
		}
		
		public void keyPressed(KeyEvent e) 
		{
			
	    }
		
	}
}