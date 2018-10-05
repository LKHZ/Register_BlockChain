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
		tabPanel.addTab("등기 검색", new SearchPanel());
		tabPanel.addTab("매매",  new TradePanel());
		tabPanel.addTab("전세", new CharterPanel());
		tabPanel.addTab("월세", new RentPanel());

		add(tabPanel, BorderLayout.NORTH);
	}
}
