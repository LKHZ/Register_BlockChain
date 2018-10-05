package ui;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
import core.BlockChain;
import core.Transaction;
import core.Wallet;
import web.WebDoc;

public class RentPanel extends JPanel {
	private JPanel firstCol;
	private JPanel secondCol;
	private JPanel bottom;

	private JTextField addrText;
	private JTextField sellerText;
	private JTextField buyerText;
	private JTextField depositText;
	private JTextField totalPriceText;
	private JTextField rentFeeText;

	private JButton startButton;
	private JButton finishButton;

	public RentPanel() {
		firstCol = new JPanel();
		firstCol.setLayout(new GridLayout(6,1));
		firstCol.add(new Label("주 소: "));
		firstCol.add(new Label("소유자: "));
		firstCol.add(new Label("세입자: "));
		firstCol.add(new Label("계약금(원):"));
		firstCol.add(new Label("보증금(원):"));
		firstCol.add(new Label("월세(원):"));

		sellerText = new JTextField(30);
		addrText = new JTextField(30);
		buyerText = new JTextField(30);
		depositText = new JTextField(30);
		totalPriceText = new JTextField(30);
		rentFeeText = new JTextField(30);

		secondCol = new JPanel();
		secondCol.setLayout(new GridLayout(6,1));
		secondCol.add(addrText);
		secondCol.add(sellerText);
		secondCol.add(buyerText);
		secondCol.add(depositText);
		secondCol.add(totalPriceText);
		secondCol.add(rentFeeText);
		
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
				String rentFeeString = rentFeeText.getText();
				int deposit = Integer.parseInt(depositString);
				int totalPrice = Integer.parseInt(totalPriceString);
				int rentFee = Integer.parseInt(rentFeeString);
				Wallet wallet = new Wallet();
				try {
					wallet.setFromFile(seller);
					BlockChain bc = BlockChain.getInstance();
					//소유자 wallet, type, seller, buyer, totalprice, deposit, addr
					Transaction transaction = new Transaction(wallet, seller, buyer, totalPrice, deposit, addr, rentFee);
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
