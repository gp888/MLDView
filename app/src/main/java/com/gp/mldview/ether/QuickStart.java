package com.gp.mldview.ether;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

import static com.gp.mldview.ether.utils.Environment.RPC_URL;


/**
 * 快速开始
 */
public class QuickStart {

	private static Web3j web3j;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(RPC_URL));

		Web3ClientVersion web3ClientVersion = null;
		try {
			web3ClientVersion = web3j.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("clientVersion " + clientVersion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
