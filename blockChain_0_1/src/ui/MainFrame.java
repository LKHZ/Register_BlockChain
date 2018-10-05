package ui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		setTitle("Block Chain");
		setLocation(500,  400);
		setPreferredSize(new Dimension(430, 310));
		setLayout(new BorderLayout());
		Container cp = getContentPane();
		cp.add(new MainPanel(),BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
