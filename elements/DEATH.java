package elements;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import main.Base_Scene;
import main.Game;

@SuppressWarnings("serial")
public class DEATH extends JLabel implements ActionListener {

	Timer timer;
	int seconds, sec,r;
	Game w;
	JLabel next;

	public DEATH(Game w, JLabel next) {
		seconds = 2;
		sec = 10;
		r = 0;
		this.next=next;
		timer = new Timer(100, this);
		this.w = w;
		timer.start();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("DeathImage.png")).getImage(), 0, 0, Base_Scene.SCREEN_WIDTH+10, Base_Scene.SCREEN_HEIGHT+10, null);
		play();
	}
	
	void play()
	{
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((getClass().getClassLoader().getResource("DeathCutscene.wav")));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (sec > 0)
			sec--;
		else if (sec == 0 && seconds > 0) {
			seconds--;
			sec = 10;
		}

		if (seconds == 0) {
			w.add(next);
			SwingUtilities.updateComponentTreeUI(w);
			timer.stop();
			w.remove(this);
		}
	}

}
