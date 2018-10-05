package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Util {
	//TRADE: 매매, CHARTER: 전세, RENT: 월세
	public enum trType { TRADE, CHARTER, RENT};
	public static String getHash(String input) {
		StringBuffer result = new StringBuffer();
		
		try {
			MessageDigest enc = MessageDigest.getInstance("SHA-256");
			enc.update(input.getBytes());
			byte bytes[] = enc.digest();
			for(int i = 0; i < bytes.length; i++) {
				result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	//현재 날짜를 yyyy/mm/dd 형식의 문자열로 바꿔주는 함수
	public static String getDate() {
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		return format.format(now.getTime());
	}
	
	public static void fileWrite(String filename, String data) {
		try {
			File file = new File(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
			if(file.isFile() && file.canWrite()) {
				bufferedWriter.write(data);
				bufferedWriter.close();
			}
		}
		catch (IOException ioe) {
			System.out.println("파일 쓰기 실패");
		}
		finally {
		}
	}
	public static void fileWrite(String filename, ArrayList<String> data) {
		try {
			File file = new File(filename); 
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
			if(file.isFile() && file.canWrite()) {
				for(int i = 0; i < data.size(); i++) {
					bufferedWriter.write(data.get(i));
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
			}
		}
		catch (IOException ioe) {
			System.out.println("파일 쓰기 실패");
		}
		finally {
		}
	}
	public static ArrayList<String> fileReader(String filename) {
		ArrayList<String> result = new ArrayList<String>(); 

		try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(fileReader);
			String line = "";

			while((line = bufReader.readLine()) != null){
				result.add(line);
			}
			bufReader.close();
			return result;
		}
		catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
