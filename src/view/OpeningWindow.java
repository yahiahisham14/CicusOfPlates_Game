package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class OpeningWindow extends JFrame {

	private Controller control = Controller.createController();
	private JPanel contentPane;
	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int width = d.width;
	int hight = d.height;

	/**
	 * Create the frame.
	 */
	public OpeningWindow() {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, hight);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoadGame.setBounds(314, 23, 189, 81);
		contentPane.add(btnLoadGame);

		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				control.runGame();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(23, 23, 189, 81);
		contentPane.add(btnNewButton);
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					control.loadFile();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);

		Image elyoyy = new ImageIcon("wall.jpg").getImage();
		g.drawImage(elyoyy, 0, 0,width ,hight
				, null);

	}
}
