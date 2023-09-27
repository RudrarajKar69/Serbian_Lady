package main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import book.Dialog;
import elements.Book;
import elements.DEATH;
import elements.GameObject;
import elements.ID;
import elements.Player;

@SuppressWarnings("serial")
public class Scene6 extends Base_Scene {

	Scene6(Game w, Player p) {
		super(w, p);
		floor = new ImageIcon(getClass().getClassLoader().getResource("floor/Floor3.png")).getImage();
		if(!p.books[1])
			objects.add(new Book(rnd.nextInt(TILE_SIZE,SCREEN_WIDTH-TILE_SIZE), rnd.nextInt(TILE_SIZE,SCREEN_HEIGHT-TILE_SIZE), TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
	}

	@SuppressWarnings("removal")
	@Override
	public void actionPerformed(ActionEvent e) {

		if ((p.getXspeed() != 0 || p.getYspeed() != 0) && !playing) {
			playSound("/walking.wav");
			playing = true;
		}
		if (p.getXspeed() == 0 && p.getYspeed() == 0 && playing) {
			stopSound();
			playing = false;
		}

		if (p.Q) {
			System.out.println("Ammo");
			Credits temp = new Credits(w, this);
			timer.stop();
			w.add(temp);
			SwingUtilities.updateComponentTreeUI(w);
			w.remove(this);
		}

		ArrayList<GameObject> obj = new ArrayList<GameObject>();
		for (GameObject o : objects) {
			obj.add(o);
		}

		if (rnd.nextInt(1, 40) == 1)
			enemy_Spawner();

		if (c > 0 && rnd.nextInt(1, 100) == 1)
			deSpawner();

		for (GameObject x : obj) {
			if (x.getId() == ID.Player) {
				Player temp = (Player) x;
				temp.update(w.ke, w.me);
				// Move to next level
				if (x.getX() > SCREEN_WIDTH) 
					p.setX(p.getX()-TILE_SIZE);
				
				if (x.getY() < 0) {
					p.setLevel(3);
					p.setY(SCREEN_HEIGHT - p.getHeight());
					thread.stop();
					timer.stop();
					next = new Scene3(w, p); // Adds next scene
					stopSound();
					w.add(next);
					SwingUtilities.updateComponentTreeUI(w);
					w.remove(this);
				}
				
				if (x.getX() < 0) {
					p.setLevel(5);
					p.setX(SCREEN_WIDTH - p.getWidth());
					thread.stop();
					timer.stop();
					next = new Scene5(w, p); // Adds next scene
					stopSound();
					w.add(next);
					SwingUtilities.updateComponentTreeUI(w);
					w.remove(this);
				}

				if (temp.isAlive() == false && p.getDay() < 5) {
					p.setDay(p.getDay() + 1);
					p.setAlive(true);
					p.setLevel(1);
					stopSound();
					playing = false;
					playSound("/video/TOWEr.wav");
					next = new DEATH(w,new Scene(w, p));
					thread.stop();
					timer.stop();
					w.add(next);
					SwingUtilities.updateComponentTreeUI(w);
					w.remove(this);
				}

				if (temp.isAlive() == false && p.getDay() == 5) {
					p.setDay(p.getDay() + 1);
					p.setAlive(true);
					stopSound();
					playSound("/video/TOWEr.wav");
					System.out.println("Darwaza");
					thread.stop();
					timer.stop();
					next = new Starting(w); // Adds death/end screen
					w.add(next);
					SwingUtilities.updateComponentTreeUI(w);
					w.remove(this);
				}
			}

			// Updates the enemy
			else if (x.getId() == ID.Enemy) {
				if (!x.isAlive())
					objects.remove(x);

				else if (x.isAlive()) {

					if (p.getX() < x.getX())
						x.setXspeed(-1);
					if (p.getX() > x.getX())
						x.setXspeed(1);
					if (p.getY() < x.getY())
						x.setYspeed(-1);
					if (p.getY() > x.getY())
						x.setYspeed(1);

					x.update();
				}
			}

			// Updates chest
			else if (x.getId() == ID.chest || x.getId() == ID.Exit_Chest) {
				if (x.isAlive())
					x.update();
				else if (!x.isAlive())
					objects.remove(x);
			}

			// Updates books
			else if (x.getId() == ID.Book && w.me != null) {
				if (x.isAlive()) {
					if (checkwithin(x.getX(), x.getX() + x.getWidth(), w.me.getPoint().x)
							&& checkwithin(x.getY(), x.getY() + x.getHeight(), w.me.getPoint().y)) {
						new Dialog(w, 3);
						x.setAlive(false);
						p.books[1] = true;
					}
				} else if (!x.isAlive())
					objects.remove(x);
			}
		}
	}
}