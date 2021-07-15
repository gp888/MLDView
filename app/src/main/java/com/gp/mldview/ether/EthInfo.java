package com.gp.mldview.ether;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.gp.mldview.ether.utils.Environment;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * 连接节点的相关信息接口
 */
public class EthInfo {
	private static Web3j web3j;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		getEthInfo();
	}

	/**
	 * 请求区块链的信息
	 */
	private static void getEthInfo() {

		Web3ClientVersion web3ClientVersion = null;
		try {
			//客户端版本
			web3ClientVersion = web3j.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("clientVersion " + clientVersion);
			//区块数量
			EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
			BigInteger blockNumber = ethBlockNumber.getBlockNumber();
			System.out.println(blockNumber);
			//挖矿奖励账户
			EthCoinbase ethCoinbase = web3j.ethCoinbase().send();
			String coinbaseAddress = ethCoinbase.getAddress();
			System.out.println(coinbaseAddress);
			//是否在同步区块
			EthSyncing ethSyncing = web3j.ethSyncing().send();
			boolean isSyncing = ethSyncing.isSyncing();
			System.out.println(isSyncing);
			//是否在挖矿
			EthMining ethMining = web3j.ethMining().send();
			boolean isMining = ethMining.isMining();
			System.out.println(isMining);
			//当前gas price
			EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
			BigInteger gasPrice = ethGasPrice.getGasPrice();
			System.out.println(gasPrice);
			//挖矿速度
			EthHashrate ethHashrate = web3j.ethHashrate().send();
			BigInteger hashRate = ethHashrate.getHashrate();
			System.out.println(hashRate);
			//协议版本
			EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
			String protocolVersion = ethProtocolVersion.getProtocolVersion();
			System.out.println(protocolVersion);
			//连接的节点数
			NetPeerCount netPeerCount = web3j.netPeerCount().send();
			BigInteger peerCount = netPeerCount.getQuantity();
			System.out.println(peerCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TestNet3Params(公共测试网络)/RegTestParams(私有测试网络)/MainNetParams(生产网络)
	 */
	private static final MainNetParams mainnetParams = MainNetParams.get();

	/**
	 * 生成12个助记词
	 *
	 * @return
	 * @throws IOException
	 * @throws MnemonicException.MnemonicLengthException
	 */
	public List<String> getWordList() throws IOException, MnemonicException.MnemonicLengthException {
		MnemonicCode mnemonicCode = new MnemonicCode();
		SecureRandom secureRandom = new SecureRandom();
		/**必须是被4整除*/
		byte[] initialEntropy = new byte[16];
		secureRandom.nextBytes(initialEntropy);
		return mnemonicCode.toMnemonic(initialEntropy);
	}

	/**
	 * 生成12个助记词
	 *
	 * @return
	 * @throws IOException
	 * @throws MnemonicException.MnemonicLengthException
	 */
	@RequiresApi(api = Build.VERSION_CODES.N)
	public String getWordListString() throws IOException, MnemonicException.MnemonicLengthException {
		StringBuilder stringBuilder = new StringBuilder();
		getWordList().stream().forEach(word -> {
			stringBuilder.append(word).append(" ");
		});
		return stringBuilder.toString().trim();
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public void TestBip44ETH() throws Exception {
		String wordList = getWordListString();
		System.out.println("generate mnemonic code:[{}]" + wordList);
		wordList = "please promote sting series horn leave squirrel juice harsh over wash reduce";

		long creationTimeSeconds = System.currentTimeMillis() / 1000;
		DeterministicSeed deterministicSeed = new DeterministicSeed(wordList, null, "", creationTimeSeconds);
		System.out.println("BIP39 seed:{}" + deterministicSeed.toHexString());

		/**生成根私钥 root private key*/
		DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(deterministicSeed.getSeedBytes());
		/**根私钥进行 priB58编码*/
		String priv = rootPrivateKey.serializePrivB58(mainnetParams);
		System.out.println("BIP32 extended private key:{}" + priv);
		/**由根私钥生成HD钱包*/
		DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
		/**定义父路径*/
		List<ChildNumber> parsePath = HDUtils.parsePath("44H/60H/0H");

		DeterministicKey accountKey0 = deterministicHierarchy.get(parsePath, true, true);
		System.out.println("Account extended private key:{}"+ accountKey0.serializePrivB58(mainnetParams));
		System.out.println("Account extended public key:{}"+ accountKey0.serializePubB58(mainnetParams));

		/**由父路径,派生出第一个子私钥*/
		DeterministicKey childKey0 = HDKeyDerivation.deriveChildKey(accountKey0, 0);
//        DeterministicKey childKey0 = deterministicHierarchy.deriveChild(parsePath, true, true, new ChildNumber(0));
//		log.info("BIP32 extended 0 private key:{}", childKey0.serializePrivB58(mainnetParams));
//		log.info("BIP32 extended 0 public key:{}", childKey0.serializePubB58(mainnetParams));
//		log.info("0 private key:{}", childKey0.getPrivateKeyAsHex());
//		log.info("0 public key:{}", childKey0.getPublicKeyAsHex());
		ECKeyPair childEcKeyPair0 = ECKeyPair.create(childKey0.getPrivKeyBytes());
//		log.info("0 address:{}", PREFIX + Keys.getAddress(childEcKeyPair0));

		/**由父路径,派生出第二个子私钥*/
		DeterministicKey childKey1 = HDKeyDerivation.deriveChildKey(accountKey0, 1);
//		log.info("BIP32 extended 1 private key:{}", childKey1.serializePrivB58(mainnetParams));
//		log.info("BIP32 extended 1 public key:{}", childKey1.serializePubB58(mainnetParams));
//		log.info("1 private key:{}", childKey1.getPrivateKeyAsHex());
//		log.info("1 public key:{}", childKey1.getPublicKeyAsHex());
		ECKeyPair childEcKeyPair1 = ECKeyPair.create(childKey1.getPrivKeyBytes());
//		log.info("1 address:{}", Keys.toChecksumAddress(Keys.getAddress(childEcKeyPair1)));
		String address1 = Keys.getAddress(childKey1.decompress().getPublicKeyAsHex().substring(2));
//		log.info("1 address:{}", Keys.toChecksumAddress(address1));

	}

}
