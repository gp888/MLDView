package com.gp.mldview.ether;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gp.mldview.R;
import com.gp.mldview.ether.utils.TianWallet;
import com.gp.mldview.ether.utils.Web3Utils;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.disposables.Disposable;

import static com.gp.mldview.ether.utils.Environment.RPC_URL;


public class Web3jActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = Web3jActivity.class.getSimpleName();
    String password = "123456789";
    Web3j web3j;
    Admin admin;
    TextView tvVersion;
    TextView tvAccount;
//    CountDownLatch countDownLatch = new CountDownLatch(10);
//    countDownLatch.countDown();
//    countDownLatch.await(10, TimeUnit.MINUTES);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_web3j);
        tvVersion = findViewById(R.id.tv_version);
        tvAccount = findViewById(R.id.tv_account);

        findViewById(R.id.create_Account).setOnClickListener(this);
        findViewById(R.id.account_list).setOnClickListener(this);
        findViewById(R.id.unlock_account).setOnClickListener(this);

        initWeb3j();

        String words = Web3Utils.INSTANCE.makeMnemonic();
        Log.d("Web3jActivity===", words);
        TianWallet wallet = Web3Utils.INSTANCE.createETHWalletFromWords(words);
        Log.d("Web3jActivity===", wallet.toString());

//        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/YOURKEY"));
//        Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
//        if(!clientVersion.hasError()){
//            //Connected
//        }
//        else {
//            //Show Error
//        }

        //创建钱包
//        WalletUtils.generateNewWalletFile(password, new File(""));

        //获取地址
//        Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
//        Toast.makeText(this, "Your address is " + credentials.getAddress(), Toast.LENGTH_LONG).show();

        //发送交易
//        Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
//        TransactionReceipt receipt = Transfer.sendFunds(web3,credentials,"0x31B98D14007bDEe637298086988A0bBd31184523",
//                new BigDecimal(1), Convert.Unit.ETHER).sendAsync().get();
//        Toast.makeText(this, "Transaction complete: " +receipt.getTransactionHash(), Toast.LENGTH_LONG).show();
    }

    private void initWeb3j() {
        InitWeb3JTask task = new InitWeb3JTask();
        task.execute(RPC_URL);
    }

    private class InitWeb3JTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String result = "Success initializing web3j/rpc";
            try {
                long startTime = System.currentTimeMillis();
                HttpService httpService =  new HttpService(url);
                web3j = Web3j.build(httpService);
                Log.d(TAG, "end_time:" + (System.currentTimeMillis() - startTime));

                Web3ClientVersion version1 = web3j.web3ClientVersion().send();
                result = version1.getWeb3ClientVersion();

                admin = Admin.build(httpService);

                Web3ClientVersion version = admin.web3ClientVersion().send();
                result = version.getWeb3ClientVersion();
            } catch (Exception wtf) {
                result = wtf.toString();
                Log.d(TAG, "Error initializing web3j/infura. Error: " + wtf);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvVersion.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.create_Account) {
            createNewAccount();
        } else if (v.getId()==R.id.account_list){
            getAccounts();
        }else if (v.getId()==R.id.unlock_account){
            unlockAccount();
        }
    }

    private void createNewAccount() {
        NewAccountsTask newAccountsTask = new NewAccountsTask();
        newAccountsTask.execute();
    }

    private void getAccounts() {
        AccountListTask accountListTask = new AccountListTask();
        accountListTask.execute();
    }

    private void unlockAccount() {
        UnlockAccountTask accountListTask = new UnlockAccountTask();
        accountListTask.execute();
    }

    private class NewAccountsTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String address ;
            try{
                NewAccountIdentifier newAccountIdentifier = admin.personalNewAccount(password).send();
                address = newAccountIdentifier.getAccountId();
                Log.d(TAG, "address:" + address);
            }catch (IOException e){
                e.printStackTrace();
                address = e.getMessage();
            }
            return address;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvAccount.setText(result);
        }
    }

    private class AccountListTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try{
                List<String> accounts = web3j.ethAccounts().send().getAccounts();
                Log.e(TAG,"accountSize:" + accounts.size());
                for (String account:accounts){
                    result += "  \n " + account ;
                }


                PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
                List<String> addressList = personalListAccounts.getAccountIds();
                Log.d(TAG, "account size " + addressList.size());
                for (String address : addressList) {
                    Log.d(TAG, "account address " + address);
                }
            }catch (IOException e){
                e.printStackTrace();
                result = "获取账号出错啦" + e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(Web3jActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    private class UnlockAccountTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            String address = "0x05f50cd5a97d9b3fec35df3d0c6c8234e6793bdf";
            String password = "123456789";
            //账号解锁持续时间 单位秒 缺省值300秒
            BigInteger unlockDuration = BigInteger.valueOf(60L);
            try {
                PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(address, password, unlockDuration).send();
                Boolean isUnlocked = personalUnlockAccount.accountUnlocked();
                Log.d(TAG, "account unlock " + isUnlocked);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(Web3jActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    void clientVersionExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);


        Disposable subscription = web3j.web3ClientVersion().flowable().subscribe(x -> {
            Log.d(TAG, "Client is running version:" + x.getWeb3ClientVersion());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        subscription.dispose();
    }
}
