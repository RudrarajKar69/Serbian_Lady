package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import elements.Book;
import elements.Chest;
import elements.Enemy;
import elements.Exit_Chest;
import elements.GameObject;
import elements.ID;
import elements.Player;

@SuppressWarnings("serial")
public abstract class Base_Scene extends JLabel implements ActionListener, Runnable {

	public static final int COLS = 15;

	public static final int SCREEN_WIDTH = 1366;
	public static final int SCREEN_HEIGHT = 700;

	public static final int TILE_SIZE = SCREEN_WIDTH / COLS;

	Timer timer;
	Thread thread;

	boolean running;

	public boolean music = true, playing;

	int c;
	static int chestRoom, cx, cy, ex, ey, bookroom, bx, by;

	ArrayList<GameObject> objects, enemies;
	Player p;
	Game w;
	JLabel next;
	Random rnd;
	Image floor;
	ArrayList<Image> player, enemy, chest, book;
	static Clip s; 
	Clip currentlyPlayingClip;

	Base_Scene(Game w) {
		c = 0;
		playing = false;
		player = new ArrayList<Image>();
		enemy = new ArrayList<Image>();
		chest = new ArrayList<Image>();
		book = new ArrayList<Image>();
		for (int i = 1; i <= 10; i++) {
			player.add(new ImageIcon(getClass().getClassLoader().getResource("player/Player" + i + ".png")).getImage());
		}
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy.png")).getImage());
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy1.png")).getImage());
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy2.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("Chest.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("Exit_Chest.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("RedKey.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("BlueKey.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book1.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book2.png")).getImage());

		this.w = w;

		rnd = new Random();

		timer = new Timer(1000 / (1000 / 60), this);
		thread = new Thread(this);

		running = true;

		objects = new ArrayList<GameObject>();
		enemies = new ArrayList<GameObject>();
		p = new Player(rnd.nextInt(0, SCREEN_WIDTH - TILE_SIZE), rnd.nextInt(0, SCREEN_HEIGHT - TILE_SIZE), TILE_SIZE,
				TILE_SIZE, player, true, ID.Player, SCREEN_WIDTH);

		objects.add(p);

		timer.start();
		thread.start();

		if (music)
			playMusic();
		p.setDay(1);
		chestRoom = 3;
		bookroom = rnd.nextInt(0, 4);
		bx = rnd.nextInt(TILE_SIZE, SCREEN_WIDTH - TILE_SIZE);
		by = rnd.nextInt(TILE_SIZE, SCREEN_HEIGHT - TILE_SIZE);

		cx = rnd.nextInt(TILE_SIZE, SCREEN_WIDTH - TILE_SIZE);
		cy = rnd.nextInt(TILE_SIZE, SCREEN_HEIGHT - TILE_SIZE);

		ex = rnd.nextInt(0, SCREEN_WIDTH - TILE_SIZE);
		ey = rnd.nextInt(0, SCREEN_HEIGHT - TILE_SIZE);

		if (chestRoom == p.getLevel() && p.isKey() == false)
			objects.add(new Chest(cx, cy, TILE_SIZE, TILE_SIZE, chest, true, ID.chest, this.p));

		if (p.getLevel() == 8)
			objects.add(new Exit_Chest(ex, ey, TILE_SIZE, TILE_SIZE, chest, true, ID.Exit_Chest, this.p));

		if (bookroom == 0)
			if (!p.books[0] && p.getLevel() == 1)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 1)
			if (!p.books[0] && p.getLevel() == 2)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 2)
			if (!p.books[0] && p.getLevel() == 4)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 3)
			if (!p.books[0] && p.getLevel() == 5)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		
	}

	Base_Scene(Game w, Player p) {
		c = 0;
		playing = false;	
		this.p = p;
		player = new ArrayList<Image>();
		enemy = new ArrayList<Image>();
		chest = new ArrayList<Image>();
		book = new ArrayList<Image>();
		for (int i = 1; i <= 10; i++) {
			player.add(new ImageIcon(getClass().getClassLoader().getResource("player/Player" + i + ".png")).getImage());
		}
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy.png")).getImage());
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy1.png")).getImage());
		enemy.add(new ImageIcon(getClass().getClassLoader().getResource("Enemy2.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("Chest.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("Exit_Chest.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("RedKey.png")).getImage());
		chest.add(new ImageIcon(getClass().getClassLoader().getResource("BlueKey.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book1.png")).getImage());
		book.add(new ImageIcon(getClass().getClassLoader().getResource("Book2.png")).getImage());

		System.out.println(bookroom+" "+bx+" "+by);

		this.w = w;

		rnd = new Random();

		timer = new Timer(1000 / (1000 / 60), this);
		thread = new Thread(this);

		running = true;

		objects = new ArrayList<GameObject>();
		enemies = new ArrayList<GameObject>();

		objects.add(this.p);

		if (chestRoom == p.getLevel() && p.isKey() == false)
			objects.add(new Chest(cx, cy, TILE_SIZE, TILE_SIZE, chest, true, ID.chest, this.p));
		if (p.getLevel() == 8)
			objects.add(new Exit_Chest(ex, ey, TILE_SIZE, TILE_SIZE, chest, true, ID.Exit_Chest, this.p));
		if (bookroom == 0)
			if (!p.books[0] && p.getLevel() == 1)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 1)
			if (!p.books[0] && p.getLevel() == 2)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 2)
			if (!p.books[0] && p.getLevel() == 4)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		if (bookroom == 3)
			if (!p.books[0] && p.getLevel() == 5)
				objects.add(new Book(bx, by, TILE_SIZE, TILE_SIZE, book, true, ID.Book,p.getLevel()));
		
		timer.start();
		thread.start();
	}

	public void playSound(String filePath) {
		try {
			// Stop any previously playing sound
			stopSound();

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

			// Set the currently playing clip
			try {
				currentlyPlayingClip = clip;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopSound() {
		if (currentlyPlayingClip != null && currentlyPlayingClip.isRunning()) {
			currentlyPlayingClip.stop();
			currentlyPlayingClip.close();
		}
	}

	public void playMusic() {
		try {
			if (s == null) {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(Base_Scene.class.getResource("/MUSIC.wav"));
				s = AudioSystem.getClip();
				s.open(audioInputStream);
				// Add a listener to handle the sound completion event
				s.addLineListener(event -> {
					if (event.getType() == LineEvent.Type.STOP) {
						s.setFramePosition(0); // Reset the clip's position to the beginning
						s.start(); // Start playing the sound again
					}
				});
			}

			s.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean checkwithin(int start, int end, int value) {
		for (int i = start; i <= end; i++) {
			if (i == value)
				return true;
		}

		return false;
	}

	Image flooring(Image I) {
		BufferedImage tintedImage = new BufferedImage(I.getWidth(null), I.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Get the Graphics object for drawing on the tintedImage
		Graphics2D g2d = tintedImage.createGraphics();

		// Draw the original image onto the tintedImage
		g2d.drawImage(I, 0, 0, null);

		// Apply the black tint by manipulating the pixels
		int width = tintedImage.getWidth();
		int height = tintedImage.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Get the pixel color at (x, y)
				int rgb = tintedImage.getRGB(x, y);
				Color color = new Color(rgb, true);

				// Reduce all RGB components to create a black tint
				int red = Math.max(0, color.getRed() - 100);
				int green = Math.max(0, color.getGreen() - 100);
				int blue = Math.max(0, color.getBlue() - 100);

				// Create a new color with the reduced RGB components
				Color tintedColor = new Color(red, green, blue, color.getAlpha());

				// Set the tinted color at (x, y)
				tintedImage.setRGB(x, y, tintedColor.getRGB());
			}
		}

		return tintedImage;
	}

	protected void paintComponent(Graphics g) {

		int rangeMultiplier = 2;
		g.drawImage(flooring(floor), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT+10, null);
		Graphics2D g2d = (Graphics2D) g;

		p.setPlayerRadius(TILE_SIZE * rangeMultiplier);
		// Set the clipping region as before
		Shape clip = new Ellipse2D.Double(p.getX() - p.getPlayerRadius(), p.getY() - p.getPlayerRadius(),
				p.getPlayerRadius() * 2, p.getPlayerRadius() * 2);
		g2d.setClip(clip);

		// Paint the entire panel with a black background within the clipping region
		g.drawImage(floor, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

		// Reset the clipping region to paint other objects normally
		g2d.setClip(null);

		// Loop through and draw all game objects
		draw(g);

	}

	void enemy_Spawner() {

		int rangeMultiplier = 3;
		deSpawner();
		c = rnd.nextInt(1, 3);
		for (int i = 0; i < c; i++) {
			GameObject temp;
			switch (rnd.nextInt(1, 5)) {
			case 1:
				temp = new Enemy(
						rnd.nextInt((p.getX() - (TILE_SIZE * rangeMultiplier)),
								(p.getX() + (TILE_SIZE * rangeMultiplier))),
						(p.getY() - (TILE_SIZE * rangeMultiplier)), TILE_SIZE + 20, TILE_SIZE + 20, enemy, true,
						ID.Enemy, p, p.getLevel());
				break;
			case 2:
				temp = new Enemy(
						rnd.nextInt((p.getX() - (TILE_SIZE * rangeMultiplier)),
								(p.getX() + (TILE_SIZE * rangeMultiplier))),
						(p.getY() + (TILE_SIZE * rangeMultiplier)), TILE_SIZE + 20, TILE_SIZE + 20, enemy, true,
						ID.Enemy, p, p.getLevel());
				break;
			case 3:
				temp = new Enemy((p.getX() + (TILE_SIZE * rangeMultiplier)),
						rnd.nextInt((p.getY() - (TILE_SIZE * rangeMultiplier)),
								(p.getY() + (TILE_SIZE * rangeMultiplier))),
						TILE_SIZE + 20, TILE_SIZE + 20, enemy, true, ID.Enemy, p, p.getLevel());
				break;
			default:
				temp = new Enemy((p.getX() - (TILE_SIZE * rangeMultiplier)),
						rnd.nextInt((p.getY() - (TILE_SIZE * rangeMultiplier)),
								(p.getY() + (TILE_SIZE * rangeMultiplier))),
						TILE_SIZE + 20, TILE_SIZE + 20, enemy, true, ID.Enemy, p, p.getLevel());
				break;
			}

			objects.add(temp);// Adds it to list objects
			if (enemies != null)
				enemies.add(temp);// Adds it to list enemies
		}
	}

	void deSpawner() {
		ArrayList<GameObject> temp = new ArrayList<GameObject>();
		for (GameObject x : objects) {
			temp.add(x);
		}
		objects.clear();

		for (GameObject x : temp) {
			if (x.getId() != ID.Enemy)
				objects.add(x);
		}
		enemies.clear();
	}

	void draw(Graphics g) {

		if (enemies != null) {
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Room: " + p.getLevel(), ((SCREEN_WIDTH - metrics.stringWidth("Room: " + p.getLevel())) / 2) - 80,
					g.getFont().getSize());
			g.drawString("Day: " + p.getDay(), ((SCREEN_WIDTH - metrics.stringWidth("Day: " + p.getDay())) / 2) + 80,
					g.getFont().getSize());
		}

		for (GameObject x : objects) {
			if (x.getId() != ID.Player) {
				if (x.isWithinRadius(p.getX(), p.getY(), p.getPlayerRadius()))
					x.draw(g);
			} else if (x.getId() == ID.Player) {
				int tx = x.getX(), ty = x.getY();
				x.setX(x.getX() - x.getWidth() / 2);
				x.setY(x.getY() - x.getHeight() / 2);
				x.draw(g);
				x.setX(tx);
				x.setY(ty);
			}
		}
	}

	// Draws
	@Override
	public void run() {
		while (running) {
			repaint();
		}
	}

	// Updates
	@Override
	abstract public void actionPerformed(ActionEvent e);
}