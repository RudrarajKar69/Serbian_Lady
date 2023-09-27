package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Book extends GameObject{

	int room;
	public Book(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id,int room) {
		super(x, y, width, height, image, alive, id);
		this.room=room;
	}

	@Override
	public void draw(Graphics g) {
		
		if(room==6)
			g.drawImage(getImage().get(1), getX(), getY(), getWidth(), getHeight(), null);
		else if(room==7)
			g.drawImage(getImage().get(2), getX(), getY(), getWidth(), getHeight(), null);
		else
			g.drawImage(getImage().get(0), getX(), getY(), getWidth(), getHeight(), null);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void update(KeyEvent k, MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

}
