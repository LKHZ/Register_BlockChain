package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.util.encoders.Base64;

public class EC {
	private final String ALGORITHM = "sect163k1";
	public void generate(String name) throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA", "BC");
		
		ECGenParameterSpec ecsp;
		ecsp = new ECGenParameterSpec(ALGORITHM);
		generator.initialize(ecsp, new SecureRandom());
		
		KeyPair keyPair = generator.generateKeyPair();
		//System.out.println("암호키 한 쌍 생성했습니다.");
		
		PrivateKey priv = keyPair.getPrivate();
		PublicKey pub = keyPair.getPublic();
		String privateKeyName = name + "_private.pem";
		String publicKeyName = name + "_public.pem";
		
		writePemFile(priv, "EC PRIVATE KEY", privateKeyName);
		writePemFile(pub, "EC PUBLIC KEY", publicKeyName);
	}
	
	private void writePemFile(Key key, String description, String filename) throws FileNotFoundException, IOException{
		Pem pemFile = new Pem(key, description);
		pemFile.write(filename);
		//System.out.println(String.format("EC 암호키 %s를 %s에 쓰기 완료", description, filename));
	}

	public PrivateKey readPrivateKeyFromPemFile(String privateKeyName) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		    String data = readString(privateKeyName);
		    //System.out.println("EC 개인키를 " + privateKeyName + "에서 읽기 완료");
		    //System.out.print(data);
		    data = data.replaceAll("-----BEGIN EC PRIVATE KEY-----", "");
		    data = data.replaceAll("-----END EC PRIVATE KEY-----", "");
		    byte[] decoded = Base64.decode(data);
		    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
		    KeyFactory factory = KeyFactory.getInstance("ECDSA");
		    PrivateKey privateKey = factory.generatePrivate(spec);
		    return privateKey;
	}

	public PublicKey readPublicKeyFromPemFile(String publicKeyName) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		    String data = readString(publicKeyName);
		    //System.out.println("EC 개인키를 " + publicKeyName + "에서 읽기 완료");
		    //System.out.print(data);
		    data = data.replaceAll("-----BEGIN EC PUBLIC KEY-----", "");
		    data = data.replaceAll("-----END EC PUBLIC KEY-----", "");
		    byte[] decoded = Base64.decode(data);
		    X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
		    KeyFactory factory = KeyFactory.getInstance("ECDSA");
		    PublicKey publicKey = factory.generatePublic(spec);
		    return publicKey;
	}

	private String readString(String filename) throws FileNotFoundException, IOException {
		    String pem = "";
		    BufferedReader br = new BufferedReader(new FileReader(filename));
		    String line;
		    while ((line = br.readLine()) != null)
		    	pem += line + "\n";
		    br.close();
		    return pem;
	}  
}
