package core;

import java.util.ArrayList;
import util.Util;

public class BlockChain {
	public static class BlockChainHolder {
		private static final BlockChain bc = new BlockChain();
	}
	
	public static BlockChain getInstance() {
		return BlockChainHolder.bc;
	}
	
	private int index;
	private ArrayList<Block> blockChain;

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ArrayList<Block> getBlockChain() {
		return blockChain;
	}
	public void setBlockChain(ArrayList<Block> blockChain) {
		this.blockChain = blockChain;
	}
	
	public void showInformation() {
		for(int i = 0; i < blockChain.size(); i++) {
			blockChain.get(i).showInformation();
		}
	}

	public void addBlock() {
		Block block = new Block(index + 1, blockChain.get(index).getBlockHash(), new ArrayList<Transaction>());
		blockChain.add(block);
		index += 1;
	}
	
	public ArrayList<Transaction> getHistory(String addr){
		// index  0번이 가장 최근, 뒤로갈수록 과거
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		int limit = 0;
		Transaction temp = new Transaction();
		for(int i = index; i >= 0; i--) {
			Block block = blockChain.get(i);
			for(int j = block.getTransactionList().size()-1; j >= 0; j--) {
				if(block.getTransactionList().get(j).getAddr().equals(addr)) {
					result.add(block.getTransactionList().get(j));
					limit++;
					if(limit == 5) {
						return result;
					}
				}
			}
		}
		return result;
	}
	public Transaction getLastTransaction(String addr) {
		Transaction result = new Transaction();
		System.out.println(result.getInformation());
		for(int i = index; i >= 0; i--) {
			Block block = blockChain.get(i);
			for(int j = block.getTransactionList().size()-1; j >= 0; j--) {
				if(block.getTransactionList().get(j).getAddr().equals(addr)) {
					result.copyTransaction(block.getTransactionList().get(j));
					return result;
				}
			}
		}
		result = null;
		return result;
	}

	//계약 맺기 
	public void addMakeContractTransaction(Transaction transaction) throws Exception {
		blockChain.get(index).addTransaction(transaction);
	}

	//계약 끝내기
	public void addFinishContractTransaction(Wallet wallet, String addr) throws Exception {
		Transaction temp = new Transaction();
		for(int i = index; i >= 0; i--) {
			Block block = blockChain.get(i);
			for(int j = block.getTransactionList().size()-1; j >= 0; j--) {
				if(block.getTransactionList().get(j).getAddr().equals(addr)) {
					temp.copyTransaction(block.getTransactionList().get(j));
					if(temp.getType() == Util.trType.TRADE) {
						//매매계약 완료
						temp.setBalanceDate(Util.getDate());
						temp.setPrevOwner(temp.getBuyer());
					}
					else if(temp.getType() == Util.trType.CHARTER) {
						//전세계약
						temp.setBalanceDate(Util.getDate());
						temp.setChartedPerson(temp.getBuyer());
					}
					else if(temp.getType() == Util.trType.RENT) {
						//월세계약
						temp.setBalanceDate(Util.getDate());
						temp.setTenant(temp.getBuyer());
					}
					else
						System.out.println("error in blockchain-addFinishContractTransaction");
					temp.setSignature(wallet.sign(temp.getInformation()));
					blockChain.get(index).addTransaction(temp);
					return ;
				}
				else
					continue;
			}
		}
	}

	private BlockChain() {
		this.index = 0;
		this.blockChain = new ArrayList<Block>();
		Block block1 = new Block(index, null, new ArrayList<Transaction>());
		blockChain.add(block1);
	}
}
