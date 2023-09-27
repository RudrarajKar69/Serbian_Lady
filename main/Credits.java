package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Credits extends JLabel implements ActionListener{
	
	Timer timer;
	int seconds = 3;
	Game w;
	Base_Scene next;
	
	Credits(Game w,Base_Scene n)
	{
		next=n;
		timer = new Timer(1000,this);
		this.w=w;
		timer.start();
	}
	
	protected void paintComponent(Graphics g)
	{
		int y=0;
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, Base_Scene.SCREEN_WIDTH, Base_Scene.SCREEN_HEIGHT);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 60F));
		g.setColor(new Color(255,255,255));
		String text = "Credits";
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		y+=height;
		g.drawString(text, 0, y);
		
		text = "Rudraraj Kar";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		y = Base_Scene.SCREEN_HEIGHT/2-height;
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, y);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
		text = "(Solo Developer)";
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		y+=height;
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2, y);
		
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
			next.timer.start();
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
