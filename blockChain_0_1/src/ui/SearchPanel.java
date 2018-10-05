package ui;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
import util.Util;
import core.BlockChain;
import core.Transaction;


public class SearchPanel extends JPanel {
	private JPanel tempPan;
	private JPanel infoPan;
	private JButton button;
	private JTextField text;
	private JPanel firstCol;
	private JPanel secondCol;
	private JLabel lab1_1;
	private JLabel lab1_2;
	private JLabel lab1_3;
	private JLabel lab1_4;
	private JLabel lab1_5;
	private JLabel lab1_6;
	private JLabel lab1_7;
	private JLabel lab1_8;
	private JLabel lab1_9;
	private JLabel lab1_10;
	private JLabel lab1_11;
	private JLabel lab2_1;
	private JLabel lab2_2;
	private JLabel lab2_3;
	private JLabel lab2_4;
	private JLabel lab2_5;
	private JLabel lab2_6;
	private JLabel lab2_7;
	private JLabel lab2_8;
	private JLabel lab2_9;
	private JLabel lab2_10;
	private JLabel lab2_11;
	public SearchPanel() {
		tempPan = new JPanel();
		firstCol = new JPanel();
		secondCol = new JPanel();
		lab1_1 = new JLabel();
		lab1_2 = new JLabel();
		lab1_3 = new JLabel();
		lab1_4 = new JLabel();
		lab1_5 = new JLabel();
		lab1_6 = new JLabel();
		lab1_7 = new JLabel();
		lab1_8 = new JLabel();
		lab1_9 = new JLabel();
		lab1_10 = new JLabel();
		lab1_11 = new JLabel();
		lab2_1 = new JLabel();
		lab2_2 = new JLabel();
		lab2_3 = new JLabel();
		lab2_4 = new JLabel();
		lab2_5 = new JLabel();
		lab2_6 = new JLabel();
		lab2_7 = new JLabel();
		lab2_8 = new JLabel();
		lab2_9 = new JLabel();
		lab2_10 = new JLabel();
		lab2_11 = new JLabel();
		tempPan.setLayout(new BorderLayout());
		firstCol.setLayout(new GridLayout(11,1));
		secondCol.setLayout(new GridLayout(11,1));
		firstCol.add(lab1_1);
		firstCol.add(lab1_2);
		firstCol.add(lab1_3);
		firstCol.add(lab1_4);
		firstCol.add(lab1_5);
		firstCol.add(lab1_6);
		firstCol.add(lab1_7);
		firstCol.add(lab1_8);
		firstCol.add(lab1_9);
		firstCol.add(lab1_10);
		firstCol.add(lab1_11);
		secondCol.add(lab2_1);
		secondCol.add(lab2_2);
		secondCol.add(lab2_3);
		secondCol.add(lab2_4);
		secondCol.add(lab2_5);
		secondCol.add(lab2_6);
		secondCol.add(lab2_7);
		secondCol.add(lab2_8);
		secondCol.add(lab2_9);
		secondCol.add(lab2_10);
		secondCol.add(lab2_11);
		button = new JButton("검 색");
		text = new JTextField(28);
		infoPan = new JPanel();
		infoPan.setLayout(new BorderLayout());
		
		infoPan.add(firstCol, BorderLayout.WEST);
		infoPan.add(secondCol, BorderLayout.CENTER);	
		tempPan.add(text, BorderLayout.CENTER);
		tempPan.add(button, BorderLayout.EAST);
		tempPan.add(infoPan, BorderLayout.SOUTH);
		setLayout(new FlowLayout());
		add(new JLabel(" "));
		add(tempPan);
	
		
		// ActionListener listener =  new SearchButtonActionListener(text, infoPan);
		button.addActionListener(e -> {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				String addr = new String(text.getText());
				BlockChain bc = BlockChain.getInstance();
				Transaction temp = new Transaction();
				temp.copyTransaction(bc.getLastTransaction(addr));
				if(temp.getType() == Util.trType.TRADE) {
				//9	
					lab1_1.setText("주소: ");
					lab2_1.setText(temp.getAddr());
					lab1_2.setText("계약 종류: ");
					lab2_2.setText("매매");
					lab1_3.setText("매도자:");
					lab2_3.setText(temp.getOwner());
					lab1_4.setText("매수자: ");
					lab2_4.setText(temp.getBuyer());
					lab1_5.setText("계약일: ");
					lab2_5.setText(temp.getContractDate());
					lab1_6.setText("총 금액(원): ");
					lab2_6.setText(Integer.toString(temp.getTotalPrice()));
					lab1_7.setText("계약금(원): ");
					lab2_7.setText(Integer.toString(temp.getDeposit()));
					lab1_8.setText("잔금(원): ");
					lab2_8.setText(Integer.toString(temp.getBalance()));
					lab1_9.setText("잔금 납부일: ");
					lab2_9.setText(temp.getBalanceDate());
					lab1_10.setText("현재 소유자: ");
					lab2_10.setText(temp.getPrevOwner());
					lab1_11.setText("");
					lab2_11.setText("");
				}
				else if(temp.getType() == Util.trType.CHARTER) {
				//9	
					lab1_1.setText("주소: ");
					lab2_1.setText(temp.getAddr());
					lab1_2.setText("계약 종류: ");
					lab2_2.setText("전세");
					lab1_3.setText("소유자:");
					lab2_3.setText(temp.getOwner());
					lab1_4.setText("세입자: ");
					lab2_4.setText(temp.getBuyer());
					lab1_5.setText("계약일: ");
					lab2_5.setText(temp.getContractDate());
					lab1_6.setText("보증금(원): ");
					lab2_6.setText(Integer.toString(temp.getTotalPrice()));
					lab1_7.setText("계약금(원): ");
					lab2_7.setText(Integer.toString(temp.getDeposit()));
					lab1_8.setText("잔금(원): ");
					lab2_8.setText(Integer.toString(temp.getBalance()));
					lab1_9.setText("잔금 납부일: ");
					lab2_9.setText(temp.getBalanceDate());
					lab1_10.setText("전세권자: ");
					lab2_10.setText(temp.getChartedPerson());
					lab1_11.setText("");
					lab2_11.setText("");
				}
				else if(temp.getType() == Util.trType.RENT) {
				//10
					lab1_1.setText("주소: ");
					lab2_1.setText(temp.getAddr());
					lab1_2.setText("계약 종류: ");
					lab2_2.setText("월세");
					lab1_3.setText("소유자:");
					lab2_3.setText(temp.getOwner());
					lab1_4.setText("세입자: ");
					lab2_4.setText(temp.getBuyer());
					lab1_5.setText("계약일: ");
					lab2_5.setText(temp.getContractDate());
					lab1_6.setText("보증금(원): ");
					lab2_6.setText(Integer.toString(temp.getTotalPrice()));
					lab1_7.setText("계약금(원): ");
					lab2_7.setText(Integer.toString(temp.getDeposit()));
					lab1_8.setText("잔금(원): ");
					lab2_8.setText(Integer.toString(temp.getBalance()));
					lab1_9.setText("잔금 납부일: ");
					lab2_9.setText(temp.getBalanceDate());
					lab1_10.setText("월세: ");
					lab2_10.setText(temp.getRentFee());
					lab1_11.setText("임차인: ");
					lab2_11.setText(temp.getTenant());
				}
				else {
					System.out.println("검색 실패");
				}
			});
		});
	}
}
