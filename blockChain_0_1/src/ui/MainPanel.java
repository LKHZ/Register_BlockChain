package ui;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel{
	private JTabbedPane tabPanel;
	//private JPanel searchPan;
	public MainPanel() {
		setLayout(new BorderLayout());
		tabPanel = new JTabbedPane();
		//searchPan = new JPanel();
		tabPanel.addTab("��� �˻�", new SearchPanel());
		tabPanel.addTab("�Ÿ�",  new TradePanel());
		tabPanel.addTab("����", new CharterPanel());
		tabPanel.addTab("����", new RentPanel());

		add(tabPanel, BorderLayout.NORTH);
	}
}
