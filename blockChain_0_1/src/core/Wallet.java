package core;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import util.EC;

public class Wallet {
	private static final String ALGORITHM = "SHA1withECDSA";
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setFromFile(String filename) throws Exception{
		this.privateKey = new EC().readPrivateKeyFromPemFile(filename + "_private.pem");
		this.publicKey = new EC().readPublicKeyFromPemFile(filename + "_public.pem");
	}
	
	// 데이터를 개인키로 서명
	public String sign(String data) throws Exception{
		Signature signature;
		signature = Signature.getInstance(ALGORITHM);
		signature.initSign(privateKey);
		byte[] baText = data.getBytes("UTF-8");
		signature.update(baText);
		byte[] baSignature = signature.sign();
		return (new BigInteger(1, baSignature).toString(16)).toUpperCase();
	}
}
