package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Generator {

	private JFrame f;
	private JLabel l1, l2, l3, l4, l5, platzhalter1, l6;
	private JTextField tf1;
	private JButton b1, b2, b3, b4;
	private JMenuBar menuBar;
	private JMenu menuSettings, menuData, menuExit;
	private JMenuItem menuItemExit, selectFile, saveSettings;
	private JCheckBox checkbox_kb, checkbox_gb, checkbox_z, checkbox_s;
	public String selectedFile;
	
	public Generator() {

		f = new JFrame();
		
		menuBar = new JMenuBar();
		menuSettings = new JMenu("Settings");
		menuExit = new JMenu("Exit");
		menuData = new JMenu("Datei");
		
		// MenuBar definieren
		menuBar.add(menuData);
		menuBar.add(menuSettings);
		menuBar.add(menuExit);
		
		
		checkbox_kb = new JCheckBox("Kleinbuchstaben");
		checkbox_gb = new JCheckBox("Grossbuchstaben");
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
		
		platzhalter1 = new JLabel("");
		platzhalter1.setVerticalAlignment(JLabel.CENTER);
		platzhalter1.setHorizontalAlignment(JLabel.CENTER);
		platzhalter1.setForeground(Color.red);
		
		
		selectFile = new JMenuItem(new AbstractAction("Import Settings") {
			public void actionPerformed(ActionEvent f) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Configuration file", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = chooser.getSelectedFile().getAbsolutePath();
					
					FileReader input;
					try {
						input = new FileReader(selectedFile);
						BufferedReader bufRead = new BufferedReader(input);
						String myLine = null;
						
						platzhalter1.setText("Config loaded!");

						while ( (myLine = bufRead.readLine()) != null)
						{    
							String[] array = myLine.split(":");
						    
						    array[1] = array[1].replaceAll("\\s","");
						    
						    if(array[0].equals(checkbox_kb.getText())) {
						    	checkbox_kb.setSelected(Boolean.parseBoolean(array[1]));
						    }
						    else if(array[0].equals(checkbox_gb.getText())) {
						    	checkbox_gb.setSelected(Boolean.parseBoolean(array[1]));
						    }
						    else if(array[0].equals(checkbox_z.getText())) {
						    	checkbox_z.setSelected(Boolean.parseBoolean(array[1]));
						    }
						    else if(array[0].equals(checkbox_s.getText())) {
						    	checkbox_s.setSelected(Boolean.parseBoolean(array[1]));
						    }
						    else if(array[0].equals("Passwortlaenge")) {
						    	tf1.setText(array[1]);
						    }
						    else {

						    }
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		
		
		saveSettings = new JMenuItem(new AbstractAction("Save Settings") {
			public void actionPerformed(ActionEvent g) {
				String line1 = checkbox_kb.getText() + ": "+ String.valueOf(checkbox_kb.isSelected());
				String line2 = checkbox_gb.getText() + ": " + String.valueOf(checkbox_gb.isSelected());
				String line3 = checkbox_z.getText() + ": " + String.valueOf(checkbox_z.isSelected());
				String line4 = checkbox_s.getText() + ": " + String.valueOf(checkbox_s.isSelected());
				String line5 = "Passwortlaenge: " + tf1.getText();
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					
					String path = chooser.getSelectedFile().getAbsolutePath();
					
					BufferedWriter writer;
					try {
						writer = new BufferedWriter(new FileWriter(path + "\\settings.txt"));
						writer.write(line1);
	                    writer.newLine();
	                    writer.write(line2);
	                    writer.newLine();
	                    writer.write(line3);
	                    writer.newLine();
	                    writer.write(line4);
	                    writer.newLine();
	                    writer.write(line5);
	                    
	                    writer.close();
	                    
	                    platzhalter1.setText("Settings saved!");
	                    platzhalter1.setForeground(Color.green);
	                    
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		menuData.add(selectFile);
		menuData.add(saveSettings);
		
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
		
	}

	public void frameFestlegen() {

		f.setTitle("Passwortgenerator 2.0");

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
