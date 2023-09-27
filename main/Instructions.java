package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Instructions extends JLabel implements ActionListener{
	
	Timer timer;
	int seconds = 3;
	Game w;
	JLabel next;
	
	Instructions(Game w){
		
		timer = new Timer(1000,this);
		this.w=w;
		timer.start();
	}
	
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Instruction.png")).getImage(), 0, 0, null);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 60F));
		g.setColor(new Color(200,0,0));
		String text = "---Instructions---";
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height);
		
		text = "1) DO NOT CRASH INTO ENEMIES";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height*3);
		
		text = "2) ESCAPE WITHIN 5 DAYS";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height*4);
		
		text = "3) YOU HAVE 3 BULLETS, USE WISELY";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height*5);
		
		text = "4) Use Q to pause/view credits";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height*6);
		
		text = "5) Press any key to continue from pause menu";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height*7);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		g.setColor(Color.white);
		text = "Time remaining:- "+seconds;
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH-length, Base_Scene.SCREEN_HEIGHT-height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(seconds>0)
			seconds--;
		else if(seconds==0)
		{
			// If "Restart" is selected, go back to the difficulty screen
			next = new Scene(w);
			w.add(next);
			// Update the window's UI to show the new screen
			SwingUtilities.updateComponentTreeUI(w);
			// Stop the timer
			timer.stop();
			// Remove this screen from the window
			w.remove(this);
		}
		
		repaint();
		
	}

}
