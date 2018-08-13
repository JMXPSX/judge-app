package com.vote.model;

public class BlockchainDetail {

	private String encryptedBlock;
    private String trxDescription;
    private String time;
    private String date;
    private int blockNumber;
	public String getEncryptedBlock() {
		return encryptedBlock;
	}
	public void setEncryptedBlock(String encryptedBlock) {
		this.encryptedBlock = encryptedBlock;
	}
	public String getTrxDescription() {
		return trxDescription;
	}
	public void setTrxDescription(String trxDescription) {
		this.trxDescription = trxDescription;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
    
    
}
