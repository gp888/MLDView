package com.gp.mldview.ether;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.security.SecureRandom;

import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;
import io.reactivex.Flowable;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //钱包密码
        String pwd ="";
        try {
            File fileDir = new File(Environment.getExternalStorageDirectory().getPath() + "/MyWallet");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

          /*  ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            //keystore文件名
            String filename = WalletUtils.generateWalletFile(pwd, ecKeyPair, fileDir, false);
            //获取keystore内容
            File KeyStore = new File(Environment.getExternalStorageDirectory().getPath() + "/MyWallet/" + filename);
            Log.e("+++","keystore:"+getDatafromFile(KeyStore.getAbsolutePath()));

            String msg = "fileName:\n" + filename
                    + "\nprivateKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPrivateKey())
                    + "\nPublicKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPublicKey());
            Log.e("+++", "create:" + msg);*/
        } catch (Exception e) {
            e.printStackTrace();
        }


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
}
