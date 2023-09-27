package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

import main.Scene;

public abstract class Base_Enemy extends GameObject {

	public boolean shoot = false, move = false, left = false;
	boolean i;
	Random rnd = new Random();
	int score, shootProb;
	Player p;

	Base_Enemy(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id, Player p,
			int Score) {
		super(x, y, width, height, image, alive, id);
		this.p=p;
		score = Score;
		if(rnd.nextBoolean())
			i=true;
	}

	@Override
	public void draw(Graphics g) {

		if(i)
			g.drawImage(getImage().get(1), getX(), getY(), getWidth(), getHeight(), null);
		else
		{
			if(getXspeed()<0)
				g.drawImage(getImage().get(0), getX(), getY(), getWidth(), getHeight(), null);
			if(getXspeed()>0)
				g.drawImage(getImage().get(2), getX(), getY(), getWidth(), getHeight(), null);
		}

	}

	// Plays sound
	public static void playSound(String filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Scene.class.getResource(filePath));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Add a listener to handle the sound completion event
			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close(); // Close the clip when it finishes playing
				}
			});

			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {

		setX(getX() + getXspeed());
		setY(getY() + getYspeed());

		// Shoot bullets
		if (rnd.nextInt(shootProb) + 1 == 1)
			shoot = true;

		if(AABB(this,p)) {
			this.setAlive(false);
			p.setAlive(false);
		}
		
	}

	@Override
	public void update(KeyEvent k, MouseEvent m) {
	}

	public int getShootProb() {
		return shootProb;
	}

	public void setShootProb(int shootProb) {
		this.shootProb = shootProb;
	}

	boolean AABB(Base_Enemy base_Enemy, Player e) {
		if (base_Enemy.getX() < e.getX() + e.getWidth() && base_Enemy.getX() + base_Enemy.getWidth() > e.getX()
				&& base_Enemy.getY() < e.getY() + e.getHeight() && base_Enemy.getY() + base_Enemy.getHeight() > e.getY())
			return true;
		return false;

	}

}
