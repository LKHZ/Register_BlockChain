package blockChain_0_1;

import java.security.Security;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import core.BlockChain;
import core.Transaction;
import core.Wallet;
import ui.MainFrame;
import util.EC;
import util.Util;
import web.WebDoc;

public class Program {
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		BlockChain bc;
		Security.addProvider(new BouncyCastleProvider());
		EC ec = new EC();
		ec.generate("이경훈");
		ec.generate("오미숙");
		ec.generate("김도윤");
		ec.generate("송한솔");
		
		Wallet wallet1 = new Wallet();
		wallet1.setFromFile("이경훈");
		Wallet wallet2 = new Wallet();
		wallet2.setFromFile("오미숙");
		Wallet wallet3 = new Wallet();
		wallet3.setFromFile("김도윤");
		Wallet wallet4 = new Wallet();
		wallet4.setFromFile("송한솔");

		bc = BlockChain.getInstance();
		bc.addBlock();
		//매매계약 맺기 Transaction 생성
		Transaction transaction1 = new Transaction(wallet2, Util.trType.TRADE, "오미숙", "이경훈", 10000000, 1000000, "보문로29길 27");
		Transaction transaction_1 = new Transaction(wallet3, Util.trType.TRADE, "김도윤", "이경훈", 10000000, 1000000, "삼선교로22길 19");
		//매매계약 맺기 Transaction 블록체인에 넣기
		bc.addMakeContractTransaction(transaction1);
		bc.addMakeContractTransaction(transaction_1);
		//매매계약 끝내기 Transaction 생성
		bc.addFinishContractTransaction(wallet2, "보문로29길 27");
		bc.showInformation();
		System.out.println("*****************************************************************");
	
		//전세계약 맺기 Transaction 생성
		Transaction transaction2 = new Transaction(wallet1, Util.trType.CHARTER, "이경훈", "김도윤", 10000000, 1000000, "보문로29길 27");
		bc.addMakeContractTransaction(transaction2);
		bc.addFinishContractTransaction(wallet1, "보문로29길 27");
		bc.addFinishContractTransaction(wallet3,  "삼선교로22길 19");
		bc.showInformation();
		bc.addBlock();
	
		System.out.println("*****************************************************************");
		//월세계약 맺기
		Transaction transaction3 = new Transaction(wallet1, "이경훈", "송한솔", 10000000, 1000000, "보문로29길 27", 500000);
		bc.addMakeContractTransaction(transaction3);
		bc.addFinishContractTransaction(wallet1, "보문로29길 27");
		bc.showInformation();
	
		System.out.println("*****************************************************************");
		ArrayList<Transaction> history = new ArrayList<Transaction>();
		history = bc.getHistory("보문로29길 27");
		for(int i = 0; i < history.size(); i++) {
			history.get(i).showInformation();
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}
		WebDoc.makePage("보문로29길 27");
		MainFrame mainFrame = new MainFrame();
	}
}