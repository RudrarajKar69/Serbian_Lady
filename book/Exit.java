package book;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;

import main.Base_Scene;
import main.Game;

@SuppressWarnings("serial")
public class Exit extends JLabel implements ActionListener {

	Timer timer;
	int w, h;
	Random rnd;
	Game s;

	Exit(Game ss, int W, int H) {
		s = ss;
		rnd = new Random();
		timer = new Timer(75, this);
		timer.start();
		w = W;
		h = H;
	}

	protected void paintComponent(Graphics g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 30F));
		g.setColor(new Color(200,200,200));
		String text = "You have got the exit key";
		int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		int height = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		height = Base_Scene.SCREEN_HEIGHT/2-height/2;
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height);
		
		text = "Now go to the left of room 1 to be free";
		length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		height += (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height);
		
		text = "be sure not to die";
		length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		height += (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
		text = "Go to room 1";
		length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		height += (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, Base_Scene.SCREEN_WIDTH/2-length/2, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
