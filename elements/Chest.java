package elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.Base_Scene;

public class Chest extends GameObject{

	boolean unlock=false;
	Player p;
	public Chest(int x, int y, int width, int height, ArrayList<Image> image, boolean alive, ID id,Player p) {
		super(x, y, width, height, image, alive, id);
		this.p=p;
	}

	@Override
	public void draw(Graphics g) {

		if(!unlock)
			g.drawImage(getImage().get(0), getX(), getY(), getWidth(), getHeight(), null);
		if(unlock)
			g.drawImage(getImage().get(2), getX(), getY(), getWidth(), getHeight(), null);

	}

	@Override
	public void update() {
		
		
		if(AABB(this,p) && !unlock)
		{
			p.setX(p.getX()-Base_Scene.TILE_SIZE);
			p.setY(p.getY()-Base_Scene.TILE_SIZE);
			unlock = true;
		}
		
		if(AABB(this,p) && unlock)
		{
			p.setKey(true);
			this.setAlive(false);
		}
	}
	
	boolean AABB(Chest base_Enemy, Player e) {
		if (base_Enemy.getX() < e.getX() + e.getWidth() && base_Enemy.getX() + base_Enemy.getWidth() > e.getX()
				&& base_Enemy.getY() < e.getY() + e.getHeight() && base_Enemy.getY() + base_Enemy.getHeight() > e.getY())
			return true;
		return false;

	}

	@Override
	public void update(KeyEvent k, MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

}
