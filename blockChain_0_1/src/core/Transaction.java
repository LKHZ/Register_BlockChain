package core;

import java.security.PublicKey;
import util.Util;
import org.json.simple.JSONObject;

public class Transaction {
	String signature;
	PublicKey ownerKey;
	PublicKey buyerKey;
	private Util.trType type;	//�Ÿ�(Util.TRADE)	����(Util.CHARTER)	����(Util.RENT)
	private String owner;		//�ŵ���				������ 				������
	private String buyer;		//�ż���				������				������
	private int totalPrice;		//�ѱݾ�				�ѱݾ�				������
	private int deposit;		//����				����				����
	private String contractDate;//�����				�����				�����
	private int balance;		//�ܱ�				�ܱ�					�ܱ�
	private String balanceDate;	//�ܱ� ������			�ܱ� ������				�ܱ� ������
	private String addr;		//�ּ�				�ּ�					�ּ�
	private String prevOwner;	//���� ������
	private String chartedPerson;				//	��������
	private String rentFee;		//										����
	private String tenant;		//										������

	public PublicKey getOwnerKey() {
		return ownerKey;
	}
	public void setOwnerKey(PublicKey ownerKey) {
		this.ownerKey = ownerKey;
	}
	public PublicKey getBuyerKey() {
		return buyerKey;
	}
	public void setBuyerKey(PublicKey buyerKey) {
		this.buyerKey = buyerKey;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Util.trType getType() {
		return type;
	}
	public void setType(Util.trType type) {
		this.type = type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPrevOwner() {
		return prevOwner;
	}
	public void setPrevOwner(String prevOwner) {
		this.prevOwner = prevOwner;
	}
	public String getRentFee() {
		return rentFee;
	}
	public void setRentFee(String rentFee) {
		this.rentFee = rentFee;
	}
	public void setRentFee(int rentFee) {
		this.rentFee = Integer.toString(rentFee);
	}
	public String getChartedPerson() {
		return chartedPerson;
	}
	public void setChartedPerson(String chartedPerson) {
		this.chartedPerson = chartedPerson;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public void copyTransaction(Transaction temp) {
		setSignature(temp.getSignature());
		setOwnerKey(temp.getOwnerKey());
		setBuyerKey(temp.getBuyerKey());
		setType(temp.getType());
		setOwner(temp.getOwner());
		setBuyer(temp.getBuyer());
		setTotalPrice(temp.getTotalPrice());
		setDeposit(temp.getDeposit());
		setContractDate(temp.getContractDate());
		setBalance(temp.getBalance());
		setBalanceDate(temp.getBalanceDate());
		setAddr(temp.getAddr());
		setPrevOwner(temp.getPrevOwner());
		setChartedPerson(temp.getChartedPerson());
		setRentFee(temp.getRentFee());
		setTenant(temp.getTenant());
	}

	public void showInformation() {
		System.out.println("�ּ�: " + getAddr());
		if (getType() == Util.trType.TRADE) {
			System.out.println("����: �Ÿ�");
			System.out.println("�ŵ���: " + getOwner());
			System.out.println("�ż���: " + getBuyer());
			System.out.println("�� �ݾ�: " + getTotalPrice());
			System.out.println("����: " + getDeposit());
			System.out.println("�����: " + getContractDate());
			System.out.println("�ܱ�: " + getBalance());
			System.out.println("�ܱ� ������: " + getBalanceDate());
			System.out.println("���� ������: " + getPrevOwner());
		}
		else if(getType() == Util.trType.CHARTER) {
			System.out.println("����: ����");
			System.out.println("������: " + getOwner());
			System.out.println("������: " + getBuyer());
			System.out.println("�� �ݾ�: " + getTotalPrice());
			System.out.println("����: " + getDeposit());
			System.out.println("�����: " + getContractDate());
			System.out.println("�ܱ�: " + getBalance());
			System.out.println("�ܱ� ������: " + getBalanceDate());
			System.out.println("��������: " + getChartedPerson());
		}
		else if(getType() == Util.trType.RENT) {
			System.out.println("����: ����");
			System.out.println("������: " + getOwner());
			System.out.println("������: " + getBuyer());
			System.out.println("�� �ݾ�: " + getTotalPrice());
			System.out.println("����: " + getDeposit());
			System.out.println("�����: " + getContractDate());
			System.out.println("�ܱ�: " + getBalance());
			System.out.println("�ܱ� ������: " + getBalanceDate());
			System.out.println("����: " + getRentFee());
			System.out.println("������: " + getTenant());
		}
		else 
			System.out.println("����: error");
	}
	public String getInformation() {
		JSONObject jObj = new JSONObject();
		jObj.put("�ּ�",  getAddr());
		if (getType() == Util.trType.TRADE) {
			jObj.put("����", "�Ÿ�");
			jObj.put("�ŵ���", getOwner());
			jObj.put("�ż���", getBuyer());
			jObj.put("�ѱݾ�",  getTotalPrice());
			jObj.put("����", getDeposit());
			jObj.put("�����", getContractDate());
			jObj.put("�ܱ�", getBalance());
			jObj.put("�ܱ� ������", getBalanceDate());
			jObj.put("���� ������", getPrevOwner());
		}
		else if(getType() == Util.trType.CHARTER) {
			jObj.put("����", "����");
			jObj.put("������", getOwner());
			jObj.put("������", getBuyer());
			jObj.put("�ѱݾ�",  getTotalPrice());
			jObj.put("����", getDeposit());
			jObj.put("�����", getContractDate());
			jObj.put("�ܱ�", getBalance());
			jObj.put("�ܱ� ������", getBalanceDate());
			jObj.put("��������", getChartedPerson());
		}
		else if(getType() == Util.trType.RENT) {
			jObj.put("����", "����");
			jObj.put("������", getOwner());
			jObj.put("������", getBuyer());
			jObj.put("�ѱݾ�",  getTotalPrice());
			jObj.put("����", getDeposit());
			jObj.put("�����", getContractDate());
			jObj.put("�ܱ�", getBalance());
			jObj.put("�ܱ� ������", getBalanceDate());
			jObj.put("����", getRentFee());
			jObj.put("������", getTenant());
		}
		else 
			return null;
		String result = jObj.toString();
		return result;
	}

	public Transaction() {	}
	//�ŸŰ��, �������  ������
	public Transaction(Wallet wallet, Util.trType type, String owner, String buyer, int totalPrice, int deposit, String addr) throws Exception{
		//�������� ����Ű�� Ʈ����ǿ� ���Խ�Ŵ.
		this.ownerKey = wallet.getPublicKey();
		this.rentFee = null;					//����: ������ ��� ����
		this.tenant = null;						//������: ������

		this.owner = owner;						//�ŵ���
		this.buyer = buyer;						//�ż���
		this.totalPrice = totalPrice;			//�ѱݾ�
		this.deposit = deposit;					//����
		this.contractDate = Util.getDate();		//�����
		this.balance = totalPrice - deposit;	//�ܱ�
		this.balanceDate = "-";					//�ܱ� ������
		this.addr = addr;						//�ּ�

		if(type == Util.trType.TRADE) {
			//�ŸŰ��
			this.type = Util.trType.TRADE;
			this.prevOwner = getOwner();
			this.chartedPerson = null;				//��������: ��� ����
		}
		else if(type == Util.trType.CHARTER) {
			//�������
			this.type = Util.trType.CHARTER;
			this.prevOwner = null;
			this.chartedPerson = "-";				//��������: "-"�� �ʱ�ȭ->> �ܱ� �������� �������� ����
		}

		//�������� ���Ű�� Ʈ����� ������ ������ ���
		this.signature = wallet.sign(getInformation());
	}

	//������� ������
	public Transaction(Wallet wallet, String owner, String buyer, int totalPrice, int deposit, String addr, int rentFee) throws Exception{
		//�������� ����Ű�� Ʈ����ǿ� ���Խ�Ŵ.
		this.ownerKey = wallet.getPublicKey();

		this.type = Util.trType.RENT;
		this.owner = owner;							//������
		this.buyer = buyer;							//������
		this.totalPrice = totalPrice;				//������
		this.deposit = deposit;						//����
		this.addr = addr;							//�ּ�
		this.contractDate = Util.getDate();			//�����
		this.balance = totalPrice - deposit;		//�ܱ�
		this.balanceDate = "-";						//�ܱ� ������
		this.rentFee = Integer.toString(rentFee);	//����
		this.tenant = "-";							//������

		this.prevOwner = null;						//����������: ������
		this.chartedPerson = null;					//��������: ������

		//�������� ���Ű�� Ʈ����� ������ ������ ���
		this.signature = wallet.sign(getInformation());
	}
}
