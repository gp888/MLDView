package com.gp.mldview.ether;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.List;

import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;
import io.reactivex.Flowable;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Flowable<String> mnemonics() {
        // 1. 生成一组随机的助记词
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE)
                .createMnemonic(entropy, sb::append);
        String mnemonics = sb.toString();
        Log.d("助记词:" , mnemonics);
        return Flowable.just(mnemonics);
    }

    //创建助记词
    public List<String> createMnemonics() throws MnemonicException.MnemonicLengthException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.nextBytes(entropy);
        return  MnemonicCode.INSTANCE.toMnemonic(entropy);
    }

    //m / 44' / 60' / 0' / 0
    //Hardened意思就是派生加固，防止获取到一个子私钥之后可以派生出后面的子私钥
    //必须还有上一级的父私钥才能派生
    public static final ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

    //通过助记词生成HD钱包
    public void onCreateWallet(List<String> words, String PASSWORD) {
        byte[] seed = MnemonicCode.toSeed(words, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        // m / 44' / 60' / 0' / 0 / 0
        DeterministicKey deterministicKey = deterministicHierarchy
                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
        byte[] bytes = deterministicKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        try {
            WalletFile walletFile = Wallet.createLight(PASSWORD, keyPair);
            String address = walletFile.getAddress();
//            mAddress.setText("0x" + address);
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }

    //导出keystore
    public String exportKeyStore(WalletFile wallet) {
        try {
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            return objectMapper.writeValueAsString(wallet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //导出私钥
    public String exportPrivateKey(WalletFile wallet, String PASSWORD) {
        try {
            ECKeyPair ecKeyPair = Wallet.decrypt(PASSWORD, wallet);
            BigInteger privateKey = ecKeyPair.getPrivateKey();
            return  Numeric.toHexStringNoPrefixZeroPadded(privateKey, Keys.PRIVATE_KEY_LENGTH_IN_HEX);
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return null;
    }
//    导出助记词
//    一般可以将助记词加密存储，导出时解密。注意无法从KeyStore或者私钥导出助记词。
//
//    例如：使用IMToken导入私钥或者KeyStore创建的钱包，没有导出助记词的功能 如果是通过助记词创建的，就会有导出助记词的功能

}
