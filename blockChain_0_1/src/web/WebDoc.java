package web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.BlockChain;
import core.Transaction;
import util.Util;

public class WebDoc {

	private static String filename = "./webPage/news/video.html";
	
	private static String webContent = "<!doctype html>\r\n" + 
			"<html>\r\n" + 
			" <head>\r\n" + 
			" <link href=\"..\\title.css\" type=\"text/css\" rel=\"stylesheet\">\r\n" + 
			"   <style>\r\n" + 
			"	.remote{\r\n" + 
			"      right:10px;\r\n" + 
			"      position:fixed;\r\n" + 
			"      top:10px;\r\n" + 
			"   }\r\n" + 
			"	#opa {\r\n" + 
			"	opacity: 0.5;\r\n" + 
			"	}\r\n" + 
			"	\r\n" + 
			"	#formWrapper{\r\n" + 
			"		width: 223px;\r\n" + 
			"		margin:0 auto;\r\n" + 
			"	}\r\n" + 
			"	#tableWrapper{\r\n" + 
			"		margin:0 auto;\r\n" + 
			"		width:80%;\r\n" + 
			"		height: 400px;\r\n" + 
			"	}\r\n" + 
			"	.registerTable{\r\n" + 
			"		width:100%;\r\n" + 
			"		border:none;\r\n" + 
			"		text-align: center;\r\n" + 
			"		cellpadding: 4px;\r\n" + 
			"	}\r\n" + 
			"	.registerTable.th{\r\n" + 
			"		color:gray;\r\n" + 
			"	}\r\n" + 
			"	#addr{\r\n" + 
			"		font-weight: bold;\r\n" + 
			"		font-size: 20px;\r\n" + 
			"		text-align: center;\r\n" + 
			"	}\r\n" + 
			"   </style>\r\n" + 
			" </head>\r\n" + 
			" <body>\r\n" + 
			"	<div class=\"remote\">\r\n" + 
			"		<a href=\"#top\"><img src=\"..\\TOP.png\"></a>\r\n" + 
			"	</div>\r\n" + 
			"	<a name=\"top\"></a>\r\n" + 
			"	<br><div class=\"title\">등기 검색</div><br><div class=\"subt\">해당 등기의 최근 5개 계약 내역을 검색합니다.</div><br><hr id=\"opa\">\r\n" + 
			"		<div id=formWrapper>\r\n" + 
			"			<form action=\"\">\r\n" + 
			"				<p>\r\n" + 
			"					<input type=\"text\">\r\n" + 
			"					<input type=\"submit\" value=\"검색\">\r\n" + 
			"				</p>\r\n" + 
			"			</form>\r\n" + 
			"		</div>\r\n" + 
			"		<br>";
					
	private static String ending = "		</div>\r\n" + 
			" </body>\r\n" + 
			"</html>";
	
	public static void makePage(String addr) {
		try {
			BlockChain bc = BlockChain.getInstance();
			ArrayList<Transaction> histories = bc.getHistory(addr);
			//0번이 가장 최근, 뒤로갈수록 가장 오래전 Transaction
			
			//주소 추가, 트랜잭션 기록 추가
			Util.fileWrite(filename, "");
			File file = new File(filename); 
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
			if(file.isFile() && file.canWrite()) {
				webContent = webContent + "<div id=tableWrapper>" + "<h2 align=\"center\">" + addr + "</h2>" + makeString(histories);
				webContent = webContent + ending;
				bufferedWriter.write(webContent);
				bufferedWriter.newLine();
				bufferedWriter.close();
			}
		}
		catch (IOException ioe) {
			System.out.println("파일 쓰기 실패");
		}
		finally {
		}
	}
	public static String tdWrap(String data) {
		return "<td>" + data + "</td>";
	}
	public static String makeString(ArrayList<Transaction> transactions) {
		
		System.out.println(transactions.size());
		String result = "";
		for(int i = 0; i < transactions.size();  i++) {
			//0번이 가장 최근-> 0번부터 추가
			Transaction trans = transactions.get(i);
			if(trans.getType() == Util.trType.TRADE) {
				result += "<table class=\"registerTable\">\r\n" + 
						"				<tr>\r\n" + 
						"					<th></th>\r\n" + 
						"					<th>종류</th>\r\n" + 
						"					<th>매도자</th>\r\n" + 
						"					<th>매수자</th>\r\n" + 
						"					<th>총금액</th>\r\n" + 
						"					<th>계약금</th>\r\n" + 
						"					<th>계약일</th>\r\n" + 
						"					<th>잔금</th>\r\n" + 
						"					<th>잔금납부일</th>\r\n" + 
						"					<th>현재소유자</th>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>";
				result += tdWrap(Integer.toString(i+1));
				result += tdWrap("매매");
				result += tdWrap(trans.getOwner());
				result += tdWrap(trans.getBuyer());
				result += tdWrap(Integer.toString(trans.getTotalPrice()));
				result += tdWrap(Integer.toString(trans.getDeposit()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(Integer.toString(trans.getBalance()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(trans.getPrevOwner());
				result += "</tr>";
			}
			else if(trans.getType() == Util.trType.CHARTER) {
				result += "<table class=\"registerTable\">\r\n" + 
						"				<tr>\r\n" + 
						"					<th></th>\r\n" + 
						"					<th>종류</th>\r\n" + 
						"					<th>소유자</th>\r\n" + 
						"					<th>세입자</th>\r\n" + 
						"					<th>보증금</th>\r\n" + 
						"					<th>계약금</th>\r\n" + 
						"					<th>계약일</th>\r\n" + 
						"					<th>잔금</th>\r\n" + 
						"					<th>잔금납부일</th>\r\n" + 
						"					<th>전세권자</th>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>";
				result += tdWrap(Integer.toString(i+1));
				result += tdWrap("전세");
				result += tdWrap(trans.getOwner());
				result += tdWrap(trans.getBuyer());
				result += tdWrap(Integer.toString(trans.getTotalPrice()));
				result += tdWrap(Integer.toString(trans.getDeposit()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(Integer.toString(trans.getBalance()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(trans.getChartedPerson());
				result += "</tr>";
			}
			else if(trans.getType() == Util.trType.RENT) {
				result += "<table class=\"registerTable\">\r\n" + 
						"				<tr>\r\n" + 
						"					<th></th>\r\n" + 
						"					<th>종류</th>\r\n" + 
						"					<th>소유자</th>\r\n" + 
						"					<th>세입자</th>\r\n" + 
						"					<th>보증금</th>\r\n" + 
						"					<th>계약금</th>\r\n" + 
						"					<th>계약일</th>\r\n" + 
						"					<th>잔금</th>\r\n" + 
						"					<th>잔금납부일</th>\r\n" + 
						"					<th>임차인</th>\r\n" + 
						"					<th>월세</th>\r\n" + 
						"				</tr>\r\n" + 
						"				<tr>";
				result += tdWrap(Integer.toString(i+1));
				result += tdWrap("월세");
				result += tdWrap(trans.getOwner());
				result += tdWrap(trans.getBuyer());
				result += tdWrap(Integer.toString(trans.getTotalPrice()));
				result += tdWrap(Integer.toString(trans.getDeposit()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(Integer.toString(trans.getBalance()));
				result += tdWrap(trans.getContractDate());
				result += tdWrap(trans.getTenant());
				result += tdWrap(trans.getRentFee());
				result += "</tr>";	
			}
			else {
				System.out.println("트랜잭션 타입 인식 불가");
				return null;
			}
			if(i != 5){
				result += "<br><br>";
			}
		}
		
		return result;
	}
}
