package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.*;


public class Generator {

	private JFrame f;
	private JLabel l1, l2, l3, l4, l5, platzhalter1, l6;
	private JTextField tf1;
	private JButton b1, b2, b3, b4;
	private JMenuBar menuBar;
	private JMenu menuSettings, menuExit;
	private JMenuItem menuItemExit;
	private JCheckBox checkbox_kb, checkbox_gb, checkbox_z, checkbox_s;
	
	public Generator() {

		f = new JFrame();
		
		menuBar = new JMenuBar();
		menuSettings = new JMenu("Settings");
		menuExit = new JMenu("Exit");
		
		menuBar.add(menuSettings);
		
		checkbox_kb = new JCheckBox("Kleinbuchstaben");
		checkbox_gb = new JCheckBox("Großbuchstaben");
		checkbox_z = new JCheckBox("Zahlen");
		checkbox_s = new JCheckBox("Sonderzeichen");
		
		checkbox_kb.setSelected(true);
		checkbox_gb.setSelected(true);
		checkbox_z.setSelected(true);
		checkbox_s.setSelected(true);
		
		menuSettings.add(checkbox_kb);
		menuSettings.add(checkbox_gb);
		menuSettings.add(checkbox_z);
		menuSettings.add(checkbox_s);
		
		menuBar.add(menuExit);
		
		menuItemExit = new JMenuItem(new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		
		menuExit.add(menuItemExit);
		
		f.setJMenuBar(menuBar);
		
		l1 = new JLabel("");
		
		l6 = new JLabel("Passwortlaenge: ");

		l6.setVerticalAlignment(JLabel.CENTER);
		l6.setHorizontalAlignment(JLabel.CENTER);

		l2 = new JLabel();
		l2.setVerticalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);

		tf1 = new JTextField();
		tf1.setHorizontalAlignment(JLabel.CENTER);
		tf1.setText("8");

		b1 = new JButton("Passwort generieren");
		b1.setVerticalAlignment(JLabel.CENTER);
		b1.setHorizontalAlignment(JLabel.CENTER);

		b2 = new JButton("Passwort kopieren");
		b2.setVerticalAlignment(JLabel.CENTER);
		b2.setHorizontalAlignment(JLabel.CENTER);

		l3 = new JLabel("");
		l3.setVerticalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.CENTER);
		
		l4 = new JLabel("");
		l5 = new JLabel("");
		
		b3 = new JButton("+");
		b3.setVerticalAlignment(JLabel.CENTER);
		b3.setHorizontalAlignment(JLabel.CENTER);
		
		b4 = new JButton("-");
		b4.setVerticalAlignment(JLabel.CENTER);
		b4.setHorizontalAlignment(JLabel.CENTER);
		
		platzhalter1 = new JLabel("");
		
		
	}

	public void frameFestlegen() {

		f.setTitle("Passwortgenerator 1.0");

		f.setSize(500, 500);

		f.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.setLayout(new GridLayout(6, 1));
		
		f.add(l1);
		
		l1.setLayout(new GridLayout(1,2));
		l1.add(l6);
		l1.add(platzhalter1);
		
		//f.add(tf1);
		f.add(l4);
		f.add(b1);
		f.add(l2);
		f.add(b2);
		f.add(l3);
		
		l4.setLayout(new GridLayout(1,2));
		
		l4.add(tf1);
		l4.add(l5);
		
		l5.setLayout(new GridLayout(2,1));
		
		l5.add(b3);
		l5.add(b4);

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					int pwlength = Integer.parseInt(tf1.getText());

					l2.setForeground(Color.black);
					l2.setText(Integer.toString(pwlength));

					int i = 1;
					int length = pwlength;
					
					String kleinbuchstaben = "abcdefghijklmnopqrstuvxxyz";
					String grossbuchstaben = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					String zahlen = "1234567890";
					String sonderzeichen = "!?@";
					
					String pw = "";
					
					if (checkbox_kb.isSelected()) {
						pw = pw + kleinbuchstaben;
					}
					if (checkbox_gb.isSelected()) {
						pw = pw + grossbuchstaben;
					}
					if (checkbox_z.isSelected()) {
						pw = pw + zahlen;
					}
					if (checkbox_s.isSelected()) {
						pw = pw + sonderzeichen;
					}
					
					char[] chars = pw.toCharArray();
					
					StringBuilder stringbuilder = new StringBuilder();
					Random random = new Random();
					while (i <= length) {
						char c = chars[random.nextInt(chars.length)];
						stringbuilder.append(c);
						i = i + 1;
					}

					String code = stringbuilder.toString();

					l2.setText(code);

					// Label 3 leeren
					l3.setForeground(Color.black);
					l3.setText("");

				} catch (NumberFormatException nfe) {
					l2.setForeground(Color.red);
					l2.setText("Bitte nur Zahlen eingeben!");
					// l2.setForeground(Color.black);
				}
			}

		});
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				StringSelection stringSelection = new StringSelection(l2.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

				l3.setForeground(Color.red);
				l3.setText("Passwort kopiert");

			}

		});
		
		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					int laenge = Integer.parseInt(tf1.getText()) + 1;
					tf1.setText(String.valueOf(laenge));
					
			}
		});
		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					int laenge = Integer.parseInt(tf1.getText());
					if (laenge != 0 && laenge > 0) {
						laenge = laenge - 1;
						tf1.setText(String.valueOf(laenge));
					}
					
			}
		});
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	public static void main(String[] args) {

		Generator pwgen = new Generator();
		pwgen.frameFestlegen();
	}

}
