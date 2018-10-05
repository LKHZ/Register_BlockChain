package core;

import java.math.BigInteger;
import java.security.Signature;
import java.util.ArrayList;
import util.Util;

public class Block {
	private static final String ALGORITHM = "SHA1withECDSA";
	private int blockID;
	private String previousBlockHash;
	private ArrayList<Transaction> transactionList;

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	
	private boolean verifyTransaction(Transaction transaction) throws Exception{
		Signature signature;
		signature = Signature.getInstance(ALGORITHM);
		byte[] baText = transaction.getInformation().getBytes("UTF-8");
		signature.initVerify(transaction.getOwnerKey());
		signature.update(baText);
		return signature.verify(new BigInteger(transaction.getSignature(), 16).toByteArray());
	}
	
	public void addTransaction(Transaction transaction) throws Exception{
		if(verifyTransaction(transaction)) {
			System.out.println("�������� Ʈ����� �߰�");
			transactionList.add(transaction);
		}
		else {
			System.out.println("���������� Ʈ�����");
		}
	}

	public void showInformation() {   
		System.out.println("--------------------------------------");
		System.out.println("��� ��ȣ: " + getBlockID());
		System.out.println("���� �ؽ�: " + getPreviousBlockHash());
		System.out.println("��� �ؽ�: " + getBlockHash());
		System.out.println("Ʈ����� ����: " + transactionList.size() + "��");
		for (int i = 0; i < transactionList.size(); i++) {
			System.out.println(transactionList.get(i).getInformation());
		}
		System.out.println("--------------------------------------");
	}

	public String getBlockHash() {
		String transactionInformation = "";
		for(int i = 0; i < transactionList.size(); i++) {
			transactionInformation += transactionList.get(i).getInformation();
		}
	  	return Util.getHash(transactionInformation + previousBlockHash);
  	}

	public Block(int blockID, String previousBlockHash, ArrayList<Transaction> transactionList) {
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.transactionList = transactionList;
	}
}

