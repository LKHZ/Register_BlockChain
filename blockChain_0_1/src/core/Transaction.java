package core;

import java.security.PublicKey;
import util.Util;
import org.json.simple.JSONObject;

public class Transaction {
	String signature;
	PublicKey ownerKey;
	PublicKey buyerKey;
	private Util.trType type;	//매매(Util.TRADE)	전세(Util.CHARTER)	월세(Util.RENT)
	private String owner;		//매도자				소유자 				소유자
	private String buyer;		//매수자				세입자				전입자
	private int totalPrice;		//총금액				총금액				보증금
	private int deposit;		//계약금				계약금				계약금
	private String contractDate;//계약일				계약일				계약일
	private int balance;		//잔금				잔금					잔금
	private String balanceDate;	//잔금 납부일			잔금 납부일				잔금 납부일
	private String addr;		//주소				주소					주소
	private String prevOwner;	//현재 소유자
	private String chartedPerson;				//	전세권자
	private String rentFee;		//										월세
	private String tenant;		//										임차인

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
		System.out.println("주소: " + getAddr());
		if (getType() == Util.trType.TRADE) {
			System.out.println("종류: 매매");
			System.out.println("매도자: " + getOwner());
			System.out.println("매수자: " + getBuyer());
			System.out.println("총 금액: " + getTotalPrice());
			System.out.println("계약금: " + getDeposit());
			System.out.println("계약일: " + getContractDate());
			System.out.println("잔금: " + getBalance());
			System.out.println("잔금 납부일: " + getBalanceDate());
			System.out.println("현재 소유자: " + getPrevOwner());
		}
		else if(getType() == Util.trType.CHARTER) {
			System.out.println("종류: 전세");
			System.out.println("소유자: " + getOwner());
			System.out.println("세입자: " + getBuyer());
			System.out.println("총 금액: " + getTotalPrice());
			System.out.println("계약금: " + getDeposit());
			System.out.println("계약일: " + getContractDate());
			System.out.println("잔금: " + getBalance());
			System.out.println("잔금 납부일: " + getBalanceDate());
			System.out.println("전세권자: " + getChartedPerson());
		}
		else if(getType() == Util.trType.RENT) {
			System.out.println("종류: 월세");
			System.out.println("소유자: " + getOwner());
			System.out.println("전입자: " + getBuyer());
			System.out.println("총 금액: " + getTotalPrice());
			System.out.println("계약금: " + getDeposit());
			System.out.println("계약일: " + getContractDate());
			System.out.println("잔금: " + getBalance());
			System.out.println("잔금 납부일: " + getBalanceDate());
			System.out.println("월세: " + getRentFee());
			System.out.println("임차인: " + getTenant());
		}
		else 
			System.out.println("종류: error");
	}
	public String getInformation() {
		JSONObject jObj = new JSONObject();
		jObj.put("주소",  getAddr());
		if (getType() == Util.trType.TRADE) {
			jObj.put("종류", "매매");
			jObj.put("매도자", getOwner());
			jObj.put("매수자", getBuyer());
			jObj.put("총금액",  getTotalPrice());
			jObj.put("계약금", getDeposit());
			jObj.put("계약일", getContractDate());
			jObj.put("잔금", getBalance());
			jObj.put("잔금 납부일", getBalanceDate());
			jObj.put("현재 소유자", getPrevOwner());
		}
		else if(getType() == Util.trType.CHARTER) {
			jObj.put("종류", "전세");
			jObj.put("소유자", getOwner());
			jObj.put("세입자", getBuyer());
			jObj.put("총금액",  getTotalPrice());
			jObj.put("계약금", getDeposit());
			jObj.put("계약일", getContractDate());
			jObj.put("잔금", getBalance());
			jObj.put("잔금 납부일", getBalanceDate());
			jObj.put("전세권자", getChartedPerson());
		}
		else if(getType() == Util.trType.RENT) {
			jObj.put("종류", "월세");
			jObj.put("소유자", getOwner());
			jObj.put("전입자", getBuyer());
			jObj.put("총금액",  getTotalPrice());
			jObj.put("계약금", getDeposit());
			jObj.put("계약일", getContractDate());
			jObj.put("잔금", getBalance());
			jObj.put("잔금 납부일", getBalanceDate());
			jObj.put("월세", getRentFee());
			jObj.put("임차인", getTenant());
		}
		else 
			return null;
		String result = jObj.toString();
		return result;
	}

	public Transaction() {	}
	//매매계약, 전세계약  생성자
	public Transaction(Wallet wallet, Util.trType type, String owner, String buyer, int totalPrice, int deposit, String addr) throws Exception{
		//소유자의 공개키를 트랜잭션에 포함시킴.
		this.ownerKey = wallet.getPublicKey();
		this.rentFee = null;					//월세: 전세라서 사용 안함
		this.tenant = null;						//임차인: 사용안함

		this.owner = owner;						//매도자
		this.buyer = buyer;						//매수자
		this.totalPrice = totalPrice;			//총금액
		this.deposit = deposit;					//계약금
		this.contractDate = Util.getDate();		//계약일
		this.balance = totalPrice - deposit;	//잔금
		this.balanceDate = "-";					//잔금 납부일
		this.addr = addr;						//주소

		if(type == Util.trType.TRADE) {
			//매매계약
			this.type = Util.trType.TRADE;
			this.prevOwner = getOwner();
			this.chartedPerson = null;				//전세권자: 사용 안함
		}
		else if(type == Util.trType.CHARTER) {
			//전세계약
			this.type = Util.trType.CHARTER;
			this.prevOwner = null;
			this.chartedPerson = "-";				//전세권자: "-"로 초기화->> 잔금 납부이후 전세권자 변경
		}

		//소유자의 비밀키로 트랜잭션 정보를 서명한 결과
		this.signature = wallet.sign(getInformation());
	}

	//월세계약 생성자
	public Transaction(Wallet wallet, String owner, String buyer, int totalPrice, int deposit, String addr, int rentFee) throws Exception{
		//소유자의 공개키를 트랜잭션에 포함시킴.
		this.ownerKey = wallet.getPublicKey();

		this.type = Util.trType.RENT;
		this.owner = owner;							//소유자
		this.buyer = buyer;							//세입자
		this.totalPrice = totalPrice;				//보증금
		this.deposit = deposit;						//계약금
		this.addr = addr;							//주소
		this.contractDate = Util.getDate();			//계약일
		this.balance = totalPrice - deposit;		//잔금
		this.balanceDate = "-";						//잔금 납부일
		this.rentFee = Integer.toString(rentFee);	//월세
		this.tenant = "-";							//임차인

		this.prevOwner = null;						//이전소유자: 사용안함
		this.chartedPerson = null;					//전세권자: 사용안함

		//소유자의 비밀키로 트랜잭션 정보를 서명한 결과
		this.signature = wallet.sign(getInformation());
	}
}
