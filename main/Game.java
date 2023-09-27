package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener, MouseListener {

	KeyEvent ke;
	MouseEvent me;

	Game() {
		this.setIconImage((new ImageIcon(getClass().getClassLoader().getResource("player/Player1.png")).getImage()));
		this.setTitle("Serbian Lady(Rudra Edition)");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Starting(this));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		System.out.println(this.getHeight());
//		this.setSize(Scene.SCREEN_WIDTH + 16, Scene.SCREEN_HEIGHT + 39);
		this.setLocationRelativeTo(null);
		this.addKeyListener(this);
		this.addMouseListener(this);

		JOptionPane.showMessageDialog(this, "Tutorials:-\n" + "1)A-D to move\n" + "2)W-S to scroll\n" + "3)Q to pause\n"
				+ "4)Right-Click to shoot\n");

	}
	
	public static void main(String args[])
	{
		new Game();
	}

	KeyEvent getKey() {
		return ke;
	}

	public MouseEvent getMouse() {
		return me;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ke = e;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ke = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		me = e;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		me = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setMouse(MouseEvent e) {
		me=e;
	}

}
