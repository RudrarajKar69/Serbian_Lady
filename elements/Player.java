package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Player extends GameObject {

	int Screen_Height, level = 1, playerRadius, day;
	private Event_Handler ed;
	String GUN = "Pistol";
	public boolean shot = false, shoot = true, key = false, key2 = false,Q=false;
	Image[] image;
	public boolean[] books = {false,false,false};

	public Player(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id,
			int S_H) {
		super(x, y, width, height, image, alive, id);
		day=1;
		this.image = new Image[2];
		this.Screen_Height = S_H;
	}

	@Override
	public void draw(Graphics g) {

		image[0] = getImage().get(getDay()-1);
		image[1] = getImage().get(getDay()+4);
		
		if (getXspeed() == 0)
			g.drawImage(image[0], getX(), getY(), getWidth(), getHeight(), null);
		if (getXspeed() > 0) {
			g.drawImage(image[1], getX(), getY(), getWidth(), getHeight(), null);
		}
		if (getXspeed() < 0) 
			g.drawImage(image[0], getX(), getY(), getWidth(), getHeight(), null);

	}

	@Override
	public void update(KeyEvent k, MouseEvent m) {

//		if (getLevels() > rnd.nextInt(5)+5)
//			setGUN("Machine Gun");

		setXspeed(0);
		setYspeed(0);
		setEd(new Event_Handler(k, m));

		if(getEd().Q)
			Q=true;
		if(!getEd().Q)
			Q=false;
		if (getEd().Right)
			setXspeed(12);
		if (getEd().Left)
			setXspeed(-12);
		if (getEd().Up)
			setYspeed(-12);
		if (getEd().Down)
			setYspeed(12);

		setX(getX() + getXspeed());
		setY(getY() + getYspeed());

		shooting();
	}

	void shooting() {
		switch (GUN) {
		case "Pistol":
			if (!shoot)
				shot = false;

			if (getEd().Shot && shoot) {
				shot = true;
				shoot = false;
			}
			if (!getEd().Shot) {
				shot = false;
				shoot = true;
			}
			break;
		case "Machine Gun":
			shot = getEd().Shot;
			break;
		}
	}

	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public String getGUN() {
		return GUN;
	}

	public void setGUN(String gUN) {
		GUN = gUN;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int room) {
		this.level = room;
	}

	@Override
	public void update() {
	}
	
	public int getPlayerRadius() {
		return playerRadius;
	}

	public void setPlayerRadius(int playerRadius) {
		this.playerRadius = playerRadius;
	}
	
	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean isKey2() {
		return key2;
	}

	public void setKey2(boolean key2) {
		this.key2 = key2;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Event_Handler getEd() {
		return ed;
	}

	public void setEd(Event_Handler ed) {
		this.ed = ed;
	}
}
