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
		ec.generate("�̰���");
		ec.generate("���̼�");
		ec.generate("�赵��");
		ec.generate("���Ѽ�");
		
		Wallet wallet1 = new Wallet();
		wallet1.setFromFile("�̰���");
		Wallet wallet2 = new Wallet();
		wallet2.setFromFile("���̼�");
		Wallet wallet3 = new Wallet();
		wallet3.setFromFile("�赵��");
		Wallet wallet4 = new Wallet();
		wallet4.setFromFile("���Ѽ�");

		bc = BlockChain.getInstance();
		bc.addBlock();
		//�ŸŰ�� �α� Transaction ����
		Transaction transaction1 = new Transaction(wallet2, Util.trType.TRADE, "���̼�", "�̰���", 10000000, 1000000, "������29�� 27");
		Transaction transaction_1 = new Transaction(wallet3, Util.trType.TRADE, "�赵��", "�̰���", 10000000, 1000000, "�Ｑ����22�� 19");
		//�ŸŰ�� �α� Transaction ���ü�ο� �ֱ�
		bc.addMakeContractTransaction(transaction1);
		bc.addMakeContractTransaction(transaction_1);
		//�ŸŰ�� ������ Transaction ����
		bc.addFinishContractTransaction(wallet2, "������29�� 27");
		bc.showInformation();
		System.out.println("*****************************************************************");
	
		//������� �α� Transaction ����
		Transaction transaction2 = new Transaction(wallet1, Util.trType.CHARTER, "�̰���", "�赵��", 10000000, 1000000, "������29�� 27");
		bc.addMakeContractTransaction(transaction2);
		bc.addFinishContractTransaction(wallet1, "������29�� 27");
		bc.addFinishContractTransaction(wallet3,  "�Ｑ����22�� 19");
		bc.showInformation();
		bc.addBlock();
	
		System.out.println("*****************************************************************");
		//������� �α�
		Transaction transaction3 = new Transaction(wallet1, "�̰���", "���Ѽ�", 10000000, 1000000, "������29�� 27", 500000);
		bc.addMakeContractTransaction(transaction3);
		bc.addFinishContractTransaction(wallet1, "������29�� 27");
		bc.showInformation();
	
		System.out.println("*****************************************************************");
		ArrayList<Transaction> history = new ArrayList<Transaction>();
		history = bc.getHistory("������29�� 27");
		for(int i = 0; i < history.size(); i++) {
			history.get(i).showInformation();
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}
		WebDoc.makePage("������29�� 27");
		MainFrame mainFrame = new MainFrame();
	}
}