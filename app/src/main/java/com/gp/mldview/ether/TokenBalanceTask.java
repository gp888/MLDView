package com.gp.mldview.ether;

import com.gp.mldview.ether.utils.Environment;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量查询token余额
 */
public class TokenBalanceTask {

	public static class Token {
		public String contractAddress;
		public int decimals;
		public String name;

		public Token(String contractAddress) {
			this.contractAddress = contractAddress;
			this.decimals = 0;
		}

		public Token(String contractAddress, int decimals) {
			this.contractAddress = contractAddress;
			this.decimals = decimals;
		}
	}

	private static Web3j web3j;

	//要查询的token合约地址
	private static List<Token> tokenList;

	//要查询的钱包地址
	private static List<String> addressList;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.BINANCE_MAIN_RPC_URL));
		loadData();
		//如果没有decimals则需要请求
		requestDecimals();
		requestName();
		processTask();
	}


	private static void loadData() {
		tokenList = new ArrayList<>();
		//panda
		tokenList.add(new Token("0xd6b20b77358923327541991d3b0c3dbadb27b0b1"));

		addressList = new ArrayList<>();
		addressList.add("0xa5cd0a361d8007ef65f1158274573521b33a0032");
	}

	private static void requestDecimals() {
		for (Token token : tokenList) {
			token.decimals = TokenClient.getTokenDecimals(web3j, token.contractAddress);
		}
	}

	private static void requestName() {
		for (Token token : tokenList) {
			token.name = TokenClient.getTokenName(web3j, token.contractAddress);
		}
	}

	private static void processTask() {
		for (String address : addressList) {
			for (Token token : tokenList) {
				BigDecimal balance = new BigDecimal(TokenClient.getTokenBalance(web3j, address, token.contractAddress));
				balance.divide(BigDecimal.TEN.pow(token.decimals));
				System.out.println("address " + address + " name " + token.name + " balance " + balance + " decimal " + token.decimals);
			}
		}
	}
}
