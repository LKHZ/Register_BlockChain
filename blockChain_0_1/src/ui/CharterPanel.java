package ui;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
import core.BlockChain;
import core.Transaction;
import core.Wallet;
import util.Util;
import web.WebDoc;

public class CharterPanel extends JPanel{
	private JPanel firstCol;
	private JPanel secondCol;
	private JPanel bottom;

	private JTextField addrText;
	private JTextField sellerText;
	private JTextField buyerText;
	private JTextField depositText;
	private JTextField totalPriceText;

	private JButton startButton;
	private JButton finishButton;

	public CharterPanel() {
		firstCol = new JPanel();
		firstCol.setLayout(new GridLayout(5,1));
		firstCol.add(new Label("주 소: "));
		firstCol.add(new Label("소유자: "));
		firstCol.add(new Label("세입자: "));
		firstCol.add(new Label("계약금(원):"));
		firstCol.add(new Label("총금액(원):"));

		sellerText = new JTextField(30);
		buyerText = new JTextField(30);
		depositText = new JTextField(30);
		totalPriceText = new JTextField(30);
		addrText = new JTextField(30);

		secondCol = new JPanel();
		secondCol.setLayout(new GridLayout(5,1));
		secondCol.add(addrText);
		secondCol.add(sellerText);
		secondCol.add(buyerText);
		secondCol.add(depositText);
		secondCol.add(totalPriceText);
		
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 2));
		startButton = new JButton("계약 시작");
		finishButton = new JButton("계약 종료");
		bottom.add(startButton);
		bottom.add(finishButton);
		
		setLayout(new BorderLayout());
		add(firstCol, BorderLayout.WEST);
		add(secondCol, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		//계약시작 button event handler
		startButton.addActionListener(e -> {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				String addr = addrText.getText();
				String seller = sellerText.getText();
				String buyer = buyerText.getText();
				String depositString = depositText.getText();
				String totalPriceString = totalPriceText.getText();
				int deposit = Integer.parseInt(depositString);
				int totalPrice = Integer.parseInt(totalPriceString);

				Wallet wallet = new Wallet();
				try {
					wallet.setFromFile(seller);
					BlockChain bc = BlockChain.getInstance();
					//소유자 wallet, type, seller, buyer, totalprice, deposit, addr
					Transaction transaction = new Transaction(wallet, Util.trType.CHARTER, seller, buyer, totalPrice, deposit, addr);
					//전세계약 맺기 Transaction 블록체인에 넣기
					bc.addMakeContractTransaction(transaction);
					WebDoc.makePage(addr);
				}
				catch (Exception e1){	}
			});
		});
		
		//계약종료 button event handler
		finishButton.addActionListener(e -> {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				String addr = addrText.getText();
				String seller = sellerText.getText();
				Wallet wallet = new Wallet();
				try {
					wallet.setFromFile(seller);
					BlockChain bc = BlockChain.getInstance();
					//전세계약 끝내기 Transaction 생성
					bc.addFinishContractTransaction(wallet, addr);
					WebDoc.makePage(addr);
				}
				catch (Exception e2) {	}
			});
		});
	}
}
