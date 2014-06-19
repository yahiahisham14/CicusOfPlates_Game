package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import model.Plate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GuiClass extends JFrame implements Runnable, KeyListener,
		MouseMotionListener, MouseListener, WindowListener, Serializable {

	private JPanel contentPane;

	// variables
	private ArrayList<Plate> Platepool;
	private ArrayList<Plate> drawings;
	private boolean up;
	private Controller control;
	private Graphics doubleBuffer;
	private Image i;
	private boolean end;

	public GuiClass() {
		control = Controller.createController();
		Platepool = new ArrayList<Plate>();
		drawings = new ArrayList<Plate>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 2000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addWindowListener(this);

		setContentPane(contentPane);
		Thread thread = new Thread(this);
		thread.start();
	}

	public void setPool(ArrayList<Plate> newPool) {
		Platepool = newPool;
		for (int i = 0; i < Platepool.size(); i++) {
			drawings.add(Platepool.get(i));
		}
	}

	@Override
	public void run() {
		while (true) {
			
				if (end){
					 
					
					dispose();
					JOptionPane.showConfirmDialog(null,
							"Game Ended", "EndGame",
							JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
					break;
				}
				repaint();
				 
				Iterator iterator = control.getIterator();

				while (iterator.hasNext()) {
					Object ob = iterator.next();
					Plate p = (Plate) ob;
					if (!p.isCaught()) {
						control.update(p);
					} else {
						control.updateCargo(p);
					}
				}

				try {
					Thread.sleep(19);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	

	@Override
	public void update(Graphics g) {

		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleBuffer = i.getGraphics();
		}

		doubleBuffer.setColor(getBackground());
		doubleBuffer
				.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleBuffer.clearRect(0, 0, this.getSize().width,
				this.getSize().height);

		doubleBuffer.setColor(getForeground());
		up = true;
		paint(doubleBuffer);
		g.drawImage(i, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {

		if (!up)
			update(g);
		// g.clearRect(0, 0, this.getSize().width, this.getSize().height);
		
		Font bigFont = new Font("Serif", Font.BOLD, 50);
		
		// First Texts.
		g.setColor(Color.BLACK);
		g.setFont(bigFont);
		g.drawString(control.getFirstPlayerName(), 20, 80);
		g.drawString(control.getSecondPlayerName(), 1500, 80);
		g.setColor(Color.RED);
		g.drawString(control.getFirstPlayerScore() + "", 300, 80);
		g.drawString(control.getSecondPlayerScore() + "", 1800, 80);
		Iterator iterator = control.getIterator();

		while (iterator.hasNext()) {
			Object ob = iterator.next();
			Plate p = (Plate) ob;
			g.setColor(p.getColor());
			g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		}

		// Printing players:

		Image elyoyy = new ImageIcon("mickey.gif").getImage();

		g.drawImage(elyoyy, control.getFirstPlayerX(),
				control.getFirstPlayery(), 300, 300, this);
		g.drawImage(elyoyy, control.getSecondPlayerX(),
				control.getSecondPlayerY(), 300, 300, this);

		up = false;

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			control.setFirstPlayerX(1);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			control.setFirstPlayerX(2);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			control.setSecondPlayerX(1);
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			control.setSecondPlayerX(2);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			control.setSecondPlayerX(1);
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			control.setSecondPlayerX(2);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		int confirmed = JOptionPane.showConfirmDialog(null,
				"Do you want to save the game?", "Saving",
				JOptionPane.YES_NO_OPTION);

		String input = "text";
		if (input != null) {
			try {
				control.saveFile(input);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
